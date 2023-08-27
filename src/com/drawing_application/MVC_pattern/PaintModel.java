package com.drawing_application.MVC_pattern;


import com.drawing_application.MVC_pattern.front.components.DrawArea;
import com.drawing_application.MVC_pattern.observer.ModelObserver;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class PaintModel
{

    private Color currentColor;
    private int thickness;





    public void setMainColor(Color mainColor)
    {
        this.currentColor = mainColor;
    }


    public Color getMainColor()
    {
        return this.currentColor;
    }

    public void setThickness(int thickness)
    {
        this.thickness = thickness;
    }

    public int getThickness()
    {
        return this.thickness;
    }
}
