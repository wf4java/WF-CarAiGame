package game.components;

import game.Config;
import game.frame.Game;
import models.ai.CarPerson;
import models.render.*;
import models.utils.Line;

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
    public static TextRender physicTimeText;
    public static TextRender renderTimeText;
    public static TextRender geneticText;
    public static TextRender carsLeftText;



    public static void load() throws IOException {

        components.add(new RenderComponent(new ImageRender(ImageIO.read(Game.class.getResource("/resources/images/border.png")), 0, 0,1200, 780)));

        borders.addAll(Arrays.asList(Borders.init()));
        for (Line line : borders) components.add(new RenderComponent(new LineRender(line, Color.red, Config.bordersWidth)));


        BufferedImage[] bi = new BufferedImage[4];
        for (int i = 1; i < 5; i++) bi[i - 1] = ImageIO.read(Game.class.getResource("/resources/images/car_" + i +".png"));


        for(int i = 0; i < Config.carCount; i++){
            CarRender car = new CarRender("car-" + i,6,4, new ImageRender(bi[((i % 4))], 350, 108, 180), Config.rayFullRender);
            persons.add(new CarPerson(car,8, Config.nnHiddenLayers, Config.nnHiddenNeurons, Config.nnOutputNeurons));
            components.add(new RenderComponent(car));
        }

        GameObjects.nnRender = new NNRender(GameObjects.persons.get(0).nn.getWeights(), 520, 500, 15, 380, 208);
        GameObjects.components.add(new RenderComponent(GameObjects.nnRender));

        physicTimeText = new TextRender("1", 10, 20, 15, Color.black);
        renderTimeText = new TextRender("1", 10, 40, 15, Color.black);
        geneticText = new TextRender("1", 10, 70, 20, Color.black);
        carsLeftText = new TextRender("1", 10, 100, 20, Color.black);

        components.add(new RenderComponent(physicTimeText));
        components.add(new RenderComponent(renderTimeText));
        components.add(new RenderComponent(geneticText));
        components.add(new RenderComponent(carsLeftText));

    }


}
