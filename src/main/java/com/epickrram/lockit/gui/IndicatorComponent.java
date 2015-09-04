package com.epickrram.lockit.gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public final class IndicatorComponent
{
    private static final int SHAPE_SIZE = 50;
    private static final int HGAP = 10;
    private static final Color[] COLOURS = new Color[]
            {
                    Color.RED,
                    Color.BLUE,
                    Color.GREEN,
                    Color.BLACK,
                    Color.YELLOW,
                    Color.AQUAMARINE,
                    Color.TURQUOISE,
                    Color.DARKVIOLET
            };

    private final Canvas canvas = new Canvas(2 * SHAPE_SIZE + HGAP, SHAPE_SIZE);
    private final IndicatorCalculator calculator = new IndicatorCalculator();

    public IndicatorComponent()
    {
        updateValue("");
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public void updateValue(final String value)
    {
        final GraphicsContext gfx = canvas.getGraphicsContext2D();
        gfx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        if(value.trim().length() == 0)
        {
            return;
        }
        final int checksum = value.hashCode();
        paintShape(checksum, gfx, 0, 1, 0);
        paintShape(checksum, gfx, 2, 3, SHAPE_SIZE + HGAP);
    }

    private void paintShape(final int checksum, final GraphicsContext gfx,
                            final int colourIndex, final int shapeIndex,
                            final int xOffset)
    {
        gfx.setFill(getColour(calculator.getComponent(colourIndex, checksum)));
        gfx.setStroke(getColour(calculator.getComponent(colourIndex, checksum)));
        gfx.setLineWidth(4);
        switch (calculator.getComponent(shapeIndex, checksum))
        {
            case 0:
                gfx.fillRect(xOffset, 0, SHAPE_SIZE, SHAPE_SIZE);
                break;
            case 1:
                gfx.fillOval(xOffset, 0, SHAPE_SIZE, SHAPE_SIZE);
                break;
            case 2:
                gfx.fillRect(xOffset, 2 * HGAP, SHAPE_SIZE, HGAP);
                break;
            case 3:
                gfx.fillRect(xOffset + 2 * HGAP, 0, HGAP, SHAPE_SIZE);
                break;
            case 4:
                gfx.fillRect(xOffset, 0, SHAPE_SIZE, SHAPE_SIZE);
                break;
            case 5:
                gfx.fillOval(xOffset, 0, SHAPE_SIZE, SHAPE_SIZE);
                break;
            case 6:
                gfx.fillRect(xOffset, 2 * HGAP, SHAPE_SIZE, HGAP);
                break;
            case 7:
                gfx.fillRect(xOffset + 2 * HGAP, 0, HGAP, SHAPE_SIZE);
                break;
            default:
                throw new RuntimeException("This is not the case you're looking for");

        }
    }

    private static Color getColour(final int colourIndex)
    {
        return COLOURS[colourIndex];
    }
}
