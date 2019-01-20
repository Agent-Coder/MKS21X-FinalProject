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
abstract class Block {

  private Square[][] block;
  //array for block comprised of  squares
  private boolean[][] map;
  //easier printing of block
  private int numSquare;
  //num of squares comprising block
  private int size;
  //size of block
  private int c;
  //number of columns  in map
  private int r;
  //number of rows in map
  private Terminal.Color coloring;
  //color of block
  public int getNumSquare(){
    return numSquare;
  }
  //returns numSquare
  public Terminal.Color getColor(){
    return coloring;
  }
  //returns colors of blocks
  public int getRow(){
    return r;
  }
  //returns number of rows in map
  public int getCol(){
    return c;
  }
  //returns number of cols in map
  public Block(){
    Terminal.Color[] colors = Terminal.Color.values();
    block=new Square[1][1];
    map=new boolean[2][2];
    coloring=colors[(int)(Math.random()*100)%(colors.length-1) + 1];}
 //constructor for block
  public Block(Terminal.Color c){
    block=new Square[1][1];
    map=new boolean[2][2];
    coloring=c;}
//constructor for block taking in an input of color
  public boolean getmap(int row, int col){
    return this.map[row][col];
  }
 //returns a cell of map
  public Square[][] getBlock(){
    return block;
  }
//returns block array

  public int getLength(){
    return block.length;
  }
  //gets length of block
  public int getWidth(){
    return block[0].length;
  }
//gets width of block
  public String toString(){
      String entire="";
      for (int x=0;x<this.map.length;x++){
        String s="";
        String a="";
        for (int y=0;y<this.map[0].length;y++){
            if (x%2==1){
              a="| @ |";
            }
            else{
              a="+---+";
            }
            if (map[x][y]){
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
}
//toString of map
class emptyBlock extends Block{
  public String toString(){
    String s = "";
    for (int i = 0; i < 11; i++){
      s += "                     \n";
    }
    return s;
  }
}
//toString of empty Block
class FullBlock extends Block{
  //sqaure shaped block
  private Square[][] block;
  //array for block comprised of  squares
  private boolean[][] map;
  //easier printing of block
  private int numSquare;
  //num of squares comprising block
  private int size;
  //size of block
  private int c;
  //number of columns  in map
  private int r;
  //number of rows in map
  private Terminal.Color coloring;
  //color of block
  private int pos;
//orientation of block
  public Terminal.Color getColor(){
    return coloring;
  }
  //returns color of block
  public int getNumSquare(){
    return block.length*block.length;
  }
  //returns number of squares the block has
  public int getRow(){
    return r;
  }
  //row for map
  public int getCol(){
    return c;
  }
  //col for map
  public Square[][] getBlock(){
    return block;
  }
  //gets block array
  public int getLength(){
    return block.length;
  }
  //length of block
  public int getWidth(){
    return block[0].length;
  }
  //width of block
  public boolean getmap(int row, int col){
    return map[row][col];
  }
  //specific cell of map
  public FullBlock(int size,Terminal.Color colors){
    coloring=colors;
    block=new Square[size][size];
    for (int x=0;x<size;x++){
      for (int y=0;y<size;y++){
          block[x][y]=new Square(colors);
        }
      }
      map=new boolean[2*size+1][size];
      for (int a=0;a<map.length;a++){
        for (int b=0;b<map[0].length;b++){
          map[a][b]=true;
        }
      }
      r=map.length;
      c=map[0].length;
    }
    //constructor for fullBlock that sets the map and square array
    public String toString(){
        String entire="";
        for (int x=0;x<map.length;x++){
          String s="";
          String a="";
          for (int y=0;y<map[0].length;y++){
              if (x%2==1){
                a="| @ |";
              }
              else{
                a="+---+";
              }
              if (map[x][y]){
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
      //toString of full Block
}

class LongBlock extends Block{
  //a line-shaped block
  private Square[][] block;
  //array for block comprised of  squares
  private boolean[][] map;
  //easier printing of block
  private int numSquare;
  //num of squares comprising block
  private int size;
  //size of block
  private int c;
  //number of columns  in map
  private int r;
  //number of rows in map
  private Terminal.Color coloring;
  //color of block
  private int pos;
//orientation

  public int getNumSquare(){
    return block[0].length;
  }
  //number of squares in block
  public Terminal.Color getColor(){
    return coloring;
  }
  //getting color
  public int getRow(){
    return r;
  }
  //returns map rows
  public int getCol(){
    return c;
  }
  //returns col rows
  public Square[][] getBlock(){
    return block;
  }
  //gets square array of block
  public int getLength(){
    return block.length;
  }
  //gets length of block
  public int getWidth(){
    return block[0].length;
  }
  //gets width of block
  public boolean getmap(int row, int col){
    return this.map[row][col];
  }
  //gets cell of map
  public LongBlock(int size,int pos, Terminal.Color colors){
    coloring=colors;
    if (pos==1){
      block=new Square[size][1];
      for (int x=0;x<size;x++){
            block[x][0]=new Square(colors);
          }
      map=new boolean[2*size+1][1];
      for (int a=0;a<map.length;a++){
          map[a][0]=true;
        }
      }
    else{
      block=new Square[1][size];
      for (int x=0;x<size;x++){
            block[0][x]=new Square(colors);
          }
      map=new boolean[3][size];
      for (int a=0;a<3;a++){
        for (int b=0;b<size;b++){
          map[a][b]=true;
        }
      }
    }
    r=map.length;
    c=map[0].length;
  }
  //constructor of long block
  public String toString(){
      String entire="";
      for (int x=0;x<map.length;x++){
        String s="";
        String a="";
        for (int y=0;y<map[0].length;y++){
            if (x%2==1){
              a="| @ |";
            }
            else{
              a="+---+";
            }
            if (map[x][y]){
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
    //toString
}

class LBlock extends Block{
  private Square[][] block;
  //array for block comprised of  squares
  private boolean[][] map;
  //easier printing of block
  private int numSquare;
  //num of squares comprising block
  private int size;
  //size of block
  private int c;
  //number of columns  in map
  private int r;
  //number of rows in map
  private Terminal.Color coloring;
  //color of block
  private int pos;
//orientation
  public int getNumSquare(){
    return numSquare;
  }
  //gets number of squares in block
  public Terminal.Color getColor(){
    return coloring;
  }
  //gets color
  public int getRow(){
    return r;
  }
  //gets row in map
  public int getCol(){
    return c;
  }
  //gets col in map
  public Square[][] getBlock(){
    return block;
  }
  //gets square array of blocks
  public int getLength(){
    return block.length;
  }
  //gets length of block
  public int getWidth(){
    return block[0].length;
  }
  //gets width of block
  public boolean getmap(int row, int col){
    return this.map[row][col];
  }
  //gets specific cell of map
  public LBlock(int size,int pos, Terminal.Color colors){
    coloring=colors;
    int row,col1;
    block=new Square[size][size];
    map=new boolean[2*size+1][size];
    if(pos==0){
      row=0;
      col1=0;
    }
    else if(pos==1){
      row=size-1;
      col1=0;
    }
    else if(pos==2){
      row=size-1;
      col1=size-1;
    }
    else{
      row=0;
      col1=size-1;
    }
    for (int x=0;x<size;x++){
      for (int y=0;y<size;y++){
        if (x==row||y==col1){
          block[x][y]=new Square(colors);
          numSquare++;
        }
      }
    }
    for (int a=0;a<map.length;a++){
      for (int b=0;b<size;b++){
        if (pos==0&&a>=3&&b>0){
          map[a][b]=false;
        }
        else if(pos==1&&a<map.length-3&&b>0){
          map[a][b]=false;
        }
        else if(pos==2&&a<map.length-3&&b<size-1){
          map[a][b]=false;
        }
        else if(pos==3&&a>=3&&b<size-1){
          map[a][b]=false;
        }
        else{
          map[a][b]=true;
        }
      }
    }
    r=map.length;
    c=map[0].length;
  }
  //constructor for long block
  public String toString(){
      String entire="";
      for (int x=0;x<map.length;x++){
        String s="";
        String a="";
        for (int y=0;y<map[0].length;y++){
            if (x%2==1){
              a="| @ |";
            }
            else{
              a="+---+";
            }
            if (map[x][y]){
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
    //toString for long block
}
