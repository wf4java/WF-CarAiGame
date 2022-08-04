package wf_engine.engine.render_components;

import wf_engine.engine.interfaces.ComponentRender;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class ImageRender extends Component implements ComponentRender {

    private BufferedImage image;
    private double rotate;
    private int width;
    private int height;



    public ImageRender(BufferedImage image, double x, double y, int width, int height, double rotate){
        super(x, y);
        this.image = resize(image, width, height);
        this.rotate = rotate;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }


    public ImageRender(BufferedImage image, double x, double y){
        super(x, y);
        this.image = image;
        this.rotate = 0;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public ImageRender(BufferedImage image, double x, double y, double rotate){
        super(x, y);
        this.image = image;
        this.rotate = rotate;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public ImageRender(BufferedImage image, double x, double y, int width, int height){
        super(x, y);
        this.image = resize(image, width, height);
        this.rotate = 0;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public ImageRender(BufferedImage image, double rotate){
        super(0,0);
        this.image = image;
        this.rotate = rotate;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public ImageRender(BufferedImage image){
        super(0,0);
        this.image = image;
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

    @Override
    public void render(Graphics g) {
        AffineTransform af = AffineTransform.getTranslateInstance(getX(), getY());
        af.rotate(Math.toRadians(rotate), image.getWidth() / 2, image.getHeight() / 2);
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.drawImage(image, af, null);
    }


    @Override
    public String toString() {
        return "ImageRender{" +
                "image=" + image +
                ", rotate=" + rotate +
                ", width=" + width +
                ", height=" + height +
                '}';
    }



    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public double getRotate() {
        return rotate;
    }

    public double addRotate(double add) {
        return rotate += add;
    }

    public void setRotate(double rotate) {
        this.rotate = rotate;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
