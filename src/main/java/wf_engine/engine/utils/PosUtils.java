package wf_engine.engine.utils;


import wf_engine.engine.render_components.utils_models.Line;
import wf_engine.engine.render_components.utils_models.Point;

import java.awt.geom.Line2D;

public class PosUtils {

    public static double addX(double oldX, double w, double h, double angle){
        return (oldX + (w * cos(angle) - (h * sin(angle))));
    }

    public static double addY(double oldY,  double w, double h, double angle){
        return (oldY + (w * sin(angle) + (h * cos(angle))));
    }

    public static Point far(Point p1, double far, double angle){
        return new Point(p1.getX() + cos(angle) * far, p1.getY() + sin(angle) * far);
    }


    public static double sin(double angle){
        return Math.sin(Math.toRadians(angle));
    }

    public static double cos(double angle){
        return Math.cos(Math.toRadians(angle));
    }

    public static Point positionAngle(double oldX, double oldY, double w, double h, double angle){
        return new Point(addX(oldX, w, h ,angle), addY(oldY, w, h ,angle));
    }


    public static Point crossingPoint(Line l1, Line l2) {
        double x11 = l1.getP1().getX();
        double y11 = l1.getP1().getY();
        double x12 = l1.getP2().getX();
        double y12 = l1.getP2().getY();

        double x21 = l2.getP1().getX();
        double y21 = l2.getP1().getY();
        double x22 = l2.getP2().getX();
        double y22 = l2.getP2().getY();

        if (x11 == x12 && x21 == x22) return null;
         else if (x11 == x12 || x21 == x22) {
            double x;
            double m;
            double b;
            if (x11 == x12) {
                x = x11;
                m = (y22 - y21) / (x22 - x21);
                b = (x22 * y21 - x21 * y22) / (x22 - x21);
            } else {
                x = x21;
                m = (y12 - y11) / (x12 - x11);
                b = (x12 * y11 - x11 * y12) / (x12 - x11);
            }
            double y = m * x + b;
            return new Point(x, y);
        } else {
            double m1 = (y12 - y11) / (x12 - x11);
            double b1 = (x12 * y11 - x11 * y12) / (x12 - x11);
            double m2 = (y22 - y21) / (x22 - x21);
            double b2 = (x22 * y21 - x21 * y22) / (x22 - x21);
            if (m1 == m2) return null;
            double x = (b2 - b1)/(m1 - m2);
            double y = m1 * x + b1;  // or m2 * x + b2
            return new Point(x, y);
        }
    }

    public static boolean intersectsLines(Line l1, Line l2){
        return Line2D.linesIntersect(l1.getP1().getX(),l1.getP1().getY(), l1.getP2().getX(), l1.getP2().getY(),
                l2.getP1().getX(), l2.getP1().getY(), l2.getP2().getX(), l2.getP2().getY());
    }


    public static double getDistance(Point p1, Point p2) {
        return Math.sqrt((p2.getY() - p1.getY()) * (p2.getY() - p1.getY()) + (p2.getX() - p1.getX()) * (p2.getX() - p1.getX()));
    }

    public static int getNearestPointId(Point[] points, Point p){
        int id = 0;
        double dist = getDistance(points[0], p);
        for (int i = 1; i < points.length; i++) {
            double d = getDistance(points[i], p);
            if(d < dist) { id = i; dist = d; }
        }
        return id;
    }


}
