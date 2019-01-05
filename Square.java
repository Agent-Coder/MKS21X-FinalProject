public class Square {
  private String color;
  private int xcor;
  private int ycor;

  public Square(String c, int x, int y){
    color = c;
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
