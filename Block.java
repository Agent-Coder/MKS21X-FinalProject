abstract class Block {

  private Square[][] block;
  private boolean[][] map;
  private int numBlock;
  private int pos;
  private int blockType;
  private int size;

  public Block(){
    blockType=(int)(Math.random()*100)%4;
    pos=(int)(Math.random()*100)%4;
    if (blockType==0){
      size=(int)(Math.random()*100)%3;
      Block x=new FullBlock(1);
    }
    /*block=new Square[3][3];
    map=new boolean[7][3];
    block[0][0]=new Square("WHITE",0,0);
    for (int x=0;x<3;x++){
      for (int y=0;y<3;y++){
        int w=(int)(Math.random()*100)%2;
        if (w==1){
          block[x][y]=new Square("WHITE",x,y);
          numBlocks++;
        }
      }
    }
    for (int a=0;a<3;a++){
      for (int b=0;b<3;b++){
        boolean p=false;
        if (block[a][b]!=null){
          p=true;
        }
        if (a==0){
          map[a][b]=p;
          map[a+1][b]=map[a][b];
          map[a+2][b]=map[a][b];
        }
        else{
          map[2*a+1][b]=p;
          map[2*a][b]=(map[2*a+1][b]||map[2*a-1][b]);
          map[2*a+2][b]=map[2*a+1][b];
        }
      }
    }
    pos=((int)(Math.random()*100))%4;*/
  /*  block=new Square[5][5];
    map = new boolean[11][5];

    blockType = 3;//(int)(Math.random() * 9 + 1);
    if (blockType == 1){
      block[0][0]= new Square("white", 0, 0);
      for (int i = 0; i < 3; i++){
        map[i][0] = true;
      }
    }
    if (blockType == 2){
      for (int i = 0; i < 2; i++){
        block[i][0] = new Square("white", 0, 0);
        map[i][0] = true;
      }
      for (int i = 2; i < 5; i++){
        map[i][0] = true;
      }
    }
    if (blockType == 3){
      for (int i = 0; i < 3; i++){
        block[i][0] = new Square("white", 0, 0);
      }
      for (int i = 3; i < 7; i++){
        map[i][0] = true;
      }
    }
    if (blockType == 4){
      for (int i = 0; i < 4; i++){
        block[i][0] = new Square("white", 0, 0);
      }
      for (int i = 4; i < 9; i++){
        map[i][0] = true;
      }
    }
    if (blockType == 5){
      for (int i = 0; i < 5; i++){
        block[i][0] = new Square("white", 0, 0);
      }
      for (int i = 5; i < 11; i++){
        map[i][0] = true;
      }
    }
    if (blockType == 6){
      for (int i = 0; i < 2; i++){
        for (int j = 0; j < 2; i++){
          block[i][j] = new Square("white", 0, 0);
          map[i][j] = true;
        }
      }
      for (int i = 2; i < 5; i++){
        for (int j = 0; j < 2; i++){
          map[i][j] = true;
        }
      }
    }
    if (blockType == 7){
      for (int i = 0; i < 3; i++){
        for (int j = 0; j < 3; i++){
          block[i][j] = new Square("white", 0, 0);
          map[i][j] = true;
        }
      }
      for (int i = 3; i < 7; i++){
        for (int j = 0; j < 5; i++){
          map[i][j] = true;
        }
      }
    }
    /*if (blockType == 8){
      for (int i = 0; i < 2; i++){
        for (int j = 0; j < 2; i++){
          block[i][j] = new Square("white", 0, 0);
          map[i][j] = true;
        }
      }
      block[1][1]= null;
      map[1][1] = false;
    }
    if (blockType == 9){
      for (int i = 0; i < 2; i++){
        for (int j = 0; j < 2; i++){
          block[i][j] = new Square("white", 0, 0);
          map[i][j] = true;
        }
      }
      for (int i = 1; i < 3; i++){
        for (int j = 1; j < 3; i++){
          block[i][j] = null;
          map[i][j] = false;
        }
      }
    }*/
  }
  //block constructor has to have at least one square
  //thus first cell of array has to have a square (for now using a 3by3 as max)
  //then loop through and randomize with 50% chance
  //for now assign color to be white and the xcor and ycor will be based on their cell index
  //count number of Blocks each time we make a square part of this block
  //the position will be a random number from 0-3

  public int getBlockType(){
    return blockType;
  }

  public String toString(){
    String entire="";
    for (int x=0;x<map.length;x++){
      String s="";
      String a="";
      for (int y=0;y<map[0].length;y++){
          if (x%2==1){
            a="|   |";
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

public static void main(String[] args) {
  LBlock a=new LBlock(2,0);
  LBlock b=new LBlock(2,1);
  LBlock c=new LBlock(2,2);
  LBlock d=new LBlock(2,3);
  LBlock e=new LBlock(3,0);
  LBlock f=new LBlock(3,1);
  LBlock g=new LBlock(3,2);
  LBlock h=new LBlock(3,3);
  System.out.println(a);
  System.out.println(b);
  System.out.println(c);
  System.out.println(d);
  System.out.println(e);
  System.out.println(f);
  System.out.println(g);
  System.out.println(h);
 }
}
class FullBlock extends Block{
  private Square[][] block;
  private boolean[][] map;
  public FullBlock(int size){
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
    }
    public String toString(){
      String entire="";
      for (int x=0;x<map.length;x++){
        String s="";
        String a="";
        for (int y=0;y<map[0].length;y++){
            if (x%2==1){
              a="|   |";
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
  public LongBlock(int size,int pos){
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
  }
    public String toString(){
      String entire="";
      for (int x=0;x<map.length;x++){
        String s="";
        String a="";
        for (int y=0;y<map[0].length;y++){
            if (x%2==1){
              a="|   |";
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
  public Square[][] block;
  private boolean[][] map;
  public LBlock(int size,int pos){
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
  }
    public String toString(){
      String entire="";
      for (int x=0;x<map.length;x++){
        String s="";
        String a="";
        for (int y=0;y<map[0].length;y++){
            if (x%2==1){
              a="|   |";
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
