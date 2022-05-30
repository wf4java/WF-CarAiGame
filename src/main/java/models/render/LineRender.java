package models.render;

import models.utils.Line;

import java.awt.*;

public class LineRender {

    public Line line;
    public Color color;
    public int width;

    public LineRender(Line line, Color color, int width){
        this.line = line;
        this.color = color;
        this.width = width;
    }

}
