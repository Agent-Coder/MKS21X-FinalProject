import com.googlecode.lanterna.terminal.Terminal.Color;
public class Square {
  private Enum color;
  private int xcor;
  private int ycor;
  public Square(int x, int y){
    Enum<Terminal.Color>[] colors= Terminal.Color.values();
    color = colors[(int)(Math.random()*100%color.length)];
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

  public Enum getColor(){
    return color;
  }

  public String toString(){
    return "+===+\n| @ |\n+===+";
  }
}
