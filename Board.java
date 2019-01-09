public class Board {
  private int spotCount;
  private int blockCount;
  private int score;
  private Square[][] board;
  private boolean[][] layout;

  public Board(){
    board = new Square[10][10];
    layout= new boolean[21][10];
    for (int a=0;a<layout.length;a++){
      for (int b=0;b<layout[0].length;b++){
        layout[a][b]=false;
      }
    }
  }

  public static String PrintSelection(boolean[][] selection){
      String entire="";
      for (int x=0;x<selection.length;x++){
        String s="";
        String a="";
        for (int y=0;y<selection[0].length;y++){
            if (x%2==1){
              a="|   |";
            }
            else{
              a="+---+";
            }
            if (selection[x][y]){
              if (y!=0){
                s=s.substring(0,s.length()-1);
              }
              s+=a;
            }
            else{
              if (y==0){
                s+="     ";
              }
              else{
                s+="    ";
              }
            }
          }
          entire+=s+"\n";
        }

      return entire;
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
      int tempx=x;
      int tempy=y;
      if (placeable(b, x, y)){
        for (int i = 0; i < b.getLength(); i++){
          for (int j = 0; j < b.getWidth(); j++){
            board[tempx][tempy] = b.getBlock()[i][j];
            tempy++;
          }
          tempx++;
        }
        layout=new boolean[21][10];
        for (int r=0;r<b.getRow();r++){
          for (int s=0;s<b.getCol();s++){
            layout[x][y]=b.getmap(r,s);
          }
        }
        return true;
      }
      return false;
    }

    private boolean placeable(Block bl, int x, int y){
      int a = 0;
      int b = 0;
      //System.out.println(board.length);
      //System.out.println(y+bl.getWidth());
      for (int i = x; a < bl.getLength() && i < x+bl.getLength(); i++){
        a = 0;
        for (int j = y; b < bl.getWidth() && j < y+bl.getWidth(); j++){
          b = 0;
          //System.out.println("a: " + a + "\nb: " + b + "\ni: " + i + "\nj: " + j);
          //System.out.println(board[0][0]);
          //System.out.println(bl.getBlock()[a][b] != null);
          if (board[i][j] != null && bl.getBlock()[a][b] != null){
            return false;
          }
          b++;
        }
        a++;
      }
      return true;
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
      return s;
    }

  public static void main(String[] args) {
    Block a=generateBlock();
    Block b=generateBlock();
    Block c=generateBlock();
    boolean[][] f=blockSelection(a,b,c);
    System.out.println(a);
  }
}
