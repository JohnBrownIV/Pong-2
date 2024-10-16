import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener {

Timer timer;

 
 MyPanel(boolean players){
  
  //image = new ImageIcon("sky.png").getImage();
  this.setPreferredSize(new Dimension(1300,800));
  timer = new Timer(5, this);
	timer.start();
 }
 
 public void paint(Graphics g) {
  
  Graphics2D g2D = (Graphics2D) g;

  g2D.setPaint(Color.black);

  g2D.fillRect(0, 0, 1300, 800);
 }
  @Override
	public void actionPerformed(ActionEvent e) {
    repaint();
  }
}