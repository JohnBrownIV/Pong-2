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
  g2D.setFont(new Font("Comic Sans", 1,100));
  g2D.setPaint(Color.white);
  g2D.drawString("PONG 2", 460, 150);
  //Button 1
  g2D.fillRect(450, 200, 400, 100);
  g2D.setColor(Color.black);
  g2D.fillRect(460, 210, 380, 80);
 }
}