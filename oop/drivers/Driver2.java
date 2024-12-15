package oop.drivers;
import oop.*;
import java.util.ArrayList;
import java.util.List;
public class Driver2 extends Driver {
    public Driver2(int driver, List<List<Integer>> Destination ,int numberDestination) {
        super(driver, Destination ,numberDestination);
    }
    protected void intersection(Car car,RoadFrame roadFrame){
        List<Integer> distance = new ArrayList<>(distance(car));
        if(distance.get(1) > 0){
            car.changeNextStep("right");
            System.out.print("右折：");
        }
        else if(distance.get(0) <= 0 || distance.get(1) < 0){
            car.changeNextStep("left");
            System.out.print("左折：");
        }
        else{
            System.out.print("直進：");
        }
    }
    protected void intersectionOutside(Car car){
        System.out.print("交差点でないため直進：");
    }
}
