public class Board{
  public static void StartGame(){
    int c=10;
    while (c!=0){
      int count=10;
      while (count!=0){
        System.out.print("+---");
        count--;
      }
      System.out.println("+");
      count=10;
      while (count!=0){
        System.out.print("|   ");
        count--;
      }
      if (c==7){
        System.out.println("|           SCORE:0");
      }
      else if(c==5){
        System.out.println("|           RESTART");
      }
      else{
        System.out.println("|");
      }
      c--;
    }
    c=10;
    while (c!=0){
      System.out.print("+---");
      c--;
    }
    System.out.println("+"+"\n"+"\n"+"\n");
    System.out.println("Start Game?");
  }
  public static void main(String[] args) {
    StartGame();
  }
}
