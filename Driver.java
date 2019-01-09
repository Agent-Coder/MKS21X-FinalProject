public class Driver{
  public static void main(String[] args) {
    Board b = new Board();
    //System.out.println(b);

    Block a = new LBlock(1, 2);
    //System.out.println(a);

    System.out.println(b.placeBlock(a, 0, 0));
  }
}
