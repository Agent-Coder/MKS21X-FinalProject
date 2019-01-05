public class Board{
  public static String StartGame(){
    String s="";
    int c=10;
    while (c!=0){
          s=s+"+---+---+---+---+---+---+---+---+---+---+\n";
          if (c==7){
            s=s+"|   |   |   |   |   |   |   |   |   |   |           SCORE:0\n";
          }
          else if(c==5){
            s=s+"|   |   |   |   |   |   |   |   |   |   |           (Press R to restart)\n";
          }
          else{
          s=s+"|   |   |   |   |   |   |   |   |   |   |\n";
        }
        c--;
    }
    s=s+"+---+---+---+---+---+---+---+---+---+---+\n"+"\n"+"\n";
    s=s+"PRESS ENTER TO START GAME";
    return s;
  }
  public static void main(String[] args) {
    System.out.println(StartGame());
  }
}
