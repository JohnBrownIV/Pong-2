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
Coord backFlip;
//^ Crucial to the program not just here because I had to have an array and the rest of this was already done
//Blank line for space
 GamePanel(int inPlayers){//The constructor, takes in a player count
  this.setPreferredSize(new Dimension(1300,800));//This sets up the panels size
  this.setBackground(Color.black);
  timer = new Timer(5, this);//Sets up the timer
	timer.start();//starts the timer
  players = inPlayers;//Esablishes player count
  startUp();//Runs the startup
 }//End curly bracket
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
  }//End curly bracket
  //Setting up the cool binary grapics
  backBinary = new int[29][13];//Defining the awesome binary for the backdrop
  for (int x = 0; x < 29; x++) {//Looping through the awesome binary backdrop's 29 horizontal coordinates
    for (int y = 0; y < 13; y++) {//Looping through the awesome binary backdrop's 13 vertical coordinates
      backBinary[x][y] = randomInt(0, 2);/*Setting the value of the active coordinate to a random 1 or 0 
      (recall that randomInt will never produce the maximum due to my own lazyness)*/
    }//End for loop that cycles y coords
  }//End for loop that cycles x coords
  backFlip = new Coord(0, 0);
 }//End defining method
 //Blank line for space
 public void paint(Graphics g) {//The program's paint code. Uses a graphics object. I do not know where the graphics object comes from
  //Blank line for space
  Graphics2D g2D = (Graphics2D) g;//Immediatly replace the Graphics object with the superior graphics2D object
//Blank line for space
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
    }//End curly bracket
  }//End curly bracket
  //g2D.setColor(Color.red);
  //g2D.drawRect(getBinaryBallCoord().x * 45, getBinaryBallCoord().y * 60, 45, 60);//Debug ball scan
  //Line
  g2D.setPaint(Color.white);//Set color to white
  for (int i = 40; i < 800; i+= 80) {//Loop to paint the dashed line in board center
    g2D.fillRect(645, i, 10, 40);//Paint a rectangle at coorect position to create the effect of a dashed line
  }//End curly bracket
  //Score (and formatting)
  g2D.setFont(new Font("Comic Sans MS", 1,75));//Set the font to the correct font (I use Comic Sans MS). This changes the size
  g2D.drawString(String.format("%02d", lPaddle.score), 545, 80);//Paint the left paddle's score
  g2D.drawString(String.format("%02d", rPaddle.score), 665, 80);//Paint the right paddle's score
  //rPaddle
  g2D.fillRect(rPaddle.x, rPaddle.getTop(), rPaddle.depth, rPaddle.height * 2);//Paints the right paddle at the right paddle's location using it's height and depth
  //lPaddle
  g2D.fillRect(lPaddle.x, lPaddle.getTop(), lPaddle.depth, lPaddle.height * 2);//Paints the left paddle at the left paddle's location using it's height and depth
  //ball
  g2D.fillRect(ball.x - 10, ball.getTop(), 10, 10);//Paints the ball at the ball's location using it's height and depth
 }//End curly bracket
  @Override//This is the action the timer runs AKA a frame
	public void actionPerformed(ActionEvent e) {//Frame action. It's an action that's performed.
    rPaddle.advance();//Run the right paddle's advance code
    lPaddle.advance();//Run the left paddle's advance code
    ball.advance();//Run the ball's advance code
    ballChecks();//Run the ball check code (handeled at this level for reasons I forgot)
    for (int i = 0; i < rPaddle.score + lPaddle.score + 1; i++) {//Shifts some binary, more shifts the higher score to increase tension
      backBinary[randomInt(0, 29)][randomInt(0, 13)] = randomInt(0, 2);//Shuffle a random back binary's digits a bit
    }//End curly bracket
    binaryTrail();
    repaint();//Repaint the panel
  }//End curly bracket
//Blank line for space
  public void ballChecks() {//The ball checks
    //Checking floor && ceiling
    if (ball.getBottom() >= 800) {//If the ball is below the screen
      ball.vSpeed = -1 * ball.vSpeed;//Bounce
      //Randomly changing the vertical speed
      if (randomInt(1, 2) == 1) {//Changing speed
        if (randomInt(1, 2) == 1) {//up or down
          ball.vSpeed += 1;//Speed up
        } else {//Else
          ball.vSpeed -= 1;//Speed up but backwards because it's moving reverse
        }//End curly bracket
        if (lPaddle.bot && ball.hSpeed < 0) {//If the left paddle is a bot and the ball is moving toward it
          lPaddle.calculateTarget(ball);//Calculate the left paddle's target
        } else if (rPaddle.bot && ball.hSpeed > 0) {//If the right paddle is a bot and the ball is moving toward it
          rPaddle.calculateTarget(ball);//Calculate the right paddle's target
        }//End curly bracket
      }//End curly bracket
    } else if (ball.getTop() <= 0) {//If the ball is above the screen
      ball.vSpeed = -1 * ball.vSpeed;//Bounce
      if (randomInt(1, 2) == 1) {//Changing speed
        if (randomInt(1, 2) == 1) {//up or down
          ball.vSpeed += 1;//Increase speed
        } else {//Else
          ball.vSpeed -= 1;//Speed up but backwards because it's moving reverse
        }//End curly bracket
        if (lPaddle.bot && ball.hSpeed < 0) {//If the left paddle is a bot and the ball is moving toward it
          lPaddle.calculateTarget(ball);//Calculate the left paddle's target
        } else if (rPaddle.bot && ball.hSpeed > 0) {//If the right paddle is a bot and the ball is moving toward it
          rPaddle.calculateTarget(ball);//Calculate the right paddle's target
        }//End curly bracket
      }//End curly bracket
    }//End curly bracket
    //Checking right Paddle
    if (ball.x >= rPaddle.x && ball.x <= rPaddle.x + ball.hSpeed) {//Gonna be so honest I don't know what this part does
      if (ball.getBottom() >= rPaddle.getTop() && ball.getTop() <= rPaddle.getBottom()) {//OH I'm checking if it is within the bounds of the paddle
        ball.hSpeed = ball.hSpeed * -1;//reverse the ball
        //handling vertical bounce direction
        if (rPaddle.move != 0) {//if the right paddle is moving
          ball.vSpeed = Math.abs(ball.vSpeed) * rPaddle.move * -1;//Bounce ball against paddle move direction
        }//End curly bracket
        ball.increaseHSpeed(1);//Increase speed. Wait I made this a method? Pretend I've been using this
        if (lPaddle.bot) {//if left paddle is bot
          lPaddle.calculateTarget(ball);//calculate left paddle's target
        }//End curly bracket
      }//End curly bracket
    } else if (ball.x <= lPaddle.x + 20 && ball.x >= lPaddle.x + ball.hSpeed) {//Left paddle
      if (ball.getBottom() >= lPaddle.getTop() && ball.getTop() <= lPaddle.getBottom()) {//check if the ball is below the top of the left paddle and above the bottom
        ball.hSpeed = ball.hSpeed * -1;//reverse horizontal speed
        //handling vertical bounce direction
        if (lPaddle.move != 0) {//if the paddle is in motion
          ball.vSpeed = Math.abs(ball.vSpeed) * lPaddle.move * -1;//Set the balls vert direction to the reverse of the paddle
        }//End curly bracket
        ball.increaseHSpeed(1);//Increase the horizontal speed
        if (rPaddle.bot) {//if right paddle is a bot
          rPaddle.calculateTarget(ball);//calculate right paddle's target
        }//End curly bracket
      }//End curly bracket
    }//End curly bracket
    //Checking if the ball is off the screen
    //Left
    if (ball.x < 0) {//if ball is offscreen to the left
      ball = new Ball(650, 400);//reset ball
      ball.hSpeed = -5;//Flip its speed to serve to failure
      rPaddle.score += 1;//add score
      if (rPaddle.bot) {//if right paddle is bot
        rPaddle.calculateTarget(ball);//calculate right padddle's target (ball defaults to this direction)
      }//End curly bracket
    //Right
    } else if (ball.x > 1300) {//if ball is offscreen to the right
      ball = new Ball(650, 400);//reset ball
      lPaddle.score += 1;//add score
      if (rPaddle.bot) {//if right paddle is bot
        rPaddle.calculateTarget(ball);//calculate right padddle's target (ball defaults to this direction)
      }//End curly bracket
    }//End curly bracket
  }//End curly bracket
  //Make a random integer (will never actually return the max because I couldn't be bothered)
  private int randomInt(int min, int max) {//declare method
    return (int)((Math.random() * max) + min);//generate random number and return it
  }//End curly bracket
  private void binaryTrail() {
    Coord temp = getBinaryBallCoord();
    backBinary[temp.x][temp.y] = 0;
  }
  private Coord getBinaryBallCoord() {
    Coord re = new Coord(ball.x / 45, (ball.y / 60));
    if (re.y == 13) {
      re.y = 12;
    }
    return re;
  }
}//End curly bracket