import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class MyFrame extends JFrame implements KeyListener{

  public GamePanel Gpanel;
  public MenuPanel Mpanel;
 
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
  
 }
 @Override
	public void keyTyped(KeyEvent e) {
  }
 @Override
	public void keyPressed(KeyEvent e) {
    //System.out.println(e.getKeyCode());
  }
  @Override
	public void keyReleased(KeyEvent e) {
  }
}