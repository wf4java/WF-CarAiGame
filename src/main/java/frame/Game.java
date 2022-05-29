package frame;

import basicneuralnetwork.NeuralNetwork;
import models.ai.CarPerson;
import models.utils.Line;
import models.utils.Point;
import models.render.*;
import road.Borders;
import utils.CarUtils;
import utils.PosUtils;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Game extends JPanel{


    public static JFrame frame;

    public static int FPS = (1000) / 100;
    public static int PHYSIC_TICKS = (1000) / 85;

    public static double time;

    public static NeuralNetwork best;
    public static long bestResult;
    public static int genetics = 0;
    public static int carsCount = 0;


    public static ArrayList<Integer> keys = new ArrayList<>();


    public Game(){
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) { //when a key is released
                Game.keyReleased(e);
            }
            public void keyPressed(KeyEvent e) { //when a key is pressed
                Game.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_R){
                    Render.rayRender = Render.rayRender ? false : true;
                }

                if(e.getKeyCode() == KeyEvent.VK_K) {
                    best.writeToFile("best");
                }

            }
        });
        setFocusable(true);
    }
    

    public void paint(Graphics g){



        for (RenderComponent rc : GameObjects.components) {
            if(rc.componentType == ComponentType.LineRender) Render.renderLine(g, (LineRender) rc.get());
            else if(rc.componentType == ComponentType.RayRender) Render.renderRay(g, (RayRender) rc.get());
            else if(rc.componentType == ComponentType.CarRender) Render.renderCar(g, (CarRender) rc.get());
            else if(rc.componentType == ComponentType.ImageRender) Render.renderImage(g, (ImageRender) rc.get());
            else if(rc.componentType == ComponentType.NNRender) Render.renderNN(g, (NNRender) rc.get());
        }


        float fontSize = 25;
        Font font = g.getFont().deriveFont(fontSize);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString(String.valueOf(time), 20, 20);
        g.setColor(Color.WHITE);
        g.drawString("Genetics: " + genetics , 20, 50);
        g.drawString("Cars left: " + carsCount , 20, 80);
    }


    public static void physic(){

        if(keys.contains(KeyEvent.VK_W)) PHYSIC_TICKS = 0;
        else if(keys.contains(KeyEvent.VK_S)) PHYSIC_TICKS = (1000) / 4;
        else PHYSIC_TICKS = (1000) / 80;




        ArrayList<CarRender> cars = new ArrayList<>();
        for(RenderComponent rc : GameObjects.components) if(rc.componentType == ComponentType.CarRender) {
            cars.add((CarRender) rc.get());
        }


        if(keys.contains(KeyEvent.VK_H)) {
            cars.forEach(e -> e.inGame = false);
        }

        if(keys.contains(KeyEvent.VK_G)) {
            cars.forEach(e -> e.rot = e.rot ? false : true);
        }

        for(CarRender component : cars) if(component.inGame){
            component.setLines(2);
            component.setAi_lines(GameObjects.borders.toArray(new Line[0]));
            component.moveForward();
            component.ticks++;
        }




        for (CarPerson person : GameObjects.persons) {
            person.guess();
        }

        for(CarRender component : cars) {
            if(component.inGame) {
                for (LineRender lr : component.lines) {
                    for (Line l : GameObjects.borders) {
                        if (PosUtils.intersectsLines(lr.line, l)) {
                            component.inGame = false;
                        }
                    }
                }
            }
        }

//        for (CarPerson person : GameObjects.persons) {
//            if(person.car.inGame)
//                if(person.car.ticks == 500000) person.nn.writeToFile("good");
//        }


        carsCount = 0;
        for(CarRender component : cars) if(component.inGame) carsCount++;

        if(carsCount == 0){
            genetics++;
            for(CarRender component : cars) {
                CarPerson[] bestCars = CarUtils.getThoMaxCar(new ArrayList(GameObjects.persons));
                GameObjects.nnRender.calculate();
                GameObjects.nnRender.weights = bestCars[0].nn.getWeights();
                for(CarPerson car : GameObjects.persons){
                    double mutate = new Random().nextDouble() > 0.8 ? new Random().nextDouble() : 0.3;
                    if(best != null){
                        if(bestCars[0].car.ticks < bestResult){
                            car.nn = car.nn.merge(best);
                            car.nn.mutate(mutate);
                        }else{
                            if(car != bestCars[0] && car != bestCars[1]){
                                car.nn = car.nn.merge(bestCars[0].nn);
                                car.nn.mutate(mutate);
                            }
                            best = bestCars[0].nn.copy();
                            bestResult = bestCars[0].car.ticks;
                        }
                    }else{
                        if(car != bestCars[0] && car != bestCars[1]){
                            car.nn = car.nn.merge(bestCars[0].nn);
                            car.nn.mutate(mutate);
                        }
                        best = bestCars[0].nn.copy();
                        bestResult = bestCars[0].car.ticks;
                    }
                }
                component.respawn();
            }
        }
    }


    public static void keyPressed(KeyEvent e){
        if(!keys.contains(e.getKeyCode())) keys.add(e.getKeyCode());
    }

    public static void keyReleased(KeyEvent e){
        if(keys.contains(e.getKeyCode())) keys.remove( (Object) e.getKeyCode());
    }

    

    public static void build() throws IOException {
        frame = new JFrame("Car game");
        Game game = new Game();
        frame.add(game);
        frame.setSize(1215,818);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2 - 20);

        GameObjects.load();

        new Thread(() -> {

            while(true){
                long calculationTime = System.nanoTime();

                physic();

                int skippTime = (int) (PHYSIC_TICKS - ((System.nanoTime() - calculationTime) / 1000000));
                try {Thread.sleep(skippTime < 0 ? 0 : skippTime);}
                catch (InterruptedException e) {throw new RuntimeException(e);}

                time = skippTime;
            }

        }).start();
        new Thread(() -> {

            while(true){
                long calculationTime = System.nanoTime();

                frame.repaint();

                int skippTime = (int) (FPS - ((System.nanoTime() - calculationTime) / 1000000));
                try {Thread.sleep(skippTime < 0 ? 0 : skippTime);}
                catch (InterruptedException e) {throw new RuntimeException(e);}

            }

        }).start();


    }

}
