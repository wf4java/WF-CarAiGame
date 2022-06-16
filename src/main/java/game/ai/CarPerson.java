package game.ai;

import game.custom_renders.CarRender;
import neural_network.NeuralNetwork;


public class CarPerson {

    public NeuralNetwork nn;
    public CarRender car;


    public CarPerson(CarRender car, int inputN, int[] hiddenN, int outputN){
        this.car = car;
        this.nn = new NeuralNetwork(inputN, hiddenN , outputN);
        //this.nn = NeuralNetwork.readFromFile("good.json");
    }

    public void guess(){
        if(car.inGame) {
            double[] gs = nn.guess(car.getData());

//            if(gs[0] > 0.5) car.rotateRight(1);
//            else  car.rotateLeft(1);


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
