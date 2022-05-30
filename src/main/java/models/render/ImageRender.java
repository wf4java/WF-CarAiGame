package models.render;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageRender {

    public BufferedImage image;
    public double x;
    public double y;
    public double rotate;
    public int width;
    public int height;



    public ImageRender(BufferedImage image, double x, double y, int width, int height, double rotate){
        this.image = resize(image, width, height);
        this.x = x;
        this.y = y;
        this.rotate = rotate;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }


    public ImageRender(BufferedImage image, double x, double y){
        this.image = image;
        this.x = x;
        this.y = y;
        this.rotate = 0;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public ImageRender(BufferedImage image, double x, double y, double rotate){
        this.image = image;
        this.x = x;
        this.y = y;
        this.rotate = rotate;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public ImageRender(BufferedImage image, double x, double y, int width, int height){
        this.image = resize(image, width, height);
        this.x = x;
        this.y = y;
        this.rotate = 0;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public ImageRender(BufferedImage image, double rotate){
        this.image = image;
        this.x = 0;
        this.y = 0;
        this.rotate = rotate;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }




    public void resize(int width, int height) {
        Image tmp = this.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage new_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = new_img.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        this.image = new_img;
    }

    public static BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage new_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = new_img.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return new_img;
    }

}
