package com.drawing_application.MVC_pattern;


import com.drawing_application.MVC_pattern.front.components.DrawArea;
import com.drawing_application.MVC_pattern.front.components.color_picker.ColorPicker;
import com.drawing_application.MVC_pattern.front.components.color_picker.ColorPickerPanel;
import com.drawing_application.MVC_pattern.front.components.color_picker.RGBColorPickerPanel;
import com.drawing_application.MVC_pattern.observer.ModelObserver;
import com.drawing_application.MVC_pattern.observer.ViewObserver;
import mouse_event.MouseHandler;
import mouse_event.MouseMotionHandler;

import javax.swing.*;
import java.awt.*;

public class PaintView
    extends JFrame
{
    private final ViewObserver controller = PaintController.getInstance();
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    JPanel mainPanel = new JPanel(), topPanel = new JPanel(), rightPanel = new JPanel()
            , leftPanel = new JPanel();
    ColorPicker colorPicker = new ColorPickerPanel(this), RGBColorPicker = new RGBColorPickerPanel(this);
    final int NUMBER_OF_PAGES = 500;
    int whichPageTheUserIsLooking = 1;

    public DrawArea[] drawAreas = new DrawArea[NUMBER_OF_PAGES];
    public PaintView()
    {


        // Create menu bar
        var mainWindowMenuBar = new JMenuBar();

        // Menu Bar items
        // options list
        var options = new JMenu("Options");

        // Menu items
        var createNewFile = new JMenuItem("New file");
        var saveFile = new JMenuItem("Save file");

        // Make panels for color pickers
        var panelForColorPickers = new JPanel();

        // Make horizontal and vertical scrolls for draw area
        var leftPanelScroll = new JScrollPane(this.leftPanel);

        // Button to go to the next page
        var nextPageButton = new JButton("next");

        // Add previous button
        var previousPageButton = new JButton("previous");

        // Create slider for controlling thickness
        var thicknessSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 3);
        thicknessSlider.setMinorTickSpacing(5);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.setPaintLabels(true);




        thicknessSlider.addChangeListener(e -> {
            var value = ((JSlider) e.getSource()).getValue();
            PaintController.getInstance().loads.setThickness(value);
        });

        // Merge menus
        mainWindowMenuBar.add(options);
        options.add(createNewFile);
        options.add(saveFile);

        // Set the minimum window frame size
        this.setPreferredSize(new Dimension((int) this.toolkit.getScreenSize().getWidth() - 100
                , (int) this.toolkit.getScreenSize().getHeight() - 100));

        // Set the preferred size of the main panel of main frame
        this.mainPanel.setPreferredSize(this.getPreferredSize());

        // Set preferred sizes for top right left panels
        int mainPanelW = (int) this.mainPanel.getPreferredSize().getWidth()
                , mainPanelH = (int) this.mainPanel.getPreferredSize().getHeight();
        this.topPanel.setPreferredSize(new Dimension(mainPanelW,  100));
        this.rightPanel.setPreferredSize(new Dimension(100, mainPanelH));
        this.leftPanel.setMinimumSize(new Dimension(mainPanelW - 100, mainPanelH - 100));

        // Set positions of the color picker's panel
        panelForColorPickers.setBounds((int) (this.toolkit.getScreenSize().getWidth() - 100) - 400, 10, 320, 80);

        // Add menu bar to main frame
        this.setJMenuBar(mainWindowMenuBar);
        // Set layout of the right panel to flow layout

        // Add next page button to the right panel
        this.rightPanel.add(nextPageButton);

        // Add previous page button to the right panel
        this.rightPanel.add(previousPageButton);

        // set borders on the panels to outline them
        this.topPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 10, 0, Color.gray));
        this.mainPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 10));


        // Add action listener to the button
        this.nextPageButtonClicked(nextPageButton, leftPanelScroll);

        // Add action listener to the previous button
        this.previousPageButtonClicked(previousPageButton, leftPanelScroll);

        // Add color pickers to the color picker's panel
        panelForColorPickers.add(colorPicker);
        panelForColorPickers.add(RGBColorPicker);

        // Add color picker's panel to the top panel
        this.topPanel.add(panelForColorPickers);

        // Set bounds on thicknessSlider
        thicknessSlider.setBounds((int) (this.toolkit.getScreenSize().getWidth() - 100) - 800, 10, 320, 80);

        // Add slider to the top panel
        this.topPanel.add(thicknessSlider);

        // Set top panel layout to null
        this.topPanel.setLayout(null);
        this.rightPanel.setLayout(new GridLayout(2, 1));


        // Make main panel layout border
        this.mainPanel.setLayout(new BorderLayout());

        // Add three main panels to the main panel
        this.mainPanel.add(this.topPanel, BorderLayout.NORTH);
        this.mainPanel.add(this.rightPanel, BorderLayout.EAST);
        this.mainPanel.add(leftPanelScroll, BorderLayout.CENTER);
        this.add(this.mainPanel);

        // Create 500 draw areas
        this.drawAreas(NUMBER_OF_PAGES);




        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void nextPageButtonClicked(JButton nextPageButton, JScrollPane leftPanelScroll)
    {
        JViewport viewPort = leftPanelScroll.getViewport();
        nextPageButton.addActionListener((event) -> {
            viewPort.setViewPosition(new Point(this.whichPageTheUserIsLooking * 1250, 0));
            this.whichPageTheUserIsLooking++;
        });
    }

    private void previousPageButtonClicked(JButton previousPageButton, JScrollPane leftPanelScroll)
    {
        JViewport viewport = leftPanelScroll.getViewport();
        previousPageButton.addActionListener((event) -> {
        if (this.whichPageTheUserIsLooking >= 1)
        {
            this.whichPageTheUserIsLooking--;
        }
            viewport.setViewPosition(new Point(this.whichPageTheUserIsLooking * 1250, 0));
        });
    }


    public void notifyController(ColorPicker from)
    {
        this.controller.updateFromView(from.getColor());
    }


    public void drawAreas(int number)
    {
        for (int i = 0; i < number; i++) {
            var drawArea = new DrawArea();
            // Add left draw area to the left panel, and set preferred size to draw area
            drawArea.setPreferredSize(new Dimension((int) this.leftPanel.getMinimumSize().getWidth() - 100
                    , (int) this.leftPanel.getMinimumSize().getHeight() - 110));
            var mouseHandler = new MouseHandler(i);
            var mouseMotionHandler = new MouseMotionHandler(i);
            drawArea.addMouseListener(mouseHandler);
            drawArea.addMouseMotionListener(mouseMotionHandler);
            this.drawAreas[i] = drawArea;
            this.leftPanel.add(drawArea);
        }
    }


}
