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
  private boolean[][] map;
  private int numBlock;
  private int blockType;
  private int size;
  private int c;
  private int r;
  private Terminal.Color coloring;
  public int getNumBlock(){}
  public Terminal.Color getColor(){
    return coloring;
  }
  public int getRow(){
    return r;
  }
  public int getCol(){
    return c;
  }
  public Block(){
    Terminal.Color[] colors = Terminal.Color.values();
    block=new Square[1][1];
    map=new boolean[2][2];
    coloring=colors[(int)(Math.random()*100)%(colors.length-1) + 1];}
  public Block(Terminal.Color c){
    block=new Square[1][1];
    map=new boolean[2][2];
    coloring=c;}

  public boolean getmap(int row, int col){
    return this.map[row][col];
  }

  public Square[][] getBlock(){
    return block;
  }

  public int getBlockType(){
    return blockType;
  }

  public int getLength(){
    return block.length;
  }
  public int getWidth(){
    return block[0].length;
  }

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

class emptyBlock extends Block{
  public String toString(){
    String s = "";
    for (int i = 0; i < 11; i++){
      s += "                     \n";
    }
    return s;
  }
}

class FullBlock extends Block{
  private Square[][] block;
  private boolean[][] map;
  private int numBlock;
  private int pos;
  private int blockType;
  private int size;
  private int c;
  private int r;
  private Terminal.Color coloring;
  public int getNumBlock(){
    return r*c;
  }
  public int getRow(){
    return r;
  }
  public int getCol(){
    return c;
  }
  public Square[][] getBlock(){
    return block;
  }
  public int getLength(){
    return block.length;
  }
  public int getWidth(){
    return block[0].length;
  }
  public boolean getmap(int row, int col){
    return map[row][col];
  }
  public FullBlock(int size,Terminal.Color colors){
    coloring=colors;
    block=new Square[size][size];
    for (int x=0;x<size;x++){
      for (int y=0;y<size;y++){
          block[x][y]=new Square("WHITE",x,y);
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
}

class LongBlock extends Block{
  private Square[][] block;
  private boolean[][] map;
  private int numBlock;
  private int pos;
  private int blockType;
  private int size;
  private int c;
  private int r;
  private Terminal.Color coloring;
  public int getNumBlock(){
    return r*c;
  }
  public int getRow(){
    return r;
  }
  public int getCol(){
    return c;
  }
  public Square[][] getBlock(){
    return block;
  }
  public int getLength(){
    return block.length;
  }
  public int getWidth(){
    return block[0].length;
  }
  public boolean getmap(int row, int col){
    return this.map[row][col];
  }
  public LongBlock(int size,int pos, Terminal.Color colors){
    coloring=colors;
    if (pos==1){
      block=new Square[size][1];
      for (int x=0;x<size;x++){
            block[x][0]=new Square("WHITE",x,0);
          }
      map=new boolean[2*size+1][1];
      for (int a=0;a<map.length;a++){
          map[a][0]=true;
        }
      }
    else{
      block=new Square[1][size];
      for (int x=0;x<size;x++){
            block[0][x]=new Square("WHITE",x,0);
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
}

class LBlock extends Block{
  private Square[][] block;
  private boolean[][] map;
  private int numBlock;
  private int pos;
  private int blockType;
  private int size;
  private int c;
  private int r;
  private Terminal.Color coloring;
  public int getRow(){
    return r;
  }
  public int getCol(){
    return c;
  }
  public Square[][] getBlock(){
    return block;
  }
  public int getLength(){
    return block.length;
  }
  public int getWidth(){
    return block[0].length;
  }
  public boolean getmap(int row, int col){
    return this.map[row][col];
  }
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
          block[x][y]=new Square("WHITE",x,y);
          numBlock++;
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
}
