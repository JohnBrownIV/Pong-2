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
  g2D.drawString("2 PLAYERS", 515, 420);
  //Button 3
  g2D.fillRect(450, 500, 400, 100);
  g2D.setColor(Color.black);
  g2D.fillRect(460, 510, 380, 80);
  g2D.setColor(Color.white);
  g2D.drawString("0 PLAYERS", 515, 570);
  switch (selected) {
    case 0:
    g2D.setColor(Color.white);
    g2D.fillRect(450, 200, 400, 100);
    g2D.fillRect(460, 210, 380, 80);
    g2D.setColor(Color.black);
    g2D.drawString("1 PLAYER", 525, 270);
      break;
    case 1:
      g2D.setColor(Color.white);
      g2D.fillRect(450, 350, 400, 100);
      g2D.fillRect(460, 360, 380, 80);
      g2D.setColor(Color.black);
      g2D.drawString("2 PLAYERS", 515, 420);
      break;
    case 2:
      g2D.setColor(Color.white);
      g2D.fillRect(450, 500, 400, 100);
      g2D.fillRect(460, 510, 380, 80);
      g2D.setColor(Color.black);
      g2D.drawString("0 PLAYERS", 515, 570);
      break;
  }
 }
 public void up() {
  selected--;
  if (selected < 0) {
    selected = 2;
  }
  repaint();
 }
 public void down() {
  selected++;
  if (selected > 2) {
    selected = 0;
  }
  repaint();
 }
 public int select() {
  int out = 1;
  switch (selected) {
    case 0:
      out = 1;
      break;
    case 1:
      out = 2;
      break;
    case 2:
      out = 0;
      break;
  }
  return out;
 }
}