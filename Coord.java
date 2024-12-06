public class Coord {//Defining the class
  int x;//X coordinate
  int y;//Y coordinate (lower is vertically higher)

  //Only useful constructor
  Coord(int inX, int inY) {//Takes in and X and Y coordinate
    x = inX;//Setting x coordinate
    y = inY;//Setting y coordinate
  }
  //Shift the x coordinate
  public void changeX(int amount) {//Takes in an amount to change the x coordinate
    x += amount;//Adding the change ammount
    //(If the amount is negative adding it will stil work so this method is both for adding and subtracting coordinates)
  }
  //Shift the y coordinate
  public void changeY(int amount) {//Takes in an amount to change the y coordinate
    y += amount;//Adding the change ammount
    //(If the amount is negative adding it will stil work so this method is both for adding and subtracting coordinates)
  }
}
