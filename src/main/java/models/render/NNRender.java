package models.render;

import models.utils.Point;
import org.ejml.simple.SimpleMatrix;

public class NNRender {

    public SimpleMatrix[] weights;

    public Point[][] points;
    public double width;
    public double height;
    public double max;
    public double size;
    public double x;
    public double y;


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

}
