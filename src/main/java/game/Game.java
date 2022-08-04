package game;

import game.ai.CarPerson;
import game.binds.KeyBinds;
import game.binds.MouseBinds;
import wf_engine.engine.render_components.additional.CarRender;
import wf_engine.engine.render_components.additional.NNRender;
import game.borders.Borders;
import game.main.Main;
import game.physic.Physic;
import game.render.PreRender;
import wf_engine.engine.WFgame;
import wf_engine.engine.render_components.ImageRender;
import wf_engine.engine.render_components.LineRender;
import wf_engine.engine.render_components.TextRender;
import wf_engine.engine.render_components.utils_models.Line;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public static WFgame game;

    public static List<Line> borders = new ArrayList<>();
    public static List<CarPerson> persons = new ArrayList<>();

    public static NNRender nnRender;
    public static TextRender physicTimeText;
    public static TextRender renderTimeText;
    public static TextRender geneticText;
    public static TextRender carsLeftText;
    public static TextRender bestResultText;


    public static void build(){
        game = new WFgame("CarGame", 1215,818);
        try {loadObjects();} catch (IOException e) {game.stop(); throw new RuntimeException(e);}

        game.addComponent(new Physic());
        game.addComponent(new PreRender());
        game.addComponent(new PreRender());
        game.addComponent(new PreRender());
        game.addKeyPressedListener(new KeyBinds());
        game.addMousePressedListener(new MouseBinds());
        game.addMouseDraggedListener(new MouseBinds());
    }


    public static void loadObjects() throws IOException {
        game.addComponent(new ImageRender(ImageIO.read(Main.class.getResource("/resources/images/border.png")), 0, 0,1200, 780));

        borders.addAll(List.of(Borders.build()));
        for (Line line : borders) game.addComponent((new LineRender(line, Color.red, Config.bordersWidth)));


        BufferedImage[] bi = new BufferedImage[4];
        for (int i = 1; i < 5; i++) bi[i - 1] = ImageIO.read(Main.class.getResource("/resources/images/car_" + i +".png"));


        for(int i = 0; i < Config.carCount; i++){
            CarRender car = new CarRender("car-" + i,Config.speed, Config.sensitive, new ImageRender(bi[((i % 4))], 350, 108, 180), Config.rayFullRender, Config.far);
            persons.add(new CarPerson(car, Config.nnInputNeurons, Config.nnHiddenNeurons, Config.nnOutputNeurons));
            game.addComponent(car);
        }

        nnRender = new NNRender(380, 208, persons.get(0).nn.getWeights(), 520, 500, 15);
        game.addComponent(nnRender);

        renderTimeText = new TextRender("", 10, 20, 15, Color.black);
        physicTimeText = new TextRender("", 10, 40, 15, Color.black);
        geneticText = new TextRender("", 10, 70, 20, Color.black);
        carsLeftText = new TextRender("", 10, 100, 20, Color.black);
        bestResultText = new TextRender("", 10, 130, 20, Color.black);

        //game.addComponent(new ImageRender(ImageIO.read(Main.class.getResource("/resources/images/boom.png")), 371, 192,898 - 371, 638 - 192));

        game.addComponent(physicTimeText);
        game.addComponent(renderTimeText);
        game.addComponent(geneticText);
        game.addComponent(carsLeftText);
        game.addComponent(bestResultText);
    }

}
