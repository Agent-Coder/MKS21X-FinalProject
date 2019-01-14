public class Board {
  private int spotCount;
  //how many spots on the ten by ten board are complete
  private int blockCount;
  //how many blocks are left in selection--used to restock selection
  private int score;
  //keeps track of players score
  private Square[][] board;
  //actual board and keeps track of which parts of board are filled
  private boolean[][] layout;
//used fore easier toString
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
  //constructor for board, sets board to a 10 by 10 board and all false because no blocks on it
//no spots filled yet so a total of 100 spots and block count is 0 currently zero, hasnt generated blocks
  public int getScore(){
    return this.score;
  }
//returns score
  public Square[][] getBoard(){
    return board;
  }
//returns board array

public Square getSquare(int x, int y){
  return this.board[x][y];
}
//returns whether a spot on the board is filled

  public Block generateBlock(){
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
      this.blockCount++;
      return random;
    }
//rolls a number 0,1,2 for each type of block and then rolls random dimensions of the block and creates import junit.framework.TestCase;
//updates block Count and returns the block that is made
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
//creates the display of three  randomly generated blocks for block selection
    public boolean placeBlock(Block b, int x, int y){
      int oriY=y;
      if (placeable(b, x, y)){
        for (int i = 0; i < b.getLength(); i++){
          for (int j = 0; j < b.getWidth(); j++){
            if (b.getBlock()[i][j] != null){
              board[x][y] = b.getBlock()[i][j];
            }
            y++;
            if (j == b.getWidth() - 1){
              y = oriY;
              x++;
            }
          }
        }
        return true;
      }
      return false;
    }
//places block by seeing if placeable and then looping through to assign the spots on the empty board
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
    //checks if the blocks are placeable by using the board to see if there are already squares occupying it

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
      this.score+=100;
      this.spotCount+=10;
      return clear;
    }
//checks to find the row that is full
    private void clearRow(int i){
      for (int j = 0; j < board[0].length; j++){
        board[i][j] = null;
      }
    }
//clearsRow but making the row on the board contain null and no squares

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
      this.score+=100;
      this.spotCount+=10;
      return clear;
    }
//find column that is full
    private void clearCol(int i){
      for (int j = 0; j < board.length; j++){
        board[j][i] = null;
      }
    }

    public String toString(){
      String s="";//this is the string containing the entire board
      int c=10;
      while (c!=0){
            s=s+"+---+---+---+---+---+---+---+---+---+---+\n";
            if (c==7){
              s=s+"|   |   |   |   |   |   |   |   |   |   |           SCORE:"+this.getScore()"\n";
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
