package oop;
import java.util.List;
public class NextAction{
    protected void nextActIs(RoadFrame roadFrame, List<CarWithDriver> cars, List<List<List<CarWithDriver>>> carPlace){
        System.out.println("nextActionの変更");
        for(int x: roadFrame.x){
            for(int y: roadFrame.y){
                if(!carPlace.get(x).get(y).isEmpty()) {
                    System.out.println("x="+x + ", " + "y="+y);
                    intersectionNext(carPlace.get(x).get(y),carPlace);
                }
            }
        }//交差点での確認
        for(CarWithDriver car:cars){
            if(!roadFrame.intersectionIs(car.getX(),car.getY()) && car.getDriver() == 3 && car.getNextAction() && !car.getStep().equals(car.getNextStep()) &&!car.getNextStep().equals("G") ){
                if(carPlace.get(car.getX()).get(car.getY()).size() == 2){
                    if(!carPlace.get(car.getX()).get(car.getY()).get(0).getStep().equals("G") && !carPlace.get(car.getX()).get(car.getY()).get(1).getStep().equals("G")){
                        car.changeNextAction(false);
                        System.out.print("優先車がいるのでUターンできません：");
                        car.subCarState();
                        backChange(car,carPlace);
                    }
                    else{
                        System.out.print("Uターンできます：");
                        car.subCarState();
                    }
                }
                else if(carPlace.get(car.getX()).get(car.getY()).size() > 3) {
                    throw new IllegalStateException("(x,y)=" + "(" + car.getX() + "," + car.getY() + ")に車が三台以上います");
                }
                else if(!carPlace.get(car.getX()).get(car.getY()).get(0).getNextStep().equals("G")){
                    System.out.print("Uターンできます：");
                    car.subCarState();
                }
            }
        }//Uターンできるかの確認
        System.out.println(" ");
    }//動けない車があるかのチェック、(nextActionの変更)
    private void intersectionNext(List<CarWithDriver> cars, List<List<List<CarWithDriver>>> carPlace){
        if(cars.size() >= 2){
            for(CarWithDriver car: cars){
                if(car.getStep().equals(car.getNextStep())){
                    straightCheck(car);
                }//直進の確認
                else if(car.wantToTurn().equals("left")){
                    leftCheck(car,cars, carPlace);

                }//左折の確認
                else if(car.wantToTurn().equals("right")){
                    rightCheck(car,cars, carPlace);
                }//右折の確認
                else if(car.wantToTurn().equals("U")){
                    uTurnCheck(car,cars, carPlace);
                }//Uターンの確認
                else{
                    System.out.print("intersectionNext method内で例外が起きました：");
                    car.subCarState();
                }
            }
        }
        else {
            for (CarWithDriver car : cars) {
                car.changeNextAction(true);
                System.out.print("交差点に一台のみなので進めます：");
                car.subCarState();
            }
        }//交差点に一台のみ
    }//交差点にいる車が動けるかどうか
    private void backChange(CarWithDriver car, List<List<List<CarWithDriver>>> carPlace){
        int x = car.getX();
        int y = car.getY();
        System.out.print("x="+x+", y="+y + "の");
        System.out.println("前方で車が停止しています");
        switch (car.getStep()) {
            case "N" -> y--;
            case "S" -> y++;
            case "E" -> x--;
            case "W" -> x++;
            default -> throw new IllegalStateException("Stepに予期しない文字が含まれています"+car.getStep());
        }
        System.out.println("x="+x+", y="+y+"の確認をします");
        if(!carPlace.get(x).get(y).isEmpty()){
            for(CarWithDriver c:carPlace.get(x).get(y)){
                if(car.getStep().equals(c.getNextStep())){
                    c.changeNextAction(false);
                    System.out.print("停止：");
                    c.subCarState();
                    backChange(c,carPlace);
                }
            }
        }
        else{
            System.out.println("x="+x+", y="+y+"に車はいません");
        }
    }//交差点で止まった後ろの車も止める
    private void straightCheck(CarWithDriver car){
        car.changeNextAction(true);
        System.out.print("直進OK：");
        car.subCarState();
    }//直進の確認
    private void rightCheck(CarWithDriver car,List<CarWithDriver> cars, List<List<List<CarWithDriver>>> carPlace){
        boolean check = true;
        for(CarWithDriver car2: cars) {
            if (car2.getStep().equals(car2.getNextStep()) && car.getNextStep().equals(car2.getNextStep()) ||
                    car2.wantToTurn().equals("left") && car.getNextStep().equals(car2.getNextStep())) {
                check = false;
                break;
            }
        }
        car.changeNextAction(check);
        if(check){
            System.out.print("右折OK：");
            car.subCarState();
        }
        else{
            System.out.print("他に進む車がいるので右折できません：");
            car.subCarState();
            backChange(car,carPlace);
        }
    }//右折の確認
    private void leftCheck(CarWithDriver car,List<CarWithDriver> cars, List<List<List<CarWithDriver>>> carPlace){
        boolean check = true;
        for(CarWithDriver car2: cars) {
            if (car2.getStep().equals(car2.getNextStep()) && car.getNextStep().equals(car2.getNextStep())) {
                check = false;
                break;
            }
        }
        car.changeNextAction(check);
        if(check){
            System.out.print("左折OK：");
            car.subCarState();
        }
        else{
            System.out.print("直進車がいるので左折できません：");
            car.subCarState();
            backChange(car,carPlace);
        }
    }//左折の確認
    private void uTurnCheck(CarWithDriver car,List<CarWithDriver> cars, List<List<List<CarWithDriver>>> carPlace){
        boolean check = true;
        for(CarWithDriver car2: cars) {
            if (car.getNextStep().equals(car.getNextStep()) && !car.getStep().equals(car2.getStep())) {
                check = false;
                break;
            }
        }
        car.changeNextAction(check);
        if(check){
            System.out.print("UターンOK：");
            car.subCarState();
        }
        else{
            System.out.print("他に進む車がいるのでUターンできません：");
            car.subCarState();
            backChange(car,carPlace);
        }
    }//Uターンの確認
}