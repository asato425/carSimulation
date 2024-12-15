package oop;
import oop.drivers.*;
import oop.drivers.option.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
public class CarWithDriver {//このクラスでCarオブジェクトとDriverオブジェクトを使って車の操作など
    Car car;
    Driver driver;
    boolean nextAction = true;
    public CarWithDriver(String inputString) {
        String[] stringArray = inputString.split(" ");
        int driver = Integer.parseInt(stringArray[0]);
        int x = Integer.parseInt(stringArray[1]);
        int y = Integer.parseInt(stringArray[2]);
        String step = stringArray[3];
        int numberDestination = Integer.parseInt(stringArray[4]);
        List<List<Integer>> Destination = new ArrayList<>();//目的地
        System.out.print("目的地の数:" + numberDestination);
        for (int i = 5; i < stringArray.length; i += 2) {
            List<Integer> L = new ArrayList<>();
            L.add(Integer.parseInt(stringArray[i]));
            L.add(Integer.parseInt(stringArray[i+1]));
            Destination.add(L);
        }
        System.out.print(" 目的地:");
        for (List<Integer> integers : Destination) {
            System.out.print("(" + integers.get(0) + "," + integers.get(1) + ") ");
        }
        if(driver == 1){
            this.driver = new Driver1(driver, Destination, numberDestination);
        }
        else if(driver == 2){
            this.driver = new Driver2(driver, Destination, numberDestination);
        }
        else if(driver == 3){//Uターン可能
            this.driver = new Driver3(driver, Destination, numberDestination);
        }
        else if(driver == 4){//3+目的地ソート
            Sort s = new Sort(Destination, x, y);
            this.driver = new Driver3(driver, s.sort(), numberDestination);
        }
        else if(driver == 5){//4(Uターン可能,目的地ソート)+最短ルート
            Sort s = new Sort(Destination, x, y);
            this.driver = new Driver5(driver, s.sort(), numberDestination);
        }
        this.car = new Car(step, x, y);
        subCarState();
    }
    protected void carState(PrintStream out){
        String str = car.getX() +" " + car.getY() + " " + car.getStep() + " " + car.getNextStep();
        out.println(str);
        System.out.println(str);
    }//出力形式
    protected void subCarState(){
        String str = "driver:" + driver.getDriver()+ " x=" + car.getX() +" y=" +  car.getY() + " step:" + car.getStep() + " nextStep:" + car.getNextStep();
        System.out.println(str);
    }//車の状態を標準出力
    protected void rangeCheck(RoadFrame roadFrame){//Car1とDriverで分けて確認する
        driver.rangeCheck(roadFrame);
        car.rangeCheck(roadFrame);
    }//変数の範囲の確認
    protected int getX(){
        return car.getX();
    }
    protected int getY(){
        return car.getY();
    }
    protected String getStep(){
        return car.getStep();
    }
    protected String getNextStep(){
        return car.getNextStep();
    }
    protected boolean getNextAction(){
        return nextAction;
    }
    protected int getDriver(){
        return driver.getDriver();
    }
    protected void changeNextAction(boolean b){
        nextAction = b;
    }
    protected boolean goalIs(){
        return car.goalIs;
    }
    protected void changeNextStep(RoadFrame roadFrame){
        if(car.getStep().equals("G")){
            System.out.print("駐車済み:");
            subCarState();
        }
        else if(driver.lastDestinationIs()){
            car.changeNextStep("G");
            subCarState();
        }
        else{
            driver.changeNextStep(car,roadFrame);
            subCarState();
        }
    }
    protected void destinationIs(){
        if(!driver.lastDestinationIs()){//最終目的地の二重確認を防ぐ
            List<Integer> distance = driver.distance(car);
            if(distance.get(0) == 0 && distance.get(1) == 0 && (!car.getStep().equals("G"))){
                driver.addDestination();
                System.out.print("目的地に到着"+driver.getNowDestination()+"/"+driver.getNumberDestination()+" ");
                subCarState();
                System.out.println("　");
            }
        }
    }//現在地が目的地だったらカウント
    protected void move(){
        if(nextAction){
            car.move();
        }
    }
    protected String wantToTurn(){
        return car.wantToTurn();
    }
}

