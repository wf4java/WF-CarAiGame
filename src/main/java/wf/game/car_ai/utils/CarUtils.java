package wf.game.car_ai.utils;


import wf.game.car_ai.ai.CarPerson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CarUtils {


    public static CarPerson getMaxTickCar(ArrayList<CarPerson> cars) {
        return cars.stream()
                .max(Comparator.comparingInt(carPerson -> (int) carPerson.getCar().getTicks()))
                .orElse(null);
    }

    public static ArrayList<CarPerson> getTwoMaxCar(ArrayList<CarPerson> cars) {
        return cars.stream()
                .sorted(Comparator.comparingInt(carPerson -> -carPerson.getCar().getTicks()))
                .limit(2)
                .collect(Collectors.toCollection(ArrayList::new));
    }


}
