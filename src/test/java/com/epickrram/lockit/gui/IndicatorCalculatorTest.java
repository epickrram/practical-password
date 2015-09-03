package com.epickrram.lockit.gui;

//import static org.junit.Assert.*;

public final class IndicatorCalculatorTest
{
//    @org.junit.Test
    public void shouldFindComponents() throws Exception
    {
        final int indicator = 0b11_01_10_00;

        assertExpected(0b11, indicator, 0);
        assertExpected(0b01, indicator, 1);
        assertExpected(0b10, indicator, 2);
        assertExpected(0b00, indicator, 3);
    }

    private void assertExpected(final int expected, final int indicator, final int index)
    {

    }
}