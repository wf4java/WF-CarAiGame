package models.ai;

import neural_network.NeuralNetwork;
import models.render.CarRender;

public class CarPerson {

    public NeuralNetwork nn;
    public CarRender car;


    public CarPerson(CarRender car, int inputN, int hiddenL ,int hiddenN, int outputN){
        this.car = car;
        this.nn = new NeuralNetwork(inputN, hiddenL , hiddenN, outputN);
        //this.nn = NeuralNetwork.readFromFile("good.json");
    }

    public void guess(){
        if(car.inGame) {
            double[] gs = nn.guess(car.getData());

            if(gs.length == 1){
               if(gs[0] > 0.5) car.rotateRight(1);
               else  car.rotateLeft(1);
            }else if(gs.length == 2){
                car.rotateRight(gs[0]);
                car.rotateLeft(gs[1]);
            }

            //if(gs[1] > 0.5) car.moveForward();
            //if(gs[1] > 0.5) car.rotateLeft(1);
            //car.speed = (gs[2] * 10) + 1;
        }
    }


}
