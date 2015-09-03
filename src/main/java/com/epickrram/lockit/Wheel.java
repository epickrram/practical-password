package com.epickrram.lockit;

//////////////////////////////////////////////////////////////////////////////////
//   Copyright 2015   Mark Price     mark at epickrram.com                      //
//                                                                              //
//   Licensed under the Apache License, Version 2.0 (the "License");            //
//   you may not use this file except in compliance with the License.           //
//   You may obtain a copy of the License at                                    //
//                                                                              //
//       http://www.apache.org/licenses/LICENSE-2.0                             //
//                                                                              //
//   Unless required by applicable law or agreed to in writing, software        //
//   distributed under the License is distributed on an "AS IS" BASIS,          //
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.   //
//   See the License for the specific language governing permissions and        //
//   limitations under the License.                                             //
//////////////////////////////////////////////////////////////////////////////////


import java.io.Console;
import java.util.Arrays;
import java.util.function.Consumer;

public final class Wheel
{
    private static final char[] LOWER_CASE_CHARS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] UPPER_CASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] SPECIAL_CHARS = "_-!.?,:;$&()".toCharArray();
    private static final char[] NUMBER_CHARS = "0123456789".toCharArray();
    private static final char[][] CHARACTER_CLASSES = new char[][] {
            LOWER_CASE_CHARS, UPPER_CASE_CHARS, SPECIAL_CHARS, NUMBER_CHARS};

    private static final char[] MNEMONIC_CHARS = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    static final int TOTAL_SPOKES = MNEMONIC_CHARS.length;
    static final int DEPTH = 4;
    static final int CHARS_PER_SPOKE_ENTRY = 4;

    public static final int CHARS_PER_KEY = DEPTH * CHARS_PER_SPOKE_ENTRY;

    private final Spoke[] spokes;
    private final ViewPort viewPort;
    private int keyPointer = 0;
    private int classPointer = 0;

    public Wheel(final byte[] key)
    {
        spokes = createSpokes(key);
        viewPort = new ViewPort(TOTAL_SPOKES - 1);
    }

    public void printCode(final int viewPortPosition, final String mnemonic, final Consumer<String> consumer)
    {
        if(isInvalidMnemonic(mnemonic))
        {
            throw new IllegalArgumentException();
        }
        viewPort.setPosition(viewPortPosition);
        final int[] spokePositions = new int[DEPTH];
        Arrays.fill(spokePositions, -1);

        for(int depth = 0; depth < DEPTH; depth++)
        {
            for (final Spoke spoke : spokes)
            {
                if (spoke.getElements()[depth].getCode() == mnemonic.charAt(depth))
                {
                    spokePositions[depth] = spoke.getPosition();
                    break;
                }
            }
        }

        final StringBuilder debug = new StringBuilder();
        final StringBuilder password = new StringBuilder();
        for(int depth = 0; depth < DEPTH; depth++)
        {
            final int viewableSpokePosition = (spokePositions[depth] + viewPortPosition) % TOTAL_SPOKES;
            final Spoke spokeUnderViewPortAtDepth = spokes[viewableSpokePosition];
            password.append(spokeUnderViewPortAtDepth.getElements()[depth].getTokens());
            debug.append(spokeUnderViewPortAtDepth.getElements()[depth]);
        }

        consumer.accept(password.toString());
    }

    public static boolean isInvalidMnemonic(final String mnemonic)
    {
        return mnemonic.trim().length() != DEPTH;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();

        for(int i = 0; i < TOTAL_SPOKES; i++)
        {
            builder.append(spokes[i]).append("\n");
        }

        return builder.toString();
    }

    private Spoke[] createSpokes(final byte[] key)
    {
        final Spoke[] spokes = new Spoke[TOTAL_SPOKES];

        for(int spokePosition = 0; spokePosition < TOTAL_SPOKES; spokePosition++)
        {
            spokes[spokePosition] = new Spoke(spokePosition, createSpokeElements(key, MNEMONIC_CHARS[spokePosition]));
        }

        return spokes;
    }

    private SpokeElement[] createSpokeElements(final byte[] key, final char mnemonicChar)
    {
        final SpokeElement[] elements = new SpokeElement[DEPTH];
        for(int depth = 0; depth < DEPTH; depth++)
        {
            elements[depth] = new SpokeElement(depth, mnemonicChar, randomString(CHARS_PER_SPOKE_ENTRY, key));
        }

        return elements;
    }

    private String randomString(final int length, final byte[] key)
    {
        final StringBuilder builder = new StringBuilder(length);
        for(int i = 0; i < length; i++)
        {
            final char[] characterClass = CHARACTER_CLASSES[classPointer++ % CHARACTER_CLASSES.length];
            builder.append(characterClass[Math.abs(key[keyPointer++]) % characterClass.length]);
        }

        return builder.toString();
    }
}
