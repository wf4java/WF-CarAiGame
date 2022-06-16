package wf_engine.render_components;


import wf_engine.listeners.ComponentRender;
import wf_engine.utils_models.Line;
import wf_engine.utils_models.Ray;

import java.awt.*;

public class RayRender implements ComponentRender {

    public Ray ray;
    public Color color = Color.white;
    public int width = 2;
    public boolean fullRender = true;
    public boolean circleRender = true;
    public Color circleColor = Color.black;

    public RayRender(Ray ray){
        this.ray = ray;
    }

    public RayRender(Ray ray, Color color){
        this.ray = ray;
        this.color = color;
    }

    public RayRender(Ray ray, Color color, int width){
        this.ray = ray;
        this.color = color;
        this.width = width;
    }

    public RayRender(Ray ray, Color color, boolean fullRender){
        this.ray = ray;
        this.color = color;
        this.fullRender = fullRender;
    }

    public RayRender(Ray ray, Color color, int width, boolean fullRender){
        this.ray = ray;
        this.color = color;
        this.width = width;
        this.fullRender = fullRender;
    }

    public RayRender(Ray ray, Color color, int width, boolean fullRender, boolean circleRender){
        this.ray = ray;
        this.color = color;
        this.width = width;
        this.fullRender = fullRender;
        this.circleRender = circleRender;
    }

    public RayRender(Ray ray, Color color, int width, boolean fullRender, boolean circleRender, Color circleColor){
        this.ray = ray;
        this.color = color;
        this.width = width;
        this.fullRender = fullRender;
        this.circleRender = circleRender;
        this.circleColor = circleColor;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g.setColor(color);
        g2.setStroke(new BasicStroke(width));

        if (fullRender) {
            g.drawLine((int) ray.p1.x,(int) ray.p1.y,(int) ray.p2.x,(int) ray.p2.y);
        }else if(ray.intersect){
            Line drawLine = ray.getCalculatedRay();
            g.drawLine((int) drawLine.p1.x, (int) drawLine.p1.y, (int) drawLine.p2.x, (int) drawLine.p2.y);
        }

        if(ray.intersect && ray.intersectPoint != null){
            g.drawString(String.valueOf(Math.round(ray.distance)), (int) (ray.intersectPoint.x),
                    (int) (ray.intersectPoint.y + 20));
            g.setColor(circleColor);
            g.drawOval((int) ray.intersectPoint.x - 3, (int) ray.intersectPoint.y - 3, 5, 5);
        }
    }
}
