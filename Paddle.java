public class Paddle extends Coord {//Defining the class
  final int height = 50;//The paddles height (Only changes for playtesting) default is 50
  final int depth = 10;//The paddle's depth/width (Only changes for playtesting) default is 10
  int move;
  int speed;
  int score;
  boolean bot;
  int target;

  Paddle(int inX, int inY, boolean inBot) {
    super(inX,inY);
    move = 0;
    speed = 7;
    bot = inBot;
    score = 0;
    target = 400;
  }
  public int getTop() {
    return y - height;
  }
  public int getBottom() {
    return y + height;
  }
  public void advance() {
    if (bot) {
      botDecide();
    }
    advanceMove();
  }
  public void botDecide() {
    if (target < getTop() + 10) {
      move = 1;
    } else if (target > getBottom() - 10) {
      move = -1;
    } else {
      move = 0;
    }
  }
  public void calculateTarget(Ball inBall) {
    //System.out.println("TARGETTING");
    Ball ball = new Ball(0,0);
    try {
      ball = (Ball)inBall.clone();
    } catch (Exception CloneNotSupportedException) {
    }
    if (x < 650) {//left
      while (ball.x > x) {
        ball.advance();
        if (ball.getBottom() >= 800) {
          ball.vSpeed = -1 * ball.vSpeed;
        } else if (ball.getTop() <= 0) {
          ball.vSpeed = -1 * ball.vSpeed;
        }
      }
      target = ball.y;
    } else {
      while (ball.x < x) {
        ball.advance();
        if (ball.getBottom() >= 800) {
          ball.vSpeed = -1 * ball.vSpeed;
        } else if (ball.getTop() <= 0) {
          ball.vSpeed = -1 * ball.vSpeed;
        }
      }
      target = ball.y;
      //System.out.println("Y - " + target + " + x - " + x);
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
