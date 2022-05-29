package models.render;

import models.utils.Ray;

import java.awt.*;

public class RayRender {

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

}
