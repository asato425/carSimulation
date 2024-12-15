package oop;
import java.util.ArrayList;
import java.util.List;
public class RoadFrame{
    private final int N,R,T;//車両数,東西の幅,南北の幅
    public final List<Integer> x = new ArrayList<>();//道路のx座標
    protected final List<Integer> y = new ArrayList<>();//道路のy座標
    public RoadFrame(String inputString, String x, String y) {//strがN R Tの文字列
        String[] stringArray = inputString.split(" ");
        N = Integer.parseInt(stringArray[0]);
        R = Integer.parseInt(stringArray[1]);
        T = Integer.parseInt(stringArray[2]);
        String[] stringX = x.split(" ");
        String[] stringY = y.split(" ");
        for (String str:stringX) {
            int subX = Integer.parseInt(str);
            this.x.add(subX);
        }
        for (String str:stringY) {
            int subY = Integer.parseInt(str);
            this.y.add(subY);
        }
    }//入力の1~3行
    protected int getN(){
        return N;
    }
    protected int getR(){
        return R;
    }
    protected int getT(){
        return T;
    }
    protected void rangeCheck(){
        if(!(1 <= N && N < 10)){
            throw new IllegalStateException("Nが範囲外の入力です N:" +N);
        }
        if(!(3 <= R && R < 100)){
            throw new IllegalStateException("Rが範囲外の入力です R:" +R);
        }
        if(!(3 <= T && T < 100)){
            throw new IllegalStateException("Tが範囲外の入力です T:" +T);
        }
        for (int x:x) {
            if(x < 0 || R < x){
                throw new IllegalStateException("道路座標のxが範囲外の入力です x:" +x);
            }
        }
        for (int y:y) {
            if(y < 0 || T < y){
                throw new IllegalStateException("道路座標のyが範囲外の入力です y:" +y);
            }
        }
    }//変数の範囲の確認
    protected boolean intersectionIs(int x, int y){
        return this.x.contains(x) && this.y.contains(y) ;
    }//交差点かどうか

}