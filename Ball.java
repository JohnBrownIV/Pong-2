public class Ball extends Coord implements Cloneable {
  int hSpeed;
  int vSpeed;
  Ball(int inX, int inY) {
    super(inX, inY);
    hSpeed = 5;
    vSpeed = 5;
  }
  public int getTop() {
    return y - 5;
  }
  public int getBottom() {
    return y + 5;
  }
  public void advance() {
    changeX(hSpeed);
    changeY(vSpeed);
  }
  public void increaseHSpeed(int am) {
    if (hSpeed < 0) {
      hSpeed -= am;
    } else {
      hSpeed += am;
    }
  }
  public void increaseVSpeed(int am) {
    if (vSpeed < 0) {
      vSpeed -= am;
    } else {
      vSpeed += am;
    }
  }
  public Object clone() throws CloneNotSupportedException 
    { 
        return super.clone(); 
    } 
}
