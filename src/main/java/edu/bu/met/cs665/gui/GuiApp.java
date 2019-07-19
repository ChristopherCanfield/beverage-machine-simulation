package edu.bu.met.cs665.gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.apache.log4j.Logger;
import edu.bu.met.cs665.bev.controller.BeverageController;
import edu.bu.met.cs665.bev.controller.BeverageController.State;
import edu.bu.met.cs665.bev.controller.BeverageControllerObserver;
import edu.bu.met.cs665.bev.controller.BeverageOrder;
import edu.bu.met.cs665.bev.controller.GreenTeaBeverage;
import edu.bu.met.cs665.bev.hardware.CompletedOrder;
import edu.bu.met.cs665.bev.hardware.HardwareInterface;
import edu.bu.met.cs665.bev.hardware.MockHardwareInterface;
import edu.bu.met.cs665.gui.ResourceManager.ImageId;

public class GuiApp extends Component implements MouseListener, KeyListener, BeverageControllerObserver  {
  private static final long serialVersionUID = 1L;
  
  private static final Logger logger = Logger.getLogger(GuiApp.class);

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(() -> {
        GuiApp app = new GuiApp();
        try {
          app.start();
        } catch (IOException e) {
          logger.error(e.toString());
        }
      });
  }
  
  private JFrame window;
  
  private ResourceManager resourceManager = new ResourceManager();
  private Image currentMachineImage;
  private Image currentMilkQuantityImage;
  private Image currentSugarQuantityImage;
  
  private List<Button> buttons = new ArrayList<Button>();
  
  private HardwareInterface hardwareInterface;
  private BeverageController controller;
  
  public void start() throws IOException {
    assert SwingUtilities.isEventDispatchThread();
    
    hardwareInterface = new MockHardwareInterface(1_500);
    controller = new BeverageController(hardwareInterface);
    // Subscribe to BeverageController events.
    controller.addObserver(this);

    currentMachineImage = resourceManager.getImage(ImageId.MACHINE);
    currentMilkQuantityImage = resourceManager.getImage(ImageId.CHAR_0);
    currentSugarQuantityImage = resourceManager.getImage(ImageId.CHAR_0);
    
    createWindow();
    addButtons();
    
    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    window.setVisible(true);
  }
  
  private void addButtons() {
    // Brew button
    buttons.add(new Button(159, 358, 98, 47, () -> {
      logger.debug("Brew button clicked. Submitting order.");
      // TODO (2019-07-18): Make this changeable.
      BeverageOrder order = new BeverageOrder(new GreenTeaBeverage());
      controller.submitOrder(order);
    }));
    
    // Type down
    buttons.add(new Button(178, 235, 31, 34, () -> {
      logger.debug("Beverage type down clicked.");
    }));
    
    // Type up
    buttons.add(new Button(312, 235, 31, 34, () -> {
      logger.debug("Beverage type up clicked.");
    }));
    
    // Milk down
    buttons.add(new Button(178, 276, 31, 34, () -> {
      logger.debug("Milk down clicked.");
    }));
    
    // Milk up
    buttons.add(new Button(229, 276, 31, 34, () -> {
      logger.debug("Milk up clicked.");
    }));
    
    // Sugar down
    buttons.add(new Button(178, 313, 31, 34, () -> {
      logger.debug("Sugar down clicked.");
    }));
    
    // Sugar up
    buttons.add(new Button(229, 313, 31, 34, () -> {
      logger.debug("Sugar up clicked.");
    }));
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
    g.drawImage(currentMachineImage, 0, 0, null);
    g.drawImage(currentMilkQuantityImage, 203, 275, null);
    g.drawImage(currentSugarQuantityImage, 203, 311, null);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    logger.debug("MouseClicked: " + e.getX() + "," + e.getY());
    
    buttons.forEach(button -> button.executeIfContains(e.getPoint()));
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
  public void onStateChanged(BeverageController controller, State newState) {
    logger.info("Beverage Controller reports that its state has changed to " + newState);
  }

  @Override
  public void onOrderReceived(BeverageController controller, BeverageOrder order) {
    logger.info("Beverage Controller reports that is has received an order.");
  }

  @Override
  public void onTooManyCondimentsOrdered(BeverageController controller, String message) {
    logger.info("Beverage Controller reports that too many condiments were included in the order. Message: " + message);
  }

  @Override
  public void onOrderCompleted(BeverageController controller, CompletedOrder completedOrder) {
    logger.info("Beverage Controller reports that beverage is ready.");
    SwingUtilities.invokeLater(() -> {
      try {
        currentMachineImage = resourceManager.getImage(ImageId.MACHINE_WITH_DRINK);
      } catch (IOException e) {
        logger.error("Error loading " + ImageId.MACHINE_WITH_DRINK.path() + ": " + e.toString());
      }
      
      // Redraw the image.
      repaint();
      revalidate();
    });
  }

  @Override
  public void onOrderFailed(BeverageController controller, Throwable throwable) {
    logger.warn("Beverage Controller reports that the order has failed. Error: " + throwable);
  }
  
  
  // Swing components are serializable, but this application has no need for that functionality.
  // By disabling it, we also eliminate a valid, but irrelevant to us, FindBugs warning.
  
  private void writeObject(ObjectOutputStream stream) throws IOException {
    throw new NotSerializableException(GuiApp.class.getName() + " is not serializable.");
  }

  private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
    throw new NotSerializableException(GuiApp.class.getName() + " is not serializable.");
  }
}
