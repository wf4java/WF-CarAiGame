package wf_engine.engine.render_components;

import wf_engine.engine.interfaces.ComponentRender;

import java.awt.*;

public class TextRender extends Component implements ComponentRender {

    private String text;
    private float fontSize;
    private Color color;


    public TextRender(String text, double x, double y, float fontSize, Color color){
        super(x, y);
        this.text = text;
        this.fontSize = fontSize;
        this.color = color;
    }

    public TextRender(String text, double x, double y, float fontSize){
        super(x, y);
        this.text = text;
        this.fontSize = fontSize;
        this.color = Color.WHITE;
    }

    public TextRender(String text, double x, double y, Color color){
        super(x, y);
        this.text = text;
        this.fontSize = 14;
        this.color = color;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.setFont(g.getFont().deriveFont(fontSize));
        g.drawString(text, (int) getX(), (int) getY());
    }






    @Override
    public String
    toString() {
        return "TextRender{" +
                "text='" + text + '\'' +
                ", fontSize=" + fontSize +
                ", color=" + color +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public float getFontSize() {
        return fontSize;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
