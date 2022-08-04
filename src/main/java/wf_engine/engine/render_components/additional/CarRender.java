package wf_engine.engine.render_components.additional;



import wf_engine.engine.interfaces.ComponentRender;
import wf_engine.engine.render_components.ImageRender;
import wf_engine.engine.render_components.LineRender;
import wf_engine.engine.render_components.RayRender;
import wf_engine.engine.utils.PosUtils;
import wf_engine.engine.render_components.utils_models.Line;
import wf_engine.engine.render_components.utils_models.Point;
import wf_engine.engine.render_components.utils_models.Ray;


import java.awt.*;
import java.awt.geom.AffineTransform;

public class CarRender extends ImageRender implements ComponentRender {

    public String name;
    public double speed;
    public double sensitivity;
    public double far;
    public LineRender[] lines = new LineRender[4];
    public RayRender[] ai_lines = new RayRender[8];
    public boolean rayFullRender;
    public boolean rayRender = true;
    public int rayWidth = 3;
    public Color rayColor = Color.WHITE;
    public boolean inGame = true;
    public Point spawnPoint;
    public double spawnRotation;
    public double ticks;




    public CarRender(String name, double speed, double sensitivity, ImageRender ri, boolean rayFullRender, int far){
        super(ri.getImage(), ri.getX(), ri.getY(), ri.getWidth(), ri.getHeight(), ri.getRotate());
        this.name = name;
        this.speed = speed;
        this.sensitivity = sensitivity;
        spawnPoint = new Point(ri.getX(), ri.getY());
        spawnRotation = ri.getRotate();
        this.rayFullRender = rayFullRender;
        this.far = far;
    }

    public void setLines(int lineWidth){

        AffineTransform af = AffineTransform.getTranslateInstance(getX(), getY());
        af.rotate(Math.toRadians(getRotate()), getWidth() / 2, getHeight() / 2);

        Point p1 = new Point(af.getTranslateX(), (int) af.getTranslateY());
        Point p2 =  new Point(PosUtils.addX(af.getTranslateX(), 0, getHeight(), getRotate()), (int) PosUtils.addY(af.getTranslateY(), 0, getHeight(), getRotate()));
        Point p3 =  new Point(PosUtils.addX(af.getTranslateX(), getWidth(), getHeight(), getRotate()), (int) PosUtils.addY(af.getTranslateY(), getWidth(), getHeight(), getRotate()));
        Point p4 =  new Point(PosUtils.addX(af.getTranslateX(), getWidth(), 0, getRotate()), (int) PosUtils.addY(af.getTranslateY(), getWidth(), 0, getRotate()));

        lines[0] = new LineRender(new Line(p1, p2), Color.blue, lineWidth);
        lines[1] = new LineRender(new Line(p2, p3), Color.blue, lineWidth);
        lines[2] = new LineRender(new Line(p3, p4), Color.blue, lineWidth);
        lines[3] = new LineRender(new Line(p4, p1), Color.blue, lineWidth);

    }


    public void setAi_lines(Line[] lines){

        Point p1 = new Point(getX()+ getWidth() / 2, getY() + getHeight() / 2);
        ai_lines[0] = new RayRender(new Ray(p1, PosUtils.far(p1, far, getRotate() + 180),getRotate() + 180), rayColor, rayWidth, rayFullRender);
        ai_lines[1] = new RayRender(new Ray(p1, PosUtils.far(p1, far, getRotate() + 0),getRotate() + 0), rayColor, rayWidth, rayFullRender);
        ai_lines[2] = new RayRender(new Ray(p1, PosUtils.far(p1, far, getRotate() + 90),getRotate() + 90), rayColor, rayWidth, rayFullRender);
        ai_lines[3] = new RayRender(new Ray(p1, PosUtils.far(p1, far, getRotate() + 270),getRotate() + 270), rayColor, rayWidth, rayFullRender);
        ai_lines[4] = new RayRender(new Ray(p1, PosUtils.far(p1, far, getRotate() + 45),getRotate() + 45), rayColor, rayWidth, rayFullRender);
        ai_lines[5] = new RayRender(new Ray(p1, PosUtils.far(p1, far, getRotate() + 135),getRotate() + 135), rayColor, rayWidth, rayFullRender);
        ai_lines[6] = new RayRender(new Ray(p1, PosUtils.far(p1, far, getRotate() + 225),getRotate() + 225), rayColor, rayWidth, rayFullRender);
        ai_lines[7] = new RayRender(new Ray(p1, PosUtils.far(p1, far, getRotate() + 315),getRotate() + 315), rayColor, rayWidth, rayFullRender);

        for (RayRender ai_line : ai_lines) ai_line.getRay().calculate(lines);

    }


    public void moveForward(){
        setX(getX() - (Math.cos(Math.toRadians(getRotate())) * speed));
        setY(getY() - (Math.sin(Math.toRadians(getRotate())) * speed));
    }




    public void moveBack(){
        setX(getX() + (Math.cos(Math.toRadians(getRotate())) * speed));
        setY(getY() + (Math.sin(Math.toRadians(getRotate())) * speed));
    }

    public void rotateRight(double ins){
        addRotate(sensitivity * ins);
    }

    public void rotateLeft(double ins){
        addRotate(-(sensitivity * ins));
    }

    public void move(boolean[] keys){
        if(keys[0]) {
            moveForward();
        }
        if(keys[1]){
            moveBack();
        }
        if(keys[2]){
            rotateLeft(1);
        }
        if(keys[3]){
            rotateRight(1);
        }
    }

    public boolean isCollision(Line[] lines){
        for(LineRender lr : this.lines) for(Line l : lines) if(PosUtils.intersectsLines(lr.getLine(), l)) return true;
        return false;
    }

    public void respawn(){
        setX(spawnPoint.getX());
        setY(spawnPoint.getY());
        setRotate(spawnRotation);
        this.inGame = true;
        ticks = 0;
    }

    public void respawn(Point p, double angle){
        setX(p.getX());
        setY(p.getY());
        setRotate(angle);
        this.inGame = true;
    }


    public double[] getData(){
        double[] data = new double[ai_lines.length];

        for (int i = 0; i < ai_lines.length; i++) {
            data[i] = ai_lines[i].getRay().getDistance() / far;
            if(ai_lines[i].getRay().getDistance() == -1) data[i] = 1;
        }

        return data;
    }



    @Override
    public void render(Graphics g) {
        if(!inGame) return;
        if(ai_lines[0] != null && rayRender) for (RayRender ray : ai_lines) ray.render(g);
        super.render(g);
        if(lines[0] != null) for(LineRender line : lines) line.render(g);
    }
}
