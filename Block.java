import java.lang.Math;
public class Block {

  private Square[][] block;
  private int numBlocks;
  private int pos;
  public Block(){
    block[0][0]=new Square("WHITE",0,0);
    for (int x=0;x<3;x++){
      for (int y=1;y<3;y++){
        if (((int)(Math.random()*100))%2==0);{
          block[x][y]=new Square("WHITE",x,y);
          numBlocks++;
        }
      }
    }
    pos=((int)(Math.random()*100))%4;
  }
  //block constructor has to have at least one square
  //thus first cell of array has to have a square (for now using a 3by3 as max)
  //then loop through and randomize with 50% chance
  //for now assign color to be white and the xcor and ycor will be based on their cell index
  //count number of Blocks each time we make a square part of this block
  //the position will be a random number from 0-3
}
