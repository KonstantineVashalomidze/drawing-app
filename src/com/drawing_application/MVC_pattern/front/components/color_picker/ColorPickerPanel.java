package com.drawing_application.MVC_pattern.front.components.color_picker;

import com.drawing_application.MVC_pattern.PaintView;
import com.drawing_application.MVC_pattern.front.components.color_picker.ColorPicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class ColorPickerPanel
        extends ColorPicker
{
    private final Color[] colors = {
            Color.RED, Color.GREEN, Color.BLUE,
            Color.YELLOW, Color.ORANGE, Color.CYAN,
            Color.MAGENTA, Color.PINK, Color.LIGHT_GRAY,
            Color.DARK_GRAY, Color.BLACK, Color.WHITE
    };

    JPanel[] colorCells = new JPanel[12];

    private Color color;

    private final int cellSize = 30;

    private ColorPicker self = this;

    public ColorPickerPanel(PaintView frameToAdd) {
        super(frameToAdd);
        this.setLayout(new GridLayout(0, 6));
        var border = BorderFactory.createLineBorder(Color.gray, 3);
        var counter = 0;
        for (Color color : this.colors) {
            JPanel colorCell = new JPanel();
            colorCell.setPreferredSize(new Dimension(this.cellSize, this.cellSize));
            colorCell.setBackground(color);
            this.colorCells[counter] = colorCell;
            counter++;
            colorCell.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    setColor(colorCell.getBackground());
                    colorCell.setBorder(border);
                    clearSelectionExcept(colorCell);
                    frameToAdd.notifyController(self);
                }
            });

            this.add(colorCell);
        }
    }


    private void clearSelectionExcept(JPanel self)
    {
        for (JPanel colorCell: this.colorCells)
        {
            if (colorCell != self)
            {
                colorCell.setBorder(null);
            }
            colorCell.repaint();
        }
    }

    private void setColor(Color color)
    {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return Objects.requireNonNullElse(this.color, Color.gray);
    }
}