package edu.bu.met.cs665;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import org.apache.log4j.Logger;
import edu.bu.met.cs665.bev.controller.BeverageController;
import edu.bu.met.cs665.bev.controller.BeverageController.State;
import edu.bu.met.cs665.bev.controller.BeverageControllerObserver;
import edu.bu.met.cs665.bev.controller.BeverageOrder;
import edu.bu.met.cs665.bev.controller.GreenTeaBeverage;
import edu.bu.met.cs665.bev.hardware.CompletedOrder;
import edu.bu.met.cs665.bev.hardware.HardwareInterface;
import edu.bu.met.cs665.bev.hardware.MockHardwareInterface;

public class App extends Component implements MouseListener, KeyListener, BeverageControllerObserver  {
  private static final long serialVersionUID = 1L;
  
  private static final Logger logger = Logger.getLogger(App.class);

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        App app = new App();
        app.start();
      }});
  }
  
  private JFrame window;
  
  private Image currentImage;
  private Image automaticBeverageMachineImage;
  private Image automaticBeverageMachineImageWithCoffee;
  
  private Rectangle brewButton = new Rectangle(159, 358, 98, 47);
  
  private HardwareInterface hardwareInterface;
  private BeverageController controller;
  
  public void start() {
    hardwareInterface = new MockHardwareInterface(1_500);
    controller = new BeverageController(hardwareInterface);
    controller.addObserver(this);
    
    loadImages();
    currentImage = automaticBeverageMachineImage;
    
    createWindow();
    window.setVisible(true);
  }
  
  private void loadImages() {
    try {
      InputStream imageInputStream = getClass().getClassLoader().getResourceAsStream("assignment-1-beverage-machine.png");
      automaticBeverageMachineImage = ImageIO.read(imageInputStream);
      
      imageInputStream = getClass().getClassLoader().getResourceAsStream("assignment-1-beverage-machine-with-drink.png");
      automaticBeverageMachineImageWithCoffee = ImageIO.read(imageInputStream);
    } catch (IOException e) {
      logger.error(e);
      // TODO: It would probably be better to crash here, since the image wasn't found.
    }    
  }
  
  private void createWindow() {
    window = new JFrame("Automatic Beverage Machine");
    window.setUndecorated(true);
    
    window.setSize(400, 500);
    
    window.addKeyListener(this);
    window.addMouseListener(this);
    window.add(this);

    window.setLocationRelativeTo(null);
    window.setVisible(true);
  }
  
  @Override
  public void paint(Graphics g) {
    g.drawImage(currentImage, 0, 0, null);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    logger.debug("MouseClicked: " + e.getX() + "," + e.getY());
    
    if (brewButton.contains(e.getPoint())) {
      logger.debug("Brew button clicked");
      
      BeverageOrder order = new BeverageOrder(new GreenTeaBeverage());
      controller.submitOrder(order);
    }
  }
  
  
  //// MouseEventListener events ////

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  
  //// KeyEventListener events ////
  
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

  
  //// BeverageControllerObserver events ////
  
  @Override
  public void onStateChanged(BeverageController controller, State newState) {}

  @Override
  public void onOrderReceived(BeverageController controller, BeverageOrder order) {}

  @Override
  public void onTooManyCondimentsOrdered(BeverageController controller, String message) {}

  @Override
  public void onOrderCompleted(BeverageController controller, CompletedOrder completedOrder) {
    logger.info("Beverage is ready");
    SwingUtilities.invokeLater(() -> {
      currentImage = automaticBeverageMachineImageWithCoffee;
      repaint();
      revalidate();
    });
  }

  @Override
  public void onOrderFailed(BeverageController controller, Throwable throwable) {}
}
