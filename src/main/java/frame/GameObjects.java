package frame;

import models.ai.CarPerson;
import models.render.*;
import models.utils.Line;
import road.Borders;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GameObjects {

    public static ArrayList<RenderComponent> components = new ArrayList<>();
    public static ArrayList<Line> borders = new ArrayList<>();
    public static ArrayList<CarPerson> persons = new ArrayList<>();

    public static NNRender nnRender;


    public static void load() throws IOException {

        components.add(new RenderComponent(new ImageRender(ImageIO.read(Game.class.getClassLoader().getResource("images/border.png")), 0, 0,1200, 780)));

        borders.addAll(Arrays.asList(Borders.init()));
        for (Line line : borders) components.add(new RenderComponent(new LineRender(line, Color.red, 5)));

        BufferedImage[] bi = new BufferedImage[4];
        for (int i = 1; i < 5; i++) {
            bi[i - 1] = ImageIO.read(Game.class.getClassLoader().getResource("images/car_" + i +".png"));
        }

        for(int i = 0; i < 20; i++){
            CarRender car = new CarRender("car-" + i,6,4, new ImageRender(bi[((i % 4))], 350, 108, 180));
            persons.add(new CarPerson(car,8,1,4,1));
            components.add(new RenderComponent(car));
        }

        GameObjects.nnRender = new NNRender(GameObjects.persons.get(0).nn.getWeights(), 520, 500, 25, 380, 208);
        GameObjects.components.add(new RenderComponent(GameObjects.nnRender));

    }


}
