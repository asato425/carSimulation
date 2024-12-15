package oop;
import java.util.ArrayList;
import java.util.List;
public abstract class Driver{
    protected int driver;
    protected int nowDestination = 0;//目的地に到着した数
    protected int numberDestination;//目的地の数
    protected final List<List<Integer>> Destination = new ArrayList<>();//目的地の保存
    public Driver(int driver,List<List<Integer>> Destination ,int numberDestination) {//妻女に全ての情報受け取って後から必要な情報をCarに渡す
        this.Destination.addAll(Destination);
        this.numberDestination = numberDestination;
        this.driver = driver;
    }
    protected void changeNextStep(Car car,RoadFrame roadFrame) {
        if (roadFrame.intersectionIs(car.getX(), car.getY())) {
            System.out.print("交差点のためdriverに確認：");
            intersection(car,roadFrame);
        }
        else{
            intersectionOutside(car);
        }
    }//次のステップの向きの変更
    abstract protected void intersection(Car car,RoadFrame roadFrame);//交差点での次のステップの向きの変更
    abstract protected void intersectionOutside(Car car);//交差点以外での次のステップの向きの変更
    protected void rangeCheck(RoadFrame roadFrame){
        if(numberDestination != Destination.size()){
            throw new IllegalStateException("目的地の数が異なります");
        }//目的地の数
        for (List<Integer> integers : Destination) {
            if (!roadFrame.x.contains(integers.get(0)) && !roadFrame.y.contains(integers.get(1))) {
                throw new IllegalStateException("目的地が道路座標ではありません"+"x="+integers.get(0)+", y="+integers.get(1));
            }
        }
    }//入力の範囲の確認
    protected int getDriver(){
        return driver;
    }
    protected int getNumberDestination(){
        return numberDestination;
    }
    protected int getNowDestination(){
        return nowDestination;
    }
    protected void addDestination(){
        nowDestination++;
    }
    protected boolean lastDestinationIs(){
        return nowDestination == numberDestination;
    }//現在地が最終目的地かどうか
    protected List<Integer> distance(Car car){//x,yから目的地までの前と右の距離と向き(北から0,1,2,3)をリストで返す
        List<Integer> distance = new ArrayList<>();
        int distanceX = Destination.get(nowDestination).get(0)-car.getX();
        int distanceY = Destination.get(nowDestination).get(1)-car.getY();
        switch (car.getStep()) {
            case "N":{
                distance.add(distanceY);
                distance.add(distanceX);
                distance.add(0);
                break;
            }
            case "S":{
                distance.add(-distanceY);
                distance.add(-distanceX);
                distance.add(2);
                break;
            }
            case "E":{
                distance.add(distanceX);
                distance.add(-distanceY);
                distance.add(1);
                break;
            }
            case "W":{
                distance.add(-distanceX);
                distance.add(distanceY);
                distance.add(3);
                break;
            }
            default:throw new IllegalStateException("Stepに予期しない文字が含まれています:"+car.getStep());
        }
        return distance;
    }// 直進方向、右方向の目的地までの距離をリストで返す

}
