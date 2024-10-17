import java.awt.*;
import javax.swing.*;

public class MenuPanel extends JPanel {

  int selected;

 MenuPanel(){
  this.setPreferredSize(new Dimension(1300,800));
  selected = 0;
 }
 
 public void paint(Graphics g) {
  
  Graphics2D g2D = (Graphics2D) g;

  g2D.setPaint(Color.black);

  g2D.fillRect(0, 0, 1300, 800);
  //Name
  g2D.setFont(new Font("Comic Sans MS", 1,100));
  g2D.setPaint(Color.white);
  g2D.drawString("PONG 2", 460, 150);
  //Button 1
  g2D.fillRect(450, 200, 400, 100);
  g2D.setColor(Color.black);
  g2D.fillRect(460, 210, 380, 80);
  g2D.setColor(Color.white);
  g2D.setFont(new Font("Comic Sans MS", 1,50));
  g2D.drawString("1 PLAYER", 525, 270);
  //Button 2
  g2D.fillRect(450, 350, 400, 100);
  g2D.setColor(Color.black);
  g2D.fillRect(460, 360, 380, 80);
  g2D.setColor(Color.white);
  g2D.setFont(new Font("Comic Sans MS", 1,50));
  g2D.drawString("2 PLAYERS", 515, 420);
 }
}