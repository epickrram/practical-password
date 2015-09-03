package com.epickrram.lockit.gui;

public final class IndicatorCalculator
{
    private static final int COMPONENT_BIT_LENGTH = 4;
    private static final int MAX_COMPONENTS = 4;

    public int getComponent(final int index, final int indicator)
    {
        return (indicator >> ((MAX_COMPONENTS - 1 - index) * COMPONENT_BIT_LENGTH)) &
                ((COMPONENT_BIT_LENGTH << 1) - 1);
    }

    public static void main(String[] args)
    {
        final int indicator = 0b00_01_10_11;
        System.out.println(new IndicatorCalculator().getComponent(0, indicator));
        System.out.println(new IndicatorCalculator().getComponent(1, indicator));
        System.out.println(new IndicatorCalculator().getComponent(2, indicator));
        System.out.println(new IndicatorCalculator().getComponent(3, indicator));
    }
}