package oop.drivers.option;
import oop.*;
import java.util.ArrayList;
import java.util.List;
public class Driver5 extends Driver {
    boolean nextUturnIs = false;
    public Driver5(int driver, List<List<Integer>> Destination ,int numberDestination) {
        super(driver, Destination ,numberDestination);
    }
    protected void intersection(Car car, RoadFrame roadFrame){
        List<Integer> distance = new ArrayList<>(distance(car));
        if(distance.get(1) == 0) {
            System.out.print("直進：");
        }
        else if(distance.get(0) == 0){
            if(distance.get(1) > 0){
                car.changeNextStep("right");
                System.out.print("右折：");
            }
            else{
                car.changeNextStep("left");
                System.out.print("左折：");
            }
        }
        else{
            ShortestDistanceSelection(car, roadFrame);
        }
    }
    protected void intersectionOutside(Car car){
        List<Integer> distance = new ArrayList<>(distance(car));
        if((distance.get(0) < 0 && distance.get(1) == 0) || nextUturnIs){
            car.changeNextStep("U");
            System.out.print("Uターン：");
            nextUturnIs = false;
        }
    }
    private void ShortestDistanceSelection(Car car, RoadFrame roadFrame){
        List<Integer> destination = new ArrayList<>(Destination.get(nowDestination));
        int x = car.getX();
        int y = car.getY();
        int distanceX = destination.get(0)-x;
        int distanceY = destination.get(1)-y;
        if(roadFrame.x.contains(destination.get(0))){
            if(distanceX > 0){
                change(car, "E");
            }
            else{
                change(car, "W");
            }
        }
        else{
            if(distanceY > 0){
                change(car, "N");
            }
            else{
                change(car, "S");
            }
        }
    }//最短距離の探索
    private void change(Car car, String step){
        switch (step) {
            case "E":
                switch (car.getStep()) {
                    case "N":
                        car.changeNextStep("right");
                        System.out.print("右折：");
                        break;
                    case "S":
                        car.changeNextStep("left");
                        System.out.print("左折：");
                        break;
                    case "E":
                        System.out.print("直進：");
                        break;
                    case "W":
                        System.out.print("次Uターン：");
                        nextUturnIs = true;
                        break;
                    default:throw new IllegalStateException("Stepに予期しない文字が含まれています" + step);
                }
                break;
            case "S":
                switch (car.getStep()) {
                    case "E":
                        car.changeNextStep("right");
                        System.out.print("右折：");
                        break;
                    case "W":
                        car.changeNextStep("left");
                        System.out.print("左折：");
                        break;
                    case "S":
                        System.out.print("直進：");
                        break;
                    case "N":
                        System.out.print("次Uターン：");
                        nextUturnIs = true;
                        break;
                    default:throw new IllegalStateException("Stepに予期しない文字が含まれています" + step);
                }
                break;
            case "W":
                switch (car.getStep()) {
                    case "S":
                        car.changeNextStep("right");
                        System.out.print("右折：");
                        break;
                    case "N":
                        car.changeNextStep("left");
                        System.out.print("左折：");
                        break;
                    case "W":
                        System.out.print("直進：");
                        break;
                    case "E":
                        System.out.print("次Uターン：");
                        nextUturnIs = true;
                        break;
                    default:throw new IllegalStateException("Stepに予期しない文字が含まれています" + step);
                }
                break;
            case "N":
                switch (car.getStep()) {
                    case "W":
                        car.changeNextStep("right");
                        System.out.print("右折：");
                        break;
                    case "E":
                        car.changeNextStep("left");
                        System.out.print("左折：");
                        break;
                    case "N":
                        System.out.print("直進：");
                        break;
                    case "S":
                        System.out.print("次Uターン：");
                        nextUturnIs = true;
                        break;
                    default:
                        throw new IllegalStateException("Stepに予期しない文字が含まれています" + step);
                }
                break;
            default:throw new IllegalStateException("nextStepの変更で無効な引数：" + step);
        }
    }

}
