import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;
import com.googlecode.lanterna.screen.Screen;
import java.lang.Math;
public class Board {
  private int spotCount;
  private int blockCount;
  private int score;
  private Square[][] board;
  private boolean[][] layout;

  public Board(){
    this.board = new Square[10][10];
    this.layout= new boolean[21][10];
    for (int a=0;a<21;a++){
      for (int b=0;b<10;b++){
        this.layout[a][b]=false;
      }
    }
    this.score=0;
    this.spotCount=100;
    this.blockCount=0;
  }
  public void powerUps(int x){
    if (x==1){
      this.score=this.score-300;
    }
    else if(x==2){
      this.score=this.score-50;
    }
  }
  public void continueScore(int x){
    score=x;
  }
  public int getScore(){
    return this.score;
  }

  public Square[][] getBoard(){
    return board;
  }
  public void setBoard(Square[][] replace){
    this.board=replace;
  }
  public Block generateBlock(){
      String[] colors = new String[6];
      colors[0]= "BLUE";
      colors[1]= "CYAN";
      colors[2]= "GREEN";
      colors[3]= "MAGENTA";
      colors[4]= "RED";
      colors[5]= "YELLOW";
      int roll=(int)(Math.random()*100)%3;
      int length;
      Block random;
      if(roll==0){
        roll=(int)(Math.random()*100)%3+1;
        random=new FullBlock(roll,Terminal.Color.valueOf(colors[(int)(Math.random()*100)%(6)]));
      }
      else if(roll==1){
        roll=(int)(Math.random()*100)%2;
        length=(int)(Math.random()*100)%4+2;
        random=new LongBlock(length,roll,Terminal.Color.valueOf(colors[(int)(Math.random()*100)%(6)]));
      }
      else{
        roll=(int)(Math.random()*100)%4;
        length=(int)(Math.random()*100)%2+2;
        random=new LBlock(length,roll,Terminal.Color.valueOf(colors[(int)(Math.random()*100)%(6)]));
      }
      this.blockCount++;
      return random;
    }

    public Square getSquare(int x, int y){
      return this.board[x][y];
    }

    public boolean placeBlock(Block b, int x, int y){
      int oriY=y;
      if (placeable(b, x, y)){
        for (int i = 0; i < b.getLength(); i++){
          for (int j = 0; j < b.getWidth(); j++){
            if (b.getBlock()[i][j] != null){
              board[x][y] = b.getBlock()[i][j];
              this.score++;
            }
            y++;
            if (j == b.getWidth() - 1){
              y = oriY;
              x++;
            }
          }
        }
        this.spotCount=this.spotCount-b.getNumSquare();
        return true;
      }
      return false;
    }

    private boolean placeable(Block bl, int x, int y){
      int oriY=y;
      for (int i = 0; i < bl.getLength(); i++){
        for (int j = 0; j < bl.getWidth(); j++){
          if (bl.getBlock()[i][j] != null && board[x][y] != null){
            return false;
          }
          y++;
          if (j == bl.getWidth() - 1){
            y = oriY;
            x++;
          }
        }
      }
      return true;
    }

    public boolean checkRows(){
      int full = 0;
      boolean clear = false;
      for (int a = 0; a < board.length; a++){
        full = 0;
        for (int b = 0; b < board[0].length; b++){
          if (board[a][b] != null){
            full++;
          }
          if (b == board[0].length - 1 && full == 10){
            clearRow(a);
            clear = true;
          }
        }
      }
      return clear;
    }

    private void clearRow(int i){
      for (int j = 0; j < board[0].length; j++){
        board[i][j] = null;
      }
      this.score+=100;
      this.spotCount+=10;
    }

    public boolean checkCols(){
      int full=0;
      boolean clear = false;
      for (int a = 0; a < board[0].length; a++){
        full = 0;
        for (int b = 0; b < board.length; b++){
          if (board[b][a] != null){
            full++;
          }
          if (b == board.length - 1 && full == 10){
            clearCol(a);
            clear = true;
          }
        }
      }
      return clear;
    }

    private void clearCol(int i){
      for (int j = 0; j < board.length; j++){
        board[j][i] = null;
      }
      this.score+=100;
      this.spotCount+=10;
    }
    public boolean BlockGameOver(Block a){
      if (this.spotCount<a.getNumSquare()){
        return true;
      }
      return false;
    }

    public boolean BlockOver(Block a){
      for (int x=0;x<this.board.length-a.getLength()+1;x++){
        for (int y=0;y<this.board[0].length-a.getWidth()+1;y++){
          if(placeable(a,x,y)){
            return false;
          }
        }
      }
      return true;
    }

    public boolean GameOver(Block a, Block b, Block c){
      if (a.getNumSquare()+b.getNumSquare()+c.getNumSquare()>this.spotCount||(BlockGameOver(a)&&BlockGameOver(b)&&BlockGameOver(c))){
        return true;
      }
      if(BlockOver(a)&&BlockOver(b)&&BlockOver(c)){
        return true;
      }
      return false;
    }

    public String toString(){
      String s="";//this is the string containing the entire board
      int c=10;
      while (c!=0){
            s=s+"+---+---+---+---+---+---+---+---+---+---+\n";
            if (c==7){
              s=s+"|   |   |   |   |   |   |   |   |   |   |           SCORE:"+this.getScore()+"\n";
            }
            else if(c==5){
              s=s+"|   |   |   |   |   |   |   |   |   |   |           (Press Tab to RESTART)\n";
            }
            else if(c==4){
              s=s+"|   |   |   |   |   |   |   |   |   |   |           (Press BackSpace to Choose Different Block)\n";
            }
            else if(c==3){
              s=s+"|   |   |   |   |   |   |   |   |   |   |           (Press Delete to Use 300 points to Purchase New Set of Blocks)\n";
            }
            else{
            s=s+"|   |   |   |   |   |   |   |   |   |   |\n";
          }
          c--;
      }
      s=s+"+---+---+---+---+---+---+---+---+---+---+\n"+"\n"+"\n";
      return s;
    }

  public static void main(String[] args) {
    Board x=new Board();
    Block a=x.generateBlock();
    Block b=x.generateBlock();

}
}
