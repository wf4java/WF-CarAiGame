package game.physic;

import neural_network.NeuralNetwork;
import game.components.GameObjects;
import models.ai.CarPerson;
import models.render.LineRender;
import models.utils.Line;
import utils.CarUtils;
import utils.PosUtils;

import java.util.ArrayList;
import java.util.Random;

public class Physic {

    public static NeuralNetwork bestNN;
    public static NeuralNetwork lastBestNN;
    public static long bestNNResult;
    public static int genetics = 0;
    public static int carsLeft = 0;


    public static void calculatePhysic() {

        for (CarPerson person : GameObjects.persons) {
            if (person.car.inGame) {
                person.car.setLines(2);
                person.car.setAi_lines(GameObjects.borders.toArray(new Line[0]));
                person.car.moveForward();
                person.car.ticks++;
                person.guess();
            }
        }


        //Collision check
        for (CarPerson person : GameObjects.persons) {
            if (!person.car.inGame) continue;
            for (LineRender lr : person.car.lines) {
                for (Line l : GameObjects.borders) {
                    if (!PosUtils.intersectsLines(lr.line, l)) continue;
                    person.car.inGame = false;
                    break;
                }
            }
        }

        carsLeft = 0;
        GameObjects.persons.forEach(p -> { if(p.car.inGame) carsLeft++; });


        if(carsLeft == 0) {
            CarPerson lastBestCar = CarUtils.getMaxTickCar(new ArrayList(GameObjects.persons));
            lastBestNN = lastBestCar.nn.copy();
            genetics++;
            for(CarPerson p : GameObjects.persons) {
                double mutate = new Random().nextDouble() > 0.8 ? new Random().nextDouble() * 1.2 : 0.3;
                if (mutate > 1)
                    p.nn = p.nn.merge(new NeuralNetwork(p.nn.getInputNodes(), p.nn.getHiddenLayers(), p.nn.getHiddenNodes(), p.nn.getOutputNodes()), (mutate - 1 * 5));
                else {
                    if (bestNN == null) bestNN = lastBestNN;
                    if (lastBestCar.car.ticks > bestNNResult) { bestNN = lastBestNN; bestNNResult = lastBestCar.car.ticks; }
                    p.nn = p.nn.merge(bestNN);
                    p.nn.mutate(mutate);
                }
                p.car.respawn();

            }
            GameObjects.nnRender.weights = lastBestNN.getWeights();
            GameObjects.nnRender.calculate();
        }
    }



}
