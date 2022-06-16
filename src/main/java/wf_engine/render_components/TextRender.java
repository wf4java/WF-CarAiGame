package wf_engine.render_components;

import wf_engine.listeners.ComponentRender;

import java.awt.*;

public class TextRender implements ComponentRender {

    public String text;
    public double x;
    public double y;
    public float fontSize;
    public Color color;


    public TextRender(String text, double x, double y, float fontSize, Color color){
        this.text = text;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
        this.color = color;
    }

    public TextRender(String text, double x, double y, float fontSize){
        this.text = text;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
        this.color = Color.WHITE;
    }

    public TextRender(String text, double x, double y, Color color){
        this.text = text;
        this.x = x;
        this.y = y;
        this.fontSize = 14;
        this.color = color;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.setFont(g.getFont().deriveFont(fontSize));
        g.drawString(text, (int) x, (int) y);
    }
}
