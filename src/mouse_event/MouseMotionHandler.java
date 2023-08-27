package mouse_event;

import com.drawing_application.MVC_pattern.PaintController;
import com.drawing_application.MVC_pattern.front.components.DrawArea;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.Objects;

public class MouseMotionHandler
    implements MouseMotionListener
{
    PaintController controller;
    int indexOfDrawArea;

    int thickness;

    public MouseMotionHandler(int indexOfDrawArea)
    {
        this.indexOfDrawArea = indexOfDrawArea;
        controller = PaintController.getInstance();
    }


    @Override
    public void mouseDragged(MouseEvent e)
    {
        this.thickness = controller.loads.getThickness();
        this.controller.changes.drawAreas[this.indexOfDrawArea]
                .collectShapeToDrawPointCoordinates(new DrawArea.ShapeWithColor(Objects.requireNonNullElse(this.controller.loads.getMainColor(), Color.blue)
                        , new Ellipse2D.Double(e.getX(), e.getY(), this.thickness, this.thickness), this.thickness + 2));
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
