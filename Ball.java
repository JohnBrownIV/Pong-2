public class Ball extends Coord implements Cloneable {
  int hSpeed;//Horizontal Speed
  int vSpeed;//Vertical Speed
  Ball(int inX, int inY) {
    super(inX, inY);//Setting up the Coordinate
    hSpeed = 5;//Starting horizontal speed of 5 pixels / frame
    vSpeed = 5;//Starting vertical speed of 5 pixels / frame
  }
  //The ball is 10 pixels tall && the center is in the middle, therefor subtract 5 for top (Lower coordinates are higher)
  public int getTop() {
    return y - 5;
  }
  //The ball is 10 pixels tall && the center is in the middle, therefor add 5 for top (Lower coordinates are higher)
  public int getBottom() {
    return y + 5;
  }
  //This is what the ball does on every frame
  public void advance() {
    changeX(hSpeed);//All the ball does is shift it's speed everything else is handled by other objects
    changeY(vSpeed);
  }
  //Increase the balls horizontal speed
  public void increaseHSpeed(int am) {
    //The ball may be moving backwards, so it may be neccesary to subtract from the ball's speed to make it faster
    if (hSpeed < 0) {
      hSpeed -= am;
    } else {
      hSpeed += am;
    }
  }
  //Increase the balls vertical speed
  public void increaseVSpeed(int am) {
    //The ball may be moving down, so it may be neccesary to subtract from the ball's speed to make it faster
    if (vSpeed < 0) {
      vSpeed -= am;
    } else {
      vSpeed += am;
    }
  }
  //I had to add a clone method because of how the paddle's AI works.
  //Notice how pointless it is
  //I hate Java sometimes
  public Object clone() throws CloneNotSupportedException 
    { 
      return super.clone(); 
    } 
}
