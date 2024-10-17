import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class MyFrame extends JFrame implements KeyListener{

  public GamePanel Gpanel;
  public MenuPanel Mpanel;
  boolean inMenu;
  int playerCount;
 
 MyFrame(){
  
  Mpanel = new MenuPanel();
  
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  this.add(Mpanel);
  this.pack();
  this.setLocationRelativeTo(null);
  this.addKeyListener(this);
  this.setVisible(true);
  this.toFront();
  this.requestFocus();
  inMenu = true;
  
 }
 @Override
	public void keyTyped(KeyEvent e) {
  }
 @Override
	public void keyPressed(KeyEvent e) {
    //System.out.println(e.getKeyCode());//W - 87, S - 83, up - 38, down - 40, E - 69, enter - 10
    if (inMenu) {
      if (e.getKeyCode() == 40 || e.getKeyCode() == 83) {
        Mpanel.down();
      } else if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {
        Mpanel.up();
      } else if (e.getKeyCode() == 69 || e.getKeyCode() == 10) {
        playerCount = Mpanel.select();
        startUp();
      }
    }
  }
  @Override
	public void keyReleased(KeyEvent e) {
  }
  public void startUp() {
    this.remove(Mpanel);
    Gpanel = new GamePanel(playerCount);
    this.add(Gpanel);
  }
}