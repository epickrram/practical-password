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


public final class Spoke
{
    private final int position;
    private final SpokeElement[] elements;

    public Spoke(final int position, final SpokeElement[] elements)
    {
        this.position = position;
        this.elements = elements;
    }

    public int getPosition()
    {
        return position;
    }

    public SpokeElement[] getElements()
    {
        return elements;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();
        for (SpokeElement element : elements)
        {
            builder.append(element);
        }

        return builder.toString();
    }
}
