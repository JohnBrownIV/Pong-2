public class Coord {
  int x;
  int y;
  Coord(int inX, int inY) {
    x = inX;
    y = inY;
  }
  public void changeX(int amount) {
    x += amount;
  }
  public void changeY(int amount) {
    y += amount;
  }
}
