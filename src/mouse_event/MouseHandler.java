package mouse_event;

import com.drawing_application.MVC_pattern.PaintController;
import com.drawing_application.MVC_pattern.front.components.DrawArea;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.Objects;

public class MouseHandler
    extends MouseAdapter
{
    PaintController controller;
    int indexOfDrawArea;

    int thickness;
    public MouseHandler(int indexOfDrawArea)
    {
        this.indexOfDrawArea = indexOfDrawArea;
        controller = PaintController.getInstance();
    }

    @Override
    public void mousePressed(MouseEvent event)
    {

    }

    @Override
    public void mouseReleased(MouseEvent event)
    {
        this.thickness = controller.loads.getThickness();
        var drawAreaShapeWithColor = new DrawArea.ShapeWithColor(Objects.requireNonNullElse(this.controller.loads.getMainColor(), Color.blue)
                                    , new Ellipse2D.Double(event.getX(), event.getY(), this.thickness, this.thickness), this.thickness + 2);
        drawAreaShapeWithColor.setMouseReleased();
        this.controller.changes.drawAreas[this.indexOfDrawArea].collectShapeToDrawPointCoordinates(drawAreaShapeWithColor);
    }

    @Override
    public void mouseClicked(MouseEvent event)
    {

    }

}