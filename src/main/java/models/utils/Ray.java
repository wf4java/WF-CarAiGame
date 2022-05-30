package models.utils;

import utils.PosUtils;

import java.awt.*;

public class Ray extends Line{

    public double distance;
    public boolean intersect = false;
    public Point intersectPoint;
    public double angle;


    public Ray(Point p1, Point p2, double angle) {
        super(p1, p2);
        this.angle = angle;
    }


    public void calculate(Line[] lines){
        for (Line line : lines) {
            if(PosUtils.intersectsLines(line, this)){
                intersect = true;
                intersectPoint = PosUtils.crossingPoint(line, this);
                distance = PosUtils.getDistance(p1, intersectPoint);
                return;
            }
        }
        distance = -1;
        intersect = false;
    }

    public Line getCalculatedRay(){
        return new Line(new Point(p1.x, p1.y), PosUtils.far(p1, distance, angle));
    }



}
