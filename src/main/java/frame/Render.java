package frame;

import models.render.*;
import models.utils.Line;
import models.utils.Point;
import models.utils.Ray;
import utils.PosUtils;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Render {


    public static boolean rayRender = true;


    public static void renderImage(Graphics g, ImageRender component){
        AffineTransform af = AffineTransform.getTranslateInstance(component.x, component.y);
        af.rotate(Math.toRadians(component.rotate), component.image.getWidth() / 2, component.image.getHeight() / 2);
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.drawImage(component.image, af, null);
    }

    public static void renderCar(Graphics g, CarRender component){
        if(!component.inGame) return;
        if(component.ai_lines[0] != null) for (RayRender ray : component.ai_lines) renderRay(g, ray);
        AffineTransform af = AffineTransform.getTranslateInstance(component.x, component.y);
        af.rotate(Math.toRadians(component.rotate), component.image.getWidth() / 2, component.image.getHeight() / 2);
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.drawImage(component.image, af, null);
        if(component.lines[0] != null) for(LineRender line : component.lines) renderLine(g, line);
    }

    public static void renderLine(Graphics g, LineRender component){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(component.width));
        g.setColor(component.color);
        g.drawLine((int) component.line.p1.x,(int) component.line.p1.y,(int) component.line.p2.x,(int) component.line.p2.y);
    }

    public static void renderRay(Graphics g, RayRender component){
        if(!rayRender) return;
        Graphics2D g2 = (Graphics2D) g;
        if (component.fullRender) {
            g.setColor(component.color);
            g2.setStroke(new BasicStroke(component.width));
            g.drawLine((int) component.ray.p1.x,(int) component.ray.p1.y,(int) component.ray.p2.x,(int) component.ray.p2.y);
            if(component.ray.intersect){
                g.drawString(String.valueOf(Math.round(component.ray.distance)), (int) (component.ray.intersectPoint.x),
                        (int) (component.ray.intersectPoint.y + 20));
                g.setColor(component.circleColor);
                g.drawOval((int) component.ray.intersectPoint.x - 3, (int) component.ray.intersectPoint.y - 3, 5, 5);
            }
        }else{
            try {
                if (component.ray.intersect) {
                    g.setColor(component.color);
                    g2.setStroke(new BasicStroke(component.width));
                    Line drawLine = component.ray.getCalculatedRay();
                    g.drawLine((int) drawLine.p1.x, (int) drawLine.p1.y, (int) drawLine.p2.x, (int) drawLine.p2.y);
                    g.drawString(String.valueOf(Math.round(component.ray.distance)), (int) (component.ray.intersectPoint.x),
                            (int) (component.ray.intersectPoint.y + 20));
                    g.setColor(component.circleColor);
                    g.drawOval((int) component.ray.intersectPoint.x - 3, (int) component.ray.intersectPoint.y - 3, 5, 5);
                }
            }catch (Exception e) {}
        }
    }

    public static void renderNN(Graphics g, NNRender component){
        Graphics2D g2 = (Graphics2D) g;
        if(component.points == null) return;

        try {


            for (int i = 0; i < component.points.length; i++) {
                for (int j = 0; j < component.points[i].length; j++) {
                    Point p = component.points[i][j];
                    if (i != component.points.length - 1) {
                        for (int d = 0; d < component.points[i + 1].length; d++) {
                            Point p2 = component.points[i + 1][d];
                            double weight = component.weights[i].get(d, j);

                            g2.setStroke(new BasicStroke(1));
                            if (weight > 0) g.setColor(Color.red);
                            else g.setColor(Color.green);


                            g2.setStroke(new BasicStroke(Math.round((1/( 1 + Math.pow(Math.E,(-1*Math.abs(weight))))) * 4)));
                            g.drawLine((int) (p.x + component.size / 2 + component.x), (int) (p.y + component.size / 2 + component.y), (int)
                                    (p2.x + component.size / 2 + component.x), (int) (p2.y + component.size / 2 + component.y));
                        }
                    }
                    g2.setStroke(new BasicStroke((int) component.size));
                    g.setColor(Color.blue);
                    g.drawOval((int) (p.x + component.x), (int) (p.y + component.y), (int) component.size, (int) component.size);
                }
            }
        }catch (Exception e) {}
    }


}
