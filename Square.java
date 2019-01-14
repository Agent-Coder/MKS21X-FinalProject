
public class Square {
  private int xcor;
  private int ycor;
  public Square(String color, int x, int y){
    xcor = x;
    ycor = y;
  }

  public void setXcor(int x){
    xcor = x;
  }
  public void setYcor(int y){
    ycor = y;
  }

  public int getXcor(){
    return xcor;
  }
  public int getYcor(){
    return ycor;
  }


  public String toString(){
    return "+===+\n| @ |\n+===+";
  }
}
