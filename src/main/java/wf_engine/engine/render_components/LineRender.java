package wf_engine.engine.render_components;


import wf_engine.engine.interfaces.ComponentRender;
import wf_engine.engine.render_components.utils_models.Line;

import java.awt.*;

public class LineRender implements ComponentRender {

    private Line line;
    private Color color;
    private int width;

    public LineRender(Line line, Color color, int width){
        this.line = line;
        this.color = color;
        this.width = width;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(width));
        g.setColor(color);
        g.drawLine((int) line.getP1().getX(),(int) line.getP1().getY(),(int) line.getP2().getX(),(int) line.getP2().getY());
    }

    @Override
    public String toString() {
        return "LineRender{" +
                "line=" + line +
                ", color=" + color +
                ", width=" + width +
                '}';
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
