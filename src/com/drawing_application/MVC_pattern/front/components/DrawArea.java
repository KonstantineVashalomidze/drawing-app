package com.drawing_application.MVC_pattern.front.components;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashSet;

public class DrawArea
    extends JPanel
{
    private LinkedHashSet<ShapeWithColor> shapeToDraw;

    public DrawArea()
    {
        this.setBackground(Color.BLACK);
        this.shapeToDraw = new LinkedHashSet<>();

    }

    public static class ShapeWithColor
    {
        Color color;
        Shape shape;
        boolean mouseReleased = false;
        public int thickness;

        public ShapeWithColor(Color color, Shape shape, int thickness)
        {
            this.color = color;
            this.shape = shape;
            this.thickness = thickness;
        }

        public void setMouseReleased()
        {
            this.mouseReleased = true;
        }


        @Override
        public String toString()
        {
            return this.mouseReleased + " " + this.color + " " + this.shape;
        }


    }


    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        var g2 = (Graphics2D) g;

        var shapeToDrawIterator = this.shapeToDraw.iterator();
        ShapeWithColor previous = null;

        while (shapeToDrawIterator.hasNext())
        {
            var current = shapeToDrawIterator.next();
            if (previous != null)
            {
                g2.setPaint(current.color);
                g2.draw(current.shape);
                g2.fill(current.shape);
                g2.setStroke(new BasicStroke(current.thickness));
                if (current.mouseReleased == previous.mouseReleased && !current.mouseReleased)
                {
                    g2.drawLine(previous.shape.getBounds().x, previous.shape.getBounds().y, current.shape.getBounds().x, current.shape.getBounds().y);
                }
            }
            previous = current;
        }


        this.shapeToDraw.forEach(shapeWithColor -> {
            g2.setPaint(shapeWithColor.color);
            g2.draw(shapeWithColor.shape);
        });

    }



    public void collectShapeToDrawPointCoordinates(ShapeWithColor shape)
    {
        this.shapeToDraw.add(shape);
        this.repaint();
    }

    public void removeShapeFrom(ShapeWithColor shape)
    {
        this.shapeToDraw.remove(shape);
    }

}
