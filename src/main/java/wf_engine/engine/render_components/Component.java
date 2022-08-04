package wf_engine.engine.render_components;

public class Component {

    private double x;
    private double y;

    public Component(double x, double y) {
        this.x = x;
        this.y = y;
    }




    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
