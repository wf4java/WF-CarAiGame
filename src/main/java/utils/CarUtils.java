package utils;

import models.ai.CarPerson;
import models.render.CarRender;

import java.util.ArrayList;

public class CarUtils {


    public static CarPerson getMaxTickCar(ArrayList<CarPerson> cars) {
        CarPerson ret_car = cars.get(0);
        for(int i = 1; i < cars.size(); i++){
            if(ret_car.car.ticks < cars.get(i).car.ticks) ret_car = cars.get(i);
        }
        return ret_car;
    }

    public static CarPerson[] getThoMaxCar(ArrayList<CarPerson> cars){
        CarPerson max_1 = getMaxTickCar(cars);
        cars.remove(max_1);
        CarPerson max_2 = getMaxTickCar(cars);
        return new CarPerson[]{max_1, max_2};
    }


}
