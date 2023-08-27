package com.drawing_application.MVC_pattern;


import com.drawing_application.MVC_pattern.observer.ModelObserver;
import com.drawing_application.MVC_pattern.observer.ViewObserver;

import java.awt.*;

public class PaintController
    implements ModelObserver, ViewObserver
{
    public PaintView changes;
    public PaintModel loads;
    private static PaintController paintController;

    private PaintController()
    {

    }

    @Override
    public void updateFromModel()
    {

    }

    @Override
    public void updateFromView(Color mainColor)
    {
        this.loads.setMainColor(mainColor);
    }


    public static PaintController getInstance()
    {
        if (paintController == null)
        {
            paintController = new PaintController();
        }
        return paintController;
    }

    public void init(PaintView changes, PaintModel loads)
    {
        this.changes = changes;
        this.loads = loads;
    }





}
