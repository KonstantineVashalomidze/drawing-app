package com.drawing_application.MVC_pattern.front.components.color_picker;

import com.drawing_application.MVC_pattern.PaintView;

import javax.swing.*;
import java.awt.*;

public abstract class ColorPicker
    extends JPanel
{
    public ColorPicker(PaintView frameToAdd)
    {

    }
    public abstract Color getColor();
}
