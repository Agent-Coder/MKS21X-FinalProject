public class Driver{
  public static void main(String[] args) {
    Game g = new Game();
    Board b = new Board();
    System.out.println(b);

    Block a = b.generateBlock();
    System.out.println(a);

    System.out.println(g.drawBlock(a.toString(), 1));
  }
}
