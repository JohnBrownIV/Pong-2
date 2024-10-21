public class Ball extends Coord {
  int hSpeed;
  int vSpeed;
  Ball(int inX, int inY) {
    super(inX, inY);
  }
  public int getTop() {
    return y - 5;
  }
  public int getBottom() {
    return y + 5;
  }
  public void advance() {

  }
}
