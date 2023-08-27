package com.drawing_application.MVC_pattern;

import com.drawing_application.MVC_pattern.PaintView;

import java.awt.*;

public class Main
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
           var paintView = new PaintView();
           var paintModel = new PaintModel();
           PaintController.getInstance();
           PaintController.getInstance().init(paintView, paintModel);
           paintView.setVisible(true);
        });
    }
}