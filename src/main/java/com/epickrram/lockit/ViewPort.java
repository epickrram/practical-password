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


public final class ViewPort
{
    private final int maxPosition;
    private int position;

    public ViewPort(final int maxPosition)
    {
        this.maxPosition = maxPosition;
    }

    public void setPosition(final int viewPortPosition)
    {
        if(viewPortPosition < 0 || viewPortPosition > maxPosition)
        {
            throw new IllegalArgumentException();
        }

        position = viewPortPosition;
    }
}
