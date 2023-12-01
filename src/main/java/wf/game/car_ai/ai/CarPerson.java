package wf.game.car_ai.ai;


import wf.core.game_engine.graphic.components.renders.additional.CarRender;
import wf.core.game_engine.neural_network.NeuralNetwork;


public class CarPerson {

    private NeuralNetwork nn;
    private CarRender car;


    public CarPerson(CarRender car, int inputNodes, int[] hiddenNodes, int outputNodes){
        this.car = car;
        this.nn = new NeuralNetwork(inputNodes, hiddenNodes, outputNodes);
        //this.nn = NeuralNetwork.readFromFile("best.json");
    }

    public CarPerson(CarRender car, int[] nodes){
        this.car = car;
        this.nn = new NeuralNetwork(nodes);
    }

    public void guess(){
        if(car.isInGame()) {
            double[] gs = nn.guess(car.getData());

            if(gs.length == 1){
                double r = gs[0];

                if(r > 0.5) car.rotateRight((r - 0.5) * 2d);
                else  car.rotateLeft((0.5 - r) * 2d);
            }else if(gs.length > 1){
                car.rotateRight(gs[0]);
                car.rotateLeft(gs[1]);
            }

            //if(gs[1] > 0.5) car.moveForward();
            //if(gs[1] > 0.5) car.rotateLeft(1);
            //car.speed = (gs[2] * 10) + 1;
        }
    }


    public NeuralNetwork getNn() {
        return nn;
    }

    public void setNn(NeuralNetwork nn) {
        this.nn = nn;
    }

    public CarRender getCar() {
        return car;
    }

    public void setCar(CarRender car) {
        this.car = car;
    }
}
