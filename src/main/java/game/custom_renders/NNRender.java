package game.custom_renders;


import org.ejml.simple.SimpleMatrix;
import wf_engine.listeners.ComponentRender;
import wf_engine.utils_models.Point;

import java.awt.*;

public class NNRender implements ComponentRender {

    public SimpleMatrix[] weights;

    public Point[][] points;
    public double width;
    public double height;
    public double max;
    public double size;
    public double x;
    public double y;
    public double nnRenderMin = 0;


    public NNRender(SimpleMatrix[] weights, double width, double height, double size, double x, double y){
        this.weights = weights;
        this.width = width;
        this.height = height;
        this.size = size;
        this.x = x;
        this.y = y;

        for (int i = 0; i < weights.length; i++) {
            max = weights[i].numRows() > max ? weights[i].numRows() : max;
            max = weights[i].numCols() > max ? weights[i].numCols() : max;
        }
    }


    public void calculate(){
        points = new Point[weights.length + 1][];
        for (int i = 0; i < weights.length; i++){
            points[i] = new Point[weights[i].numCols()];
            if(i == weights.length - 1) points[i + 1] = new Point[weights[i].numRows()];
        }

        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].numCols(); j++) {
                points[i][j] = new Point(((width) / weights.length) * i, (((height * (weights[i].numCols() / max))
                        - (weights[i].numCols() * 5)) / weights[i].numCols()) * j + ((height - (height * (weights[i].numCols() / max))) / 2));

            }
            if(i == (weights.length - 1)){
                for (int j = 0; j < weights[i].numRows(); j++) {
                    points[i + 1][j] = new Point(width, (((height * (weights[i].numRows() / max))
                            - (weights[i].numRows() * 5)) / weights[i].numRows()) * j + ((height - (height * (weights[i].numRows() / max))) / 2));
                }
            }
        }


    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        try {
            for (int i = 0; i < points.length; i++) {
                for (int j = 0; j < points[i].length; j++) {
                    Point p = points[i][j];
                    if (i != points.length - 1) {
                        for (int d = 0; d < points[i + 1].length; d++) {
                            Point p2 = points[i + 1][d];
                            double weight = weights[i].get(d, j);
                            double colorCoif = Math.min(Math.abs(weight) * 50D, 255);
                            if(nnRenderMin > 0.05) colorCoif = 255;

                            g2.setStroke(new BasicStroke(1));
                            if (weight > 0) g.setColor(new Color((int) colorCoif,0,0));
                            else g.setColor(new Color(0 ,(int) colorCoif,0));

                            if((weight < nnRenderMin && weight > -nnRenderMin) || nnRenderMin < 0.05) {
                                g2.setStroke(new BasicStroke(Math.round((1 / (1 + Math.pow(Math.E, (-1 * Math.abs(weight))))) * 4)));
                                g.drawLine((int) (p.x + size / 2 + x), (int) (p.y + size / 2 + y), (int) (p2.x + size / 2 + x), (int) (p2.y + size / 2 + y));
                            }
                        }
                    }
                    g2.setStroke(new BasicStroke((int) size));
                    g.setColor(Color.WHITE);
                    g.drawOval((int) (p.x + x), (int) (p.y + y), (int) size, (int) size);
                    g.setColor(Color.black);
                    Font font = g.getFont().deriveFont((float) size * 1.5f);

                    g.setFont(font);
                    g.drawString(String.valueOf(j + 1), (int) (p.x + x), (int) (p.y + y + size));
                }
            }
        }catch (Exception e) {}
    }
}
