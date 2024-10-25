public class Paddle extends Coord {
  int height;
  int depth;
  int move;
  int speed;
  int score;
  boolean bot;

  Paddle(int inX, int inY, boolean inBot) {
    super(inX,inY);
    height = 50;
    depth = 10;
    move = 0;
    speed = 5;
    bot = inBot;
    score = 0;
  }
  public int getTop() {
    return y - height;
  }
  public int getBottom() {
    return y + height;
  }
  public void advance(int bX, int bY, boolean bDirect) {
    if (bot) {
      botDecide(bX, bY, bDirect);
    }
    advanceMove();
  }
  public void botDecide(int bX, int bY, boolean bDirect) {
    if (bY < getTop()) {
      move = 1;
    } else if (bY > getBottom()) {
      move = -1;
    } else {
      move = 0;
    }
  }
  public void advanceMove() {
    if (move != 0) {
      changeY(-1 * move * speed);
      if (getTop() < 0) {
        y = height;
      } else if (getBottom() > 800) {
        y = 800 - height;
      }
    }
  }
}
