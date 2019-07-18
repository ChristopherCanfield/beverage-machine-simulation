package edu.bu.met.cs665;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class App extends Component implements MouseListener, KeyListener  {
  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        App app = new App();
        app.createAndShowGUI();
      }});
  }
  
  private JFrame window;
  private Image automaticBeverageMachineImage;
  
  public void createAndShowGUI() {
    //Create and set up the window.
    window = new JFrame("Automatic Beverage Machine");
    window.setUndecorated(true);
    
    window.setSize(400, 500);
    
    window.addKeyListener(this);
    window.addMouseListener(this);
    window.add(this);

    try {
      InputStream imageInputStream = getClass().getClassLoader().getResourceAsStream("assignment-1-beverage-machine.jpg");
      automaticBeverageMachineImage = ImageIO.read(imageInputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    window.setLocationRelativeTo(null);
    window.setVisible(true);
  }
  
  @Override
  public void paint(Graphics g) {
    g.drawImage(automaticBeverageMachineImage, 0, 0, null);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    System.out.println("MouseClicked: " + e.getX() + "," + e.getY());
  }

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {}

  @Override
  public void keyTyped(KeyEvent e) {}
}
