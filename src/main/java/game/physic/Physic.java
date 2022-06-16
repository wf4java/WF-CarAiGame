package game.physic;

import game.Game;
import game.ai.CarPerson;
import neural_network.NeuralNetwork;
import wf_engine.listeners.PhysicCalculate;
import wf_engine.render_components.LineRender;
import wf_engine.utils.CarUtils;
import wf_engine.utils.PosUtils;
import wf_engine.utils_models.Line;

import java.util.ArrayList;
import java.util.Random;

public class Physic implements PhysicCalculate {

    public static NeuralNetwork bestNN;
    public static NeuralNetwork lastBestNN;
    public static double bestNNResult;
    public static int genetics = 0;
    public static int carsLeft = 0;


    public void calculatePhysic() {

        for (CarPerson person : Game.persons) {
            if (person.car.inGame) {
                person.car.setLines(2);
                person.car.setAi_lines(Game.borders.toArray(new Line[0]));
                person.car.moveForward();
                person.car.ticks++;
                person.guess();
            }
        }


        //Collision check
        for (CarPerson person : Game.persons) {
            if (!person.car.inGame) continue;
            for (LineRender lr : person.car.lines) {
                for (Line l : Game.borders) {
                    if (!PosUtils.intersectsLines(lr.line, l)) continue;
                    person.car.inGame = false;
                    break;
                }
            }
        }

        carsLeft = 0;
        Game.persons.forEach(p -> { if(p.car.inGame) carsLeft++; });


        if(carsLeft == 0) {
            CarPerson lastBestCar = CarUtils.getMaxTickCar(new ArrayList(Game.persons));
            lastBestNN = lastBestCar.nn.copy();
            genetics++;
            for(CarPerson p : Game.persons) {
                double mutate = new Random().nextDouble() > 0.8 ? new Random().nextDouble() * 1.2 : 0.3;
                if (mutate > 1)
                    p.nn = p.nn.merge(new NeuralNetwork(p.nn.getInputNodes(), p.nn.getHiddenNodes() , p.nn.getOutputNodes()), 1 - (mutate - 1 * 5));
                else {
                    if (bestNN == null) bestNN = lastBestNN;
                    if (lastBestCar.car.ticks > bestNNResult) { bestNN = lastBestNN; bestNNResult = lastBestCar.car.ticks; }
                    p.nn = p.nn.merge(bestNN);
                    p.nn.mutate(mutate);
                }
                p.car.respawn();

            }
            Game.nnRender.weights = lastBestNN.getWeights();
            Game.nnRender.calculate();
        }
    }



}
