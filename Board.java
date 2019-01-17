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

  public int getScore(){
    return this.score;
  }

  public Square[][] getBoard(){
    return board;
  }

  public Block generateBlock(){
      Terminal.Color[] colors = Terminal.Color.values();
      int roll=(int)(Math.random()*100)%3;
      int length;
      Block random;
      if(roll==0){
        roll=(int)(Math.random()*100)%3+1;
        random=new FullBlock(roll,colors[(int)(Math.random()*100)%(colors.length-1) + 1]);
      }
      else if(roll==1){
        roll=(int)(Math.random()*100)%2;
        length=(int)(Math.random()*100)%4+2;
        random=new LongBlock(length,roll,colors[(int)(Math.random()*100)%(colors.length-1) + 1]);
      }
      else{
        roll=(int)(Math.random()*100)%4;
        length=(int)(Math.random()*100)%2+2;
        random=new LBlock(length,roll,colors[(int)(Math.random()*100)%(colors.length-1) + 1]);
      }
      this.blockCount++;
      return random;
    }

    public Square getSquare(int x, int y){
      return this.board[x][y];
    }

    public void BlockonBoard(Block a){
      int r =a.getRow();
      int c=a.getCol();
      for (int x=(10-r)/2;x<r+(10-r)/2;x++){
        for (int y=(10-r)/2;y<c+(10-r)/2;y++){
          layout[x][y]=a.getmap(r,c);
        }
      }
      r=a.getLength();
      c=a.getWidth();
      for (int x=r;x<r;x++){
        for (int y=c;y<c;y++){
          a.getBlock()[x][y].setXcor(x);
          a.getBlock()[x][y].setYcor(y);
        }
      }
    }

    public void ShiftHorizontal(Block a,int dir){
      int r=a.getLength();
      int c=a.getWidth();
      for (int x=r;x<r;x++){
        for (int y=c;y<c;y++){
          a.getBlock()[x][y].setXcor(x+dir);
          a.getBlock()[x][y].setYcor(y+dir);
        }
      }
      r =a.getRow();
      c=a.getCol();
      for (int x=((10-r)/2)+dir;x<(r+(10-r)/2)+dir;x++){
        for (int y=((10-r)/2)+dir;y<(c+(10-r)/2)+dir;y++){
          layout[x][y]=a.getmap(r,c);
      }
    }
  }

    public static boolean[][] blockSelection(Block a,Block b, Block c){
      boolean[][] selection=new boolean[11][a.getCol()+b.getCol()+c.getCol()+4];
      int x=0,y=0;
      for(;x<11;x++){
        for(;y<selection[0].length;y++){
            selection[x][y]=false;
        }
      }
      x=0;
      y=0;

      for(;x<a.getRow();x++){
        y=0;
        for(;y<a.getCol();y++){
          selection [x][y]=a.getmap(x,y);
        }
      }
      x=0;
      y+=2;
      int z=y;
      for(;x<b.getRow();x++){
        z=y;
        for(;z<(b.getCol()+a.getCol()+2);z++){
          selection [x][z]=b.getmap(x,z-y);
        }
      }
      x=0;
      z+=2;
      for(;x<c.getRow();x++){
        for(int w=z;w<selection[0].length;w++){
          selection [x][w]=c.getmap(x,w-z);
        }
      }
      return selection;
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
        /*for (int a=0;a<10;a++){
          full++;
          for (int b=0;b<10;b++){
            if(this.board[a][b]!=null){
              full++;
            }
          if (full==10){
            for (int c=0;c<10;c++){
              this.board[a][c]=null;
            }
            for (int d=0;d<10;d++){
              this.layout[2*a+1][d]=false;
            }
            this.score+=100;
            this.spotCount+=10;
          }
        }
      }*/
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
        /*for (int a=0;a<10;a++){
          full=0;
          for (int b=0;b<10;b++){
            if(this.board[b][a]!=null){
              full++;
            }
          if (full==10){
            for (int c=0;c<10;c++){
              this.board[c][a]=null;
            }
            for (int d=0;d<21;d++){
              this.layout[a][d]=false;
            }
            this.score+=100;
            this.spotCount+=10;
          }
        }
      }*/
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

    public boolean GameOver(Block a, Block b, Block c){
      if (a.getNumSquare()+b.getNumSquare()+c.getNumSquare()<this.spotCount||(BlockGameOver(a)&&BlockGameOver(b)&&BlockGameOver(c))){
        return true;
      }
      for (int x=0;x<this.board.length;x++){
        for (int y=0;x<this.board[0].length;y++){
          if(this.board[x][y]==null){
            if(!placeable(a,x,y)&&!placeable(a,x,y)&&!placeable(a,x,y)){
              return true;
            }
          }
        }
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
    Block c= new emptyBlock();
    System.out.println(a.toString());
    System.out.println(b.toString());
    System.out.println(c.toString());
  }
}
