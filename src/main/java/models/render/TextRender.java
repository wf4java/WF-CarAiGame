package models.render;

import java.awt.*;

public class TextRender {

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

}
