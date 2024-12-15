import oop.*;

class Main5 {
  public static void main(String[] args) {
    if(args.length == 0){
      System.out.println("引数がありません");
    }
    else{
      Simulation simulation = new Simulation(args);
      simulation.simulate();
    }
  }
}
