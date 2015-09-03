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


public final class SpokeElement
{
    private final int depth;
    private final char code;
    private final String tokens;

    public SpokeElement(final int depth, final char code, final String tokens)
    {
        this.depth = depth;
        this.code = code;
        this.tokens = tokens;
    }

    public int getDepth()
    {
        return depth;
    }

    public char getCode()
    {
        return code;
    }

    public String getTokens()
    {
        return tokens;
    }

    @Override
    public String toString()
    {
        return code + " " + tokens + "|";
    }
}
