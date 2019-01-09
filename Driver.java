public class Driver{
  public static void main(String[] args) {
    Board b = new Board();
    System.out.println(b);

    Block a = b.generateBlock();
    System.out.println(a);

    System.out.println(b.placeable(a, 0, 0));
  }
}
