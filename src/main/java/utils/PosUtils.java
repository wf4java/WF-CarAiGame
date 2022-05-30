package utils;

import models.utils.Line;
import models.utils.Point;

import java.awt.geom.Line2D;

public class PosUtils {

    public static double addX(double oldX, double w, double h, double angle){
        return (oldX + (w * cos(angle) - (h * sin(angle))));
    }

    public static double addY(double oldY,  double w, double h, double angle){
        return (oldY + (w * sin(angle) + (h * cos(angle))));
    }

    public static Point far(Point p1, double far, double angle){
        return new Point(p1.x + cos(angle) * far, p1.y + sin(angle) * far);
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
        double x11 = l1.p1.x;
        double y11 = l1.p1.y;
        double x12 = l1.p2.x;
        double y12 = l1.p2.y;

        double x21 = l2.p1.x;
        double y21 = l2.p1.y;
        double x22 = l2.p2.x;
        double y22 = l2.p2.y;

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
        return Line2D.linesIntersect(l1.p1.x,l1.p1.y, l1.p2.x, l1.p2.y, l2.p1.x, l2.p1.y, l2.p2.x, l2.p2.y);
    }


    public static double getDistance(Point p1, Point p2) {
        return Math.sqrt((p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p1.x) * (p2.x - p1.x));
    }


}
