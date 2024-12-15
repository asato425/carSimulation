package oop;
public class Car{
    private String step,nextStep;//現在の向き,進行方向
    private int x,y;//現在地
    protected boolean goalIs = false;
    public Car(String step, int x, int y) {//文字列から変換するのをDriverとCarをまとめたクラスでやる、そこからそれぞfれに必要な引数をとってオブジェクト作る
        this.step = this.nextStep = step;
        this.x = x;
        this.y = y;
    }
    protected void rangeCheck(RoadFrame roadFrame){
        if(x < 0 || roadFrame.getR() < x || y < 0 || roadFrame.getT() < y){
            throw new IllegalStateException("現在地が範囲外です　(x,y)=("+x+","+y+")");
        }
        if(!roadFrame.x.contains(x) && !roadFrame.y.contains(y)){
            throw new IllegalStateException("現在地が道路上ではありません　(x,y)=("+x+","+y+")");
        }
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public String getStep(){
        return step;
    }
    protected String getNextStep(){
        return nextStep;
    }
    public void changeNextStep(String str){
        switch (str) {
            case "right" -> {
                switch (step) {
                    case "N" -> nextStep = "E";
                    case "S" -> nextStep = "W";
                    case "E" -> nextStep = "S";
                    case "W" -> nextStep = "N";
                    default -> throw new IllegalStateException("Stepに予期しない文字が含まれています" + step);
                }
            }
            case "left" -> {
                switch (step) {
                    case "N" -> nextStep = "W";
                    case "S" -> nextStep = "E";
                    case "E" -> nextStep = "N";
                    case "W" -> nextStep = "S";
                    default -> throw new IllegalStateException("Stepに予期しない文字が含まれています" + step);
                }
            }
            case "U" -> {
                switch (step) {
                    case "N" -> nextStep = "S";
                    case "S" -> nextStep = "N";
                    case "E" -> nextStep = "W";
                    case "W" -> nextStep = "E";
                    default -> throw new IllegalStateException("Stepに予期しない文字が含まれています" + step);
                }
            }
            case "G" -> nextStep = "G";
            default -> throw new IllegalStateException("nextStepの変更で無効な引数：" + str);
        }
    }//次のステップの変更
    protected void move(){
        if(!step.equals("G")){
            if(nextStep.equals("G")){
                parking();
                goalIs = true;
                System.out.println("駐車");
            }
            else if(nextStep.equals(step)){
                goStraight();
                System.out.println("直進");
            }
            else{
                turn();
                System.out.println("曲がります");
            }
        }
        else{
            System.out.println("駐車済");
        }
    }
    private void goStraight(){
        switch (nextStep) {
            case "N" -> y++;
            case "S" -> y--;
            case "E" -> x++;
            case "W" -> x--;
            default -> throw new IllegalStateException("nextStepに予期しない文字が含まれています:"+nextStep);
        }
    }
    private void turn(){
        switch (nextStep) {
            case "N" -> {
                step = "N";
                y += 1;
            }
            case "S" -> {
                step = "S";
                y -= 1;
            }
            case "E" -> {
                step = "E";
                x += 1;
            }
            case "W" -> {
                step = "W";
                x -= 1;
            }
            default -> throw new IllegalStateException("nextStepに予期しない文字が含まれています:" + nextStep);
        }
    }
    private void parking(){
        step = "G";
    }
    protected String wantToTurn(){
        switch (nextStep) {
            case "N":{
                if (step.equals("E")) {
                    return "left";
                } else if (step.equals("W")) {
                    return "right";
                }
                break;
            }
            case "S":{
                if (step.equals("W")) {
                    return "left";
                } else if (step.equals("E")) {
                    return "right";
                }
                break;
            }
            case "E":{
                if (step.equals("S")) {
                    return "left";
                } else if (step.equals("N")) {
                    return "right";
                }
                break;
            }
            case "W":{
                if (step.equals("N")) {
                    return "left";
                } else if (step.equals("S")) {
                    return "right";
                }
                break;
            }
            default:
                throw new IllegalStateException("nextStepに予期しない文字が含まれています"+nextStep);
        }
        return null;
    }//次に曲がりたい方向を返す
}