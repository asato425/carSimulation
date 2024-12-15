package oop;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
public class SetCars{
    List<CarWithDriver> car;
    List<List<List<CarWithDriver>>> carPlace;
    public SetCars(List<CarWithDriver> car) {
        this.car = car;
        this.carPlace = new ArrayList<>();
    }
    protected void init(RoadFrame roadFrame){
        for (int i = 0; i <= roadFrame.getR(); i++) {
            List<List<CarWithDriver>> carXList = new ArrayList<>();
            for (int j = 0; j <= roadFrame.getT(); j++) {
                List<CarWithDriver> carXYList = new ArrayList<>();
                carXList.add(carXYList);
            }
            carPlace.add(carXList);
        }
        for(CarWithDriver car: car){
            add(car);
        }

    }//carPlaceにcarを(最初に)追加するメソッド
    protected void show(){
        System.out.println("現在の車の状態");
        int x = 0;
        int y = 0;
        for(List<List<CarWithDriver>> a:carPlace){
            for(List<CarWithDriver> b:a){
                if(!b.isEmpty()){
                    System.out.println("x="+x+", y="+y);
                    for(CarWithDriver c:b){
                        c.subCarState();
                    }
                    System.out.println(" ");
                }
                y++;
            }
            x++;
            y = 0;
        }
        System.out.println("　");
    }//全ての車の状態を標準出力に出力
    private void add(CarWithDriver car){
        carPlace.get(car.getX()).get(car.getY()).add(car);
    }//carPlaceにcarを追加するメソッド
    private void remove(CarWithDriver car){
        carPlace.get(car.getX()).get(car.getY()).remove(car);
    }//carPlaceにcarを削除するメソッド
    protected boolean allGoalIs(){
        for(CarWithDriver car:car){
            if(!car.goalIs()){
                return false;
            }
        }
        return true;
    }//全車両が最終目的地に着いたかどうか
    protected void move(){
        System.out.println("move");
        int n = 1;
        for(CarWithDriver car : car){
            System.out.print(n++ +"台目：");
            remove(car);
            car.move();
            add(car) ;
            car.destinationIs();
        }
        System.out.println("　");
    }//車の走行&目的地に着いたかの確認
    private void nextStepChange(RoadFrame roadFrame){//車の次の状態nextStepを変更、(stepがGなら変えない、最終目的地ならばGにする）
        System.out.println("nextStepの変更");
        int n = 1;
        for(CarWithDriver car:car){
            System.out.print(n++ +"台目　");
            car.changeNextStep(roadFrame);
        }
        System.out.println(" ");
    }//次のステップの変更
    protected void next(RoadFrame roadFrame){
        NextAction nextAction = new NextAction();
        for(CarWithDriver car:car) car.changeNextAction(true);
        nextStepChange(roadFrame);
        nextAction.nextActIs(roadFrame, car, carPlace);
    }//次の進行の準備(次のステップの変更、次動けるかの確認)
    protected void nowState(PrintStream out, int n){
        System.out.println("------");
        out.println("step "+ n);
        System.out.println("step "+n);
        for(CarWithDriver car : car){
            car.carState(out);
        }
        System.out.println("------");
        System.out.println(" ");
    }//現在の車の状態を出力
}