package oop;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final String[] args;
    private RoadFrame roadFrame;
    private SetCars setCars;
    public Simulation(String[] args) {
        this.args = args;
    }
    private boolean init(List<String> inputs) {
        if(inputs.size() < 3) {
            System.out.println("引数が足りません:" + inputs);
            return false;
        }
        roadFrame = new RoadFrame(inputs.get(0),inputs.get(1),inputs.get(2));
        roadFrame.rangeCheck();
        List<CarWithDriver> cars =  new ArrayList<>();
        for(int i = 0; i < roadFrame.getN(); i++){
            if(inputs.get(i+3) == null){
                System.out.println("車の引数が足りません");
                return false;
            }
            CarWithDriver car = new CarWithDriver(inputs.get(i+3));
            car.rangeCheck(roadFrame);
            cars.add(car);
        }
        setCars = new SetCars(cars);
        setCars.init(roadFrame);//車のぞれぞれの位置を座標に対応させたインデックスのリストに保存
        return true;
    }//必要なオブジェクト生成、初期化
    private void simulation(PrintStream out){
        int n = 1;
        setCars.next(roadFrame);
        while(!setCars.allGoalIs()){
            setCars.move();
            setCars.show();
            setCars.next(roadFrame);
            setCars.nowState(out,n++);
        }
    }
    public void simulate(){
        for(String arg:args){
            try(PrintStream out = new PrintStream(arg+".log")) {
                List<String> inputs = Files.readAllLines(Paths.get(arg));
                if(init(inputs)) {
                    simulation(out);
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}



