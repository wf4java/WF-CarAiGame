package game.binds;

import game.borders.Borders;
import wf_engine.utils.PosUtils;
import wf_engine.utils_models.Point;

import java.awt.event.MouseEvent;

public class MouseBinds {

    public static Point motion_point;


    public static void dragged(MouseEvent e){
        motion_point.x = e.getX();
        motion_point.y = e.getY();
    }

    public static void Clicked(MouseEvent e) {

    }

    public static void Pressed(MouseEvent e) {
        motion_point = Borders.points[PosUtils.getNearestPointId(Borders.points, new Point(e.getX(), e.getY()))];
    }

    public static void Released(MouseEvent e) {
//        motion_point.x = e.getX();
//        motion_point.y = e.getY();
    }

    public static void Entered(MouseEvent e) {

    }

    public static void Exited(MouseEvent e) {

    }

}
