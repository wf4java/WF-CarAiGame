package models.render;


import game.components.GameObjects;
import models.utils.Line;
import models.utils.Point;
import models.utils.Ray;
import utils.PosUtils;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class CarRender extends ImageRender{

    public String name;
    public double speed;
    public double sensitivity;
    public double far = 100;
    public LineRender[] lines = new LineRender[4];
    public RayRender[] ai_lines = new RayRender[8];
    public boolean rayFullRender;
    public int rayWidth = 3;
    public Color rayColor = Color.WHITE;
    public boolean inGame = true;
    public Point spawnPoint;
    public double spawnRotation;
    public long ticks;




    public CarRender(String name, double speed, double sensitivity, ImageRender ri, boolean rayFullRender){
        super(ri.image, ri.x, ri.y, ri.width, ri.height, ri.rotate);
        this.name = name;
        this.speed = speed;
        this.sensitivity = sensitivity;
        spawnPoint = new Point(ri.x, ri.y);
        spawnRotation = ri.rotate;
        this.rayFullRender = rayFullRender;
    }

    public void setLines(int lineWidth){

        AffineTransform af = AffineTransform.getTranslateInstance(x, y);
        af.rotate(Math.toRadians(rotate), width / 2, height / 2);

        Point p1 = new Point(af.getTranslateX(), (int) af.getTranslateY());
        Point p2 =  new Point(PosUtils.addX(af.getTranslateX(), 0, height, rotate), (int) PosUtils.addY(af.getTranslateY(), 0, height, rotate));
        Point p3 =  new Point(PosUtils.addX(af.getTranslateX(), width, height, rotate), (int) PosUtils.addY(af.getTranslateY(), width, height, rotate));
        Point p4 =  new Point(PosUtils.addX(af.getTranslateX(), width, 0, rotate), (int) PosUtils.addY(af.getTranslateY(), width, 0, rotate));

        lines[0] = new LineRender(new Line(p1, p2), Color.blue, lineWidth);
        lines[1] = new LineRender(new Line(p2, p3), Color.blue, lineWidth);
        lines[2] = new LineRender(new Line(p3, p4), Color.blue, lineWidth);
        lines[3] = new LineRender(new Line(p4, p1), Color.blue, lineWidth);

    }


    public void setAi_lines(Line[] lines){

        Point p1 = new Point(x + width / 2, y + height / 2);
        ai_lines[0] = new RayRender(new Ray(p1, PosUtils.far(p1, far, rotate + 180),rotate + 180), rayColor, rayWidth, rayFullRender);
        ai_lines[1] = new RayRender(new Ray(p1, PosUtils.far(p1, far, rotate + 0),rotate + 0), rayColor, rayWidth, rayFullRender);
        ai_lines[2] = new RayRender(new Ray(p1, PosUtils.far(p1, far, rotate + 90),rotate + 90), rayColor, rayWidth, rayFullRender);
        ai_lines[3] = new RayRender(new Ray(p1, PosUtils.far(p1, far, rotate + 270),rotate + 270), rayColor, rayWidth, rayFullRender);
        ai_lines[4] = new RayRender(new Ray(p1, PosUtils.far(p1, far, rotate + 45),rotate + 45), rayColor, rayWidth, rayFullRender);
        ai_lines[5] = new RayRender(new Ray(p1, PosUtils.far(p1, far, rotate + 135),rotate + 135), rayColor, rayWidth, rayFullRender);
        ai_lines[6] = new RayRender(new Ray(p1, PosUtils.far(p1, far, rotate + 225),rotate + 225), rayColor, rayWidth, rayFullRender);
        ai_lines[7] = new RayRender(new Ray(p1, PosUtils.far(p1, far, rotate + 315),rotate + 315), rayColor, rayWidth, rayFullRender);

        for (RayRender ai_line : ai_lines) ai_line.ray.calculate(lines);

    }


    public void moveForward(){
        x = (x - (Math.cos(Math.toRadians(rotate)) * speed));
        y = (y - (Math.sin(Math.toRadians(rotate)) * speed));
    }




    public void moveBack(){
        x = (x + (Math.cos(Math.toRadians(rotate)) * speed));
        y = (y + (Math.sin(Math.toRadians(rotate)) * speed));
    }

    public void rotateRight(double ins){
        rotate += sensitivity * ins;
    }

    public void rotateLeft(double ins){
        rotate -= sensitivity * ins;
    }

    public void move(boolean[] keys){
        if(keys[0]) {
            x = (x - (Math.cos(Math.toRadians(rotate)) * speed));
            y = (y - (Math.sin(Math.toRadians(rotate)) * speed));
        }

        if(keys[1]){
            x = (x + (Math.cos(Math.toRadians(rotate)) * speed));
            y = (y + (Math.sin(Math.toRadians(rotate)) * speed));
        }

        if(keys[2]){
            rotate += sensitivity;
        }

        if(keys[3]){
            rotate += sensitivity;
        }
    }

    public boolean isCollision(Line[] lines){
        for(LineRender lr : this.lines) for(Line l : GameObjects.borders) if(PosUtils.intersectsLines(lr.line, l)) return true;
        return false;
    }

    public void respawn(){
        this.x = spawnPoint.x;
        this.y = spawnPoint.y;
        this.rotate = spawnRotation;
        this.inGame = true;
        ticks = 0;
    }

    public void respawn(Point p, double angle){
        this.x = p.x;
        this.y = p.y;
        this.rotate = angle;
        this.inGame = true;
    }


    public double[] getData(){
        double[] data = new double[ai_lines.length];

        for (int i = 0; i < ai_lines.length; i++) {
            data[i] = ai_lines[i].ray.distance / far;
            if(ai_lines[i].ray.distance == -1) data[i] = 1;
        }

        return data;
    }


}
