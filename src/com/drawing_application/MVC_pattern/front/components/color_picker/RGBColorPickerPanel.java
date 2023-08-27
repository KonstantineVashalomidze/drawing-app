package com.drawing_application.MVC_pattern.front.components.color_picker;

import com.drawing_application.MVC_pattern.PaintView;
import com.drawing_application.MVC_pattern.front.components.color_picker.ColorPicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Objects;

public class RGBColorPickerPanel
    extends ColorPicker
{
    private final int cellSize = 40;

    JTextField number1 = new JTextField(3), number2 = new JTextField(3), number3 = new JTextField(3);

    JPanel colorCell = new JPanel();

    PaintView frameToAdd;

    public RGBColorPickerPanel(PaintView frameToAdd) {
        super(frameToAdd);

        this.frameToAdd = frameToAdd;

        this.setLayout(new GridLayout(0, 4));

        this.number1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
        this.number2.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.gray));
        this.number3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.gray));
        this.colorCell.setBorder(BorderFactory.createLineBorder(Color.gray, 1));


        this.add(this.colorCell);
        this.add(this.number1);
        this.add(this.number2);
        this.add(this.number3);

        this.colorCell.setBorder(BorderFactory.createLineBorder(new Color(213, 2, 255)));


        // Check if the all the values are written
        this.number1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                allValuesAreFilled();
            }
        });

        this.number2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                allValuesAreFilled();
            }
        });

        this.number3.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                allValuesAreFilled();
            }
        });
    }

    public void allValuesAreFilled()
    {

        if (!this.number1.getText().equals("") && !this.number2.getText().equals("") && !this.number3.getText().equals(""))
        {
            this.colorCell.setBackground(
                    new Color(Integer.parseInt(this.number1.getText())
                            , Integer.parseInt(this.number2.getText())
                            , Integer.parseInt(this.number3.getText())));
            this.frameToAdd.notifyController(this);
            this.repaint();
        }
    }


    @Override
    public Color getColor() {
        return Objects.requireNonNullElse(this.colorCell.getBackground(), Color.gray);
    }
}
