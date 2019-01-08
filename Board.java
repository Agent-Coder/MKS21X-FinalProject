public class Board {
  private int spotCount;
  private int blockCount;
  private int score;
  private Square[][] board;

  public Board(){
    Square[][] board = new Square[10][10];
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[0].length; j++){
        board[i][j] = new Square("black", i, j);
      }
    }
  }
public static Block generateBlock(){
    int roll=(int)(Math.random()*100)%3;
    int length;
    Block random;
    if(roll==0){
      roll=(int)(Math.random()*100)%3+1;
      random=new FullBlock(roll);
    }
    else if(roll==1){
      roll=(int)(Math.random()*100)%2;
      length=(int)(Math.random()*100)%4+2;
      random=new LongBlock(length,roll);
    }
    else{
      roll=(int)(Math.random()*100)%4;
      length=(int)(Math.random()*100)%2+2;
      random=new LBlock(length,roll);
    }
    return random;
  }
  public Square getSquare(int x, int y){
    return board[x][y];
  }

  public String toString(){
    String s="";//this is the string containing the entire board
    int c=10;
    while (c!=0){
          s=s+"+---+---+---+---+---+---+---+---+---+---+\n";
          if (c==7){
            s=s+"|   |   |   |   |   |   |   |   |   |   |           SCORE:0\n";
          }
          else if(c==5){
            s=s+"|   |   |   |   |   |   |   |   |   |   |           (Press Tab for Menu)\n";
          }
          else{
          s=s+"|   |   |   |   |   |   |   |   |   |   |\n";
        }
        c--;
    }
    s=s+"+---+---+---+---+---+---+---+---+---+---+\n"+"\n"+"\n";
    //s=s+"PRESS ENTER TO START GAME";
    return s;

    /*t.applyForegroundColor(Terminal.Color.BLACK);//the color of the board will be black
    t.moveCursor(0,0);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}*/


  }

}
