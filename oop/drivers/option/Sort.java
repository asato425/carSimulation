package oop.drivers.option;
import java.util.ArrayList;
import java.util.List;

public class Sort {
    private final List<List<Integer>> Destination = new ArrayList<>();
    private final int x, y;
    public Sort(List<List<Integer>> Destination, int x, int y) {
        this.Destination.addAll(Destination);
        this.x = x;
        this.y = y;
    }
    public List<List<Integer>> sort(){
        List<List<Integer>> result = new ArrayList<>();
        int nowX = x;
        int nowY = y;
        while(!Destination.isEmpty()){
            int index = 0;
            int dis = 10000;
            for(int i = 0; i < Destination.size(); i++){
                int num = distance(nowX, nowY, Destination.get(i).get(0), Destination.get(i).get(1));
                if(num < dis){
                    dis = num;
                    index = i;
                }
            }
            result.add(Destination.get(index));
            nowX = Destination.get(index).get(0);
            nowY = Destination.get(index).get(1);
            Destination.remove(index);
        }

        return result;
    }
    private int distance(int x, int y, int x1, int y1){
        return Math.abs(x - x1) + Math.abs(y-y1);
    }
}