import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

Timer timer;
int players;
Paddle rPaddle;
Paddle lPaddle;
Ball ball;
int[][] backBinary;

 GamePanel(int inPlayers){
  this.setPreferredSize(new Dimension(1300,800));
  timer = new Timer(5, this);
	timer.start();
  players = inPlayers;
  startUp();
 }
 //This sets my variables, separate block for resets. (This is a practice I have developed in my personal code)
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
  //Setting up the cool binary grapics
  backBinary = new int[29][13];
  for (int x = 0; x < 29; x++) {
    for (int y = 0; y < 13; y++) {
      backBinary[x][y] = randomInt(0, 2);
    }
  }
 }
 
 public void paint(Graphics g) {
  
  Graphics2D g2D = (Graphics2D) g;

  //Back
  g2D.setPaint(Color.black);
  g2D.fillRect(0, 0, 1300, 800);
  //The binary
  g2D.setFont(new Font("Comic Sans MS", 1,50));
  g2D.setPaint(new Color(0, 50, 0));
  for (int x = 0; x < 29; x++) {
    for (int y = 0; y < 13; y++) {
      g2D.drawString("" + backBinary[x][y],x * 45, (y + 1) * 60);
    }
  }
  //Line
  g2D.setPaint(Color.white);
  for (int i = 40; i < 800; i+= 80) {
    g2D.fillRect(645, i, 10, 40);
  }
  //Score (and formatting)
  g2D.setFont(new Font("Comic Sans MS", 1,75));
  g2D.drawString(String.format("%02d", lPaddle.score), 545, 80);
  g2D.drawString(String.format("%02d", rPaddle.score), 665, 80);
  //rPaddle
  g2D.fillRect(rPaddle.x, rPaddle.getTop(), rPaddle.depth, rPaddle.height * 2);
  //lPaddle
  g2D.fillRect(lPaddle.x, lPaddle.getTop(), lPaddle.depth, lPaddle.height * 2);
  //ball
  g2D.fillRect(ball.x - 10, ball.getTop(), 10, 10);
 }
  @Override//This is the action the timer runs AKA a frame
	public void actionPerformed(ActionEvent e) {
    rPaddle.advance();
    lPaddle.advance();
    ball.advance();
    ballChecks();
    for (int i = 0; i < rPaddle.score + lPaddle.score + 1; i++) {//Shifts some binary, more shifts the higher score to increase tension
      backBinary[randomInt(0, 29)][randomInt(0, 13)] = randomInt(0, 2);
    }
    repaint();
  }

  public void ballChecks() {
    //Checking floor && ceiling
    if (ball.getBottom() >= 800) {
      ball.vSpeed = -1 * ball.vSpeed;
      //Randomly changing the vertical speed
      if (randomInt(1, 2) == 1) {//Changing speed
        if (randomInt(1, 2) == 1) {//up or down
          ball.vSpeed += 1;
        } else {
          ball.vSpeed -= 1;
        }
        if (lPaddle.bot && ball.hSpeed < 0) {
          lPaddle.calculateTarget(ball);
        } else if (rPaddle.bot && ball.hSpeed > 0) {
          rPaddle.calculateTarget(ball);
        }
      }
    } else if (ball.getTop() <= 0) {
      ball.vSpeed = -1 * ball.vSpeed;
      if (randomInt(1, 2) == 1) {//Changing speed
        if (randomInt(1, 2) == 1) {//up or down
          ball.vSpeed += 1;
        } else {
          ball.vSpeed -= 1;
        }
        if (lPaddle.bot && ball.hSpeed < 0) {
          lPaddle.calculateTarget(ball);
        } else if (rPaddle.bot && ball.hSpeed > 0) {
          rPaddle.calculateTarget(ball);
        }
      }
    }
    //Checking right Paddle
    if (ball.x >= rPaddle.x && ball.x <= rPaddle.x + ball.hSpeed) {
      if (ball.getBottom() >= rPaddle.getTop() && ball.getTop() <= rPaddle.getBottom()) {
        ball.hSpeed = ball.hSpeed * -1;
        //handling vertical bounce direction
        if (rPaddle.move != 0) {
          ball.vSpeed = Math.abs(ball.vSpeed) * rPaddle.move * -1;
        }
        ball.increaseHSpeed(1);
        if (lPaddle.bot) {
          lPaddle.calculateTarget(ball);
        }
      }
    } else if (ball.x <= lPaddle.x + 20 && ball.x >= lPaddle.x + ball.hSpeed) {//Left paddle
      if (ball.getBottom() >= lPaddle.getTop() && ball.getTop() <= lPaddle.getBottom()) {
        ball.hSpeed = ball.hSpeed * -1;
        //handling vertical bounce direction
        if (lPaddle.move != 0) {
          ball.vSpeed = Math.abs(ball.vSpeed) * lPaddle.move * -1;
        }
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
      if (rPaddle.bot) {
        rPaddle.calculateTarget(ball);
      }
    } else if (ball.x > 1300) {
      ball = new Ball(650, 400);
      lPaddle.score += 1;
      if (rPaddle.bot) {
        rPaddle.calculateTarget(ball);
      }
    }
  }
  private int randomInt(int min, int max) {
    return (int)((Math.random() * max) + min);
  }
}