package wf_engine.engine.render_components.utils_models;


import wf_engine.engine.utils.PosUtils;

public class Ray extends Line{

    private double distance;
    private boolean intersect = false;
    private Point intersectPoint;
    private double angle;


    public Ray(Point p1, Point p2, double angle) {
        super(p1, p2);
        this.angle = angle;
    }


    public void calculate(Line[] lines){
        for (Line line : lines) {
            if(PosUtils.intersectsLines(line, this)){
                intersect = true;
                intersectPoint = PosUtils.crossingPoint(line, this);
                distance = PosUtils.getDistance(getP1(), intersectPoint);
                return;
            }
        }
        distance = -1;
        intersect = false;
    }

    public Line getCalculatedRay(){
        return new Line(new Point(getP1().getX(), getP1().getY()), PosUtils.far(getP1(), distance, angle));
    }


    @Override
    public String toString() {
        return "Ray{" +
                "distance=" + distance +
                ", intersect=" + intersect +
                ", intersectPoint=" + intersectPoint +
                ", angle=" + angle +
                '}';
    }


    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isIntersect() {
        return intersect;
    }

    public void setIntersect(boolean intersect) {
        this.intersect = intersect;
    }

    public Point getIntersectPoint() {
        return intersectPoint;
    }

    public void setIntersectPoint(Point intersectPoint) {
        this.intersectPoint = intersectPoint;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
