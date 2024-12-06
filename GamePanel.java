import java.awt.*;//Imports java awt (part of timer)
import java.awt.event.*;//Imports java awt.event (part of timeer)
import javax.swing.*;//Imports java swing (used for graphics)

public class GamePanel extends JPanel implements ActionListener {//Defining class, extends JPanel so it can be added to the display frame. Actionlistener allows the timer to work
Timer timer;//The timer that runs frames
int players;//How many players there are
Paddle rPaddle;//The right hand side paddle
Paddle lPaddle;//The top hand side paddle
//^ Nah just screwing with you it's the left paddle
Ball ball;//The ball
int[][] backBinary;//Super rad binary graphics
//^ Crucial to the program not just here because I had to have an array and the rest of this was already done

 GamePanel(int inPlayers){//The constructor, takes in a player count
  this.setPreferredSize(new Dimension(1300,800));//This sets up the panels size
  timer = new Timer(5, this);//Sets up the timer
	timer.start();//starts the timer
  players = inPlayers;//Esablishes player count
  startUp();//Runs the startup
 }
 //This sets my variables, separate block for resets. (This is a practice I have developed in my personal code)
 public void startUp() {//Startup code. Defines most variables so that the game can be reset (I never added resets though so this distinction is somewhat pointless)
  //I have started doing this in most of my code/games though because I do frequently need to reset the code.
  rPaddle = new Paddle(1250,400, false);/*Defining the right paddle. The coordinates are based on mathematics
  I did based on the size of the window which was simple but I do not care to explain. If you would like to figure out what exactly I did, 
  feel free to reverse engineer the process using the panel's scale from the statement defining the panels preffered size.*/
  lPaddle = new Paddle(50,400, false);/*Defining the left paddle. The coordinates are based on mathematics
  I did based on the size of the window which was simple but I do not care to explain. If you would like to figure out what exactly I did, 
  feel free to reverse engineer the process using the panel's scale from the statement defining the panels preffered size.*/
  ball = new Ball(650,400);//Defininf the ball. It's in the middle
  if (players == 1) {//If players has a value of 1
    lPaddle.bot = true;//The left paddle is a bot
  } else if (players == 0) {//If there are no players
    lPaddle.bot = true;//See comment on line 11 * 3
    rPaddle.bot = true;//See comment sent to from comment above, replace second word with "right"
    rPaddle.calculateTarget(ball);//The right paddle will need to identify it's target immediatly so that it does not just lose
  }
  //Setting up the cool binary grapics
  backBinary = new int[29][13];//Defining the awesome binary for the backdrop
  for (int x = 0; x < 29; x++) {//Looping through the awesome binary backdrop's 29 horizontal coordinates
    for (int y = 0; y < 13; y++) {//Looping through the awesome binary backdrop's 13 vertical coordinates
      backBinary[x][y] = randomInt(0, 2);/*Setting the value of the active coordinate to a random 1 or 0 
      (recall that randomInt will never produce the maximum due to my own lazyness)*/
    }//End for loop that cycles y coords
  }//End for loop that cycles x coords
 }//End defining method
 
 public void paint(Graphics g) {//The program's paint code. Uses a graphics object. I do not know where the graphics object comes from
  
  Graphics2D g2D = (Graphics2D) g;//Immediatly replace the Graphics object with the superior graphics2D object

  //Back
  g2D.setPaint(Color.black);//Setting the paint color to the ideal color of the programs backdrop for backdrop painting
  g2D.fillRect(0, 0, 1300, 800);//Filling a rectangle with the previously chosen color for the backdrop's color
  //The binary
  g2D.setFont(new Font("Comic Sans MS", 1,50));//Set the font to the correct font (I use Comic Sans MS)
  g2D.setPaint(new Color(0, 50, 0));//set the paint to a dark green color so that it does not make the game unplayably horrible to look at
  for (int x = 0; x < 29; x++) {//Looping through the awesome binary backdrop's 29 horizontal coordinates
    for (int y = 0; y < 13; y++) {//Looping through the awesome binary backdrop's 13 vertical coordinates
      g2D.drawString("" + backBinary[x][y],x * 45, (y + 1) * 60);/*Drawing the binary as a string. I have to add a string to it due to how the drawString method worls. 
      The binary's x position is multiplied by 45 to shift the binary into the ideal grid. The binary's y position is multiplied by 60 to place the binary's 
      rows into the ideal distance apart. One is added to the y beforehand so that no numbers are drawn off of the screen
      *///End of comment
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
    //Left
    if (ball.x < 0) {
      ball = new Ball(650, 400);
      rPaddle.score += 1;
      if (rPaddle.bot) {
        rPaddle.calculateTarget(ball);
      }
    //Right
    } else if (ball.x > 1300) {
      ball = new Ball(650, 400);
      lPaddle.score += 1;
      if (rPaddle.bot) {
        rPaddle.calculateTarget(ball);
      }
    }
  }
  //Make a random integer (will never actually return the max because I couldn't be bothered)
  private int randomInt(int min, int max) {
    return (int)((Math.random() * max) + min);
  }
}