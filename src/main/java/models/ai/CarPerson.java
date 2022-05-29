package models.ai;

import basicneuralnetwork.NeuralNetwork;
import models.render.CarRender;
import models.utils.Line;

public class CarPerson {

    public NeuralNetwork nn;
    public CarRender car;


    public CarPerson(CarRender car, int inputN,int hiddenL ,int hiddenN, int outputN){
        this.car = car;
        this.nn = new NeuralNetwork(inputN,hiddenL , hiddenN, outputN);
        //this.nn = NeuralNetwork.readFromFile("good.json");
    }

    public void guess(){
        if(car.inGame) {
            double[] gs = nn.guess(car.getData());
            if(gs[0] > 0.5) car.rotateRight(1);
            else if(gs[0] < 0.5) car.rotateLeft(1);
            //if(gs[1] > 0.5) car.moveForward();
            //if(gs[1] > 0.5) car.rotateLeft(1);
            //car.speed = (gs[2] * 10) + 1;
        }
    }


}
