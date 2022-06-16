package game.binds;

import game.borders.Borders;
import wf_engine.listeners.MouseDraggedListener;
import wf_engine.listeners.MousePressedListener;
import wf_engine.utils.PosUtils;
import wf_engine.utils_models.Point;

import java.awt.event.MouseEvent;

public class MouseBinds implements MousePressedListener, MouseDraggedListener {

    public static Point motion_point;


    public void mouseDragged(MouseEvent e){
        motion_point.x = e.getX();
        motion_point.y = e.getY();
    }


    public void mousePressed(MouseEvent e) {
        motion_point = Borders.points[PosUtils.getNearestPointId(Borders.points, new Point(e.getX(), e.getY()))];
    }

//    public static void Released(MouseEvent e) {
//        motion_point.x = e.getX();
//        motion_point.y = e.getY();
//    }



}
