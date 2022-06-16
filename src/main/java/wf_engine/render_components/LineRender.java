package wf_engine.render_components;


import wf_engine.listeners.ComponentRender;
import wf_engine.utils_models.Line;

import java.awt.*;

public class LineRender implements ComponentRender {

    public Line line;
    public Color color;
    public int width;

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
        g.drawLine((int) line.p1.x,(int) line.p1.y,(int) line.p2.x,(int) line.p2.y);
    }
}
