import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

Timer timer;
int players;
Paddle rPaddle;
Paddle lPaddle;
Ball ball;

 GamePanel(int inPlayers){
  this.setPreferredSize(new Dimension(1300,800));
  timer = new Timer(5, this);
	timer.start();
  players = inPlayers;
  startUp();
 }
 public void startUp() {
  rPaddle = new Paddle(1250,400, false);
  lPaddle = new Paddle(50,400, false);
  ball = new Ball(650,400);
  if (players == 1) {
    lPaddle.bot = true;
  } else if (players == 0) {
    lPaddle.bot = true;
    rPaddle.bot = true;
    rPaddle.calculateTarget(ball);
  }
 }
 
 public void paint(Graphics g) {
  
  Graphics2D g2D = (Graphics2D) g;

  //Back
  g2D.setPaint(Color.black);
  g2D.fillRect(0, 0, 1300, 800);
  //Line
  g2D.setPaint(Color.white);
  for (int i = 40; i < 800; i+= 80) {
    g2D.fillRect(645, i, 10, 40);
  }
  //Score
  g2D.setFont(new Font("Comic Sans MS", 1,75));
  g2D.drawString(lPaddle.score + "", 550, 80);
  g2D.drawString(rPaddle.score + "", 710, 80);
  //rPaddle
  g2D.fillRect(rPaddle.x, rPaddle.getTop(), rPaddle.depth, rPaddle.height * 2);
  //lPaddle
  g2D.fillRect(lPaddle.x, lPaddle.getTop(), lPaddle.depth, lPaddle.height * 2);
  //ball
  g2D.fillRect(ball.x - 10, ball.getTop(), 10, 10);
 }
  @Override
	public void actionPerformed(ActionEvent e) {
    rPaddle.advance();
    lPaddle.advance();
    ball.advance();
    ballChecks();
    repaint();
  }

  public void ballChecks() {
    //Checking floor && ceiling
    if (ball.getBottom() >= 800) {
      ball.vSpeed = -1 * ball.vSpeed;
    } else if (ball.getTop() <= 0) {
      ball.vSpeed = -1 * ball.vSpeed;
    }
    //Checking right Paddle
    if (ball.x >= rPaddle.x && ball.x <= rPaddle.x + rPaddle.depth) {
      if (ball.getBottom() >= rPaddle.getTop() && ball.getTop() <= rPaddle.getBottom()) {
        ball.hSpeed = ball.hSpeed * -1;
        ball.increaseHSpeed(1);
        if (lPaddle.bot) {
          lPaddle.calculateTarget(ball);
        }
      }
    } else if (ball.x <= lPaddle.x + 20 && ball.x >= lPaddle.x) {//Left paddle
      if (ball.getBottom() >= lPaddle.getTop() && ball.getTop() <= lPaddle.getBottom()) {
        ball.hSpeed = ball.hSpeed * -1;
        ball.increaseHSpeed(1);
        if (rPaddle.bot) {
          rPaddle.calculateTarget(ball);
        }

      }
    }
    //Checking if the ball is off the screen
    if (ball.x < 0) {
      ball = new Ball(650, 400);
      rPaddle.score += 1;
    } else if (ball.x > 1300) {
      ball = new Ball(650, 400);
      lPaddle.score += 1;
    }
  }
}