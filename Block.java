public class Block {

  private Square[][] block;
  private int numBlocks;
  private int pos;

  public Block(String shape, int pos, String color){
    if (shape.equals("1block")){
      block = new Square[1][1];
      block[1][1] = new Square(color, 0, 0);
    }
    if (shape.equals("2block")){
      if (pos == 1){

      } else {

      }
    }
    if (shape.equals("3block")){
      if (pos == 1){

      } else {

      }
    }
    if (shape.equals("4block")){
      if (pos == 1){

      } else {

      }
    }
    if (shape.equals("5block")){
      if (pos == 1){

      } else {

      }
    }
    if (shape.equals("4sqaure"){

    }
    if (shape.equals("9sqaure")){

    }
  }
}
