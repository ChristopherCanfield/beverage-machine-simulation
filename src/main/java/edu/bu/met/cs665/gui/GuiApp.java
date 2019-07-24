package edu.bu.met.cs665.gui;

import edu.bu.met.cs665.bev.controller.AmericanoBeverage;
import edu.bu.met.cs665.bev.controller.Beverage;
import edu.bu.met.cs665.bev.controller.BeverageController;
import edu.bu.met.cs665.bev.controller.BeverageControllerObserver;
import edu.bu.met.cs665.bev.controller.BeverageOrder;
import edu.bu.met.cs665.bev.controller.BlackTeaBeverage;
import edu.bu.met.cs665.bev.controller.EspressoBeverage;
import edu.bu.met.cs665.bev.controller.GreenTeaBeverage;
import edu.bu.met.cs665.bev.controller.HotDrinkBeverageController;
import edu.bu.met.cs665.bev.controller.LatteMacchiatoBeverage;
import edu.bu.met.cs665.bev.controller.MilkCondiment;
import edu.bu.met.cs665.bev.controller.SugarCondiment;
import edu.bu.met.cs665.bev.controller.YellowTeaBeverage;
import edu.bu.met.cs665.bev.hardware.CompletedOrder;
import edu.bu.met.cs665.bev.hardware.HardwareInterface;
import edu.bu.met.cs665.bev.hardware.MockHardwareInterface;
import edu.bu.met.cs665.gui.ResourceManager.ImageId;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.apache.log4j.Logger;

/**
 * A GUI interface for the Beverage Controller, which uses Swing as its GUI framework.
 * 
 * @author Christopher D. Canfield
 */
public class GuiApp extends Component implements 
    MouseListener, KeyListener, BeverageControllerObserver  {
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
  private Image machineImage;
  
  private ImageId submittedDrinkId;
  private boolean drawDrink = false;
  private Map<Class<? extends Beverage>, ImageId> beverageToId = createBeverageToIdMap();
  
  private List<Button> buttons = new ArrayList<Button>();
  private Spinner<Integer> milkSpinner;
  private Spinner<Integer> sugarSpinner;
  private Spinner<Beverage> beverageSpinner;
  
  private HardwareInterface hardwareInterface;
  private HotDrinkBeverageController controller;
  
  public void start() throws IOException {
    assert SwingUtilities.isEventDispatchThread();
    
    hardwareInterface = new MockHardwareInterface(1_500);
    controller = new HotDrinkBeverageController(hardwareInterface);
    // Subscribe to BeverageController events.
    controller.addObserver(this);
    
    // Load the initial images.
    machineImage = resourceManager.getImage(ImageId.MACHINE);
    
    createWindow();
    addButtons();
    addSpinners();
    
    window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    window.setVisible(true);
  }
  
  private static Map<Class<? extends Beverage>, ImageId> createBeverageToIdMap() {
    // This would look nicer in Java 9+, since we could use Map.of().
    Map<Class<? extends Beverage>, ImageId> map = new HashMap<>();
    map.put(AmericanoBeverage.class, ImageId.DRINK_AMERICANO);
    map.put(EspressoBeverage.class, ImageId.DRINK_ESPRESSO);
    map.put(LatteMacchiatoBeverage.class, ImageId.DRINK_LATTE_MACCHIATO);
    map.put(BlackTeaBeverage.class, ImageId.DRINK_BLACK_TEA);
    map.put(GreenTeaBeverage.class, ImageId.DRINK_GREEN_TEA);
    map.put(YellowTeaBeverage.class, ImageId.DRINK_YELLOW_TEA);
    return map;
  }
  
  private void addButtons() {
    // Brew button
    buttons.add(new Button(159, 358, 98, 47, () -> {
      // Create the order.
      BeverageOrder order = new BeverageOrder(beverageSpinner.itemValue());
      for (int i = 0; i < milkSpinner.itemValue().intValue(); i++) {
        order.addCondiment(new MilkCondiment());
      }
      for (int i = 0; i < sugarSpinner.itemValue().intValue(); i++) {
        order.addCondiment(new SugarCondiment());
      }
      
      logger.info(GuiApp.class.getSimpleName() + ": Brew button clicked. Submitting order: " 
            + order);
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
  
  private void addSpinners() throws IOException {
	// Create a condimentBuilder prototype.
    Spinner.Builder<Integer> condimentBuilder = createCondimentBuilderPrototype(resourceManager);
    
    // Create the milk spinner.
    milkSpinner = condimentBuilder.clone()
        .setUpButtonRect(new Rectangle(229, 276, 31, 34))
        .setDownButtonRect(new Rectangle(178, 276, 31, 34))
        .setItemPosition(new Point(202, 275))
        .build();
    
    // Create the sugar spinner.
    sugarSpinner = condimentBuilder.clone()
        .setUpButtonRect(new Rectangle(229, 313, 31, 34))
        .setDownButtonRect(new Rectangle(178, 313, 31, 34))
        .setItemPosition(new Point(202, 310))
        .build();
    
    // Create the beverage spinner.
    beverageSpinner = new Spinner.Builder<Beverage>()
        .setResourceManager(resourceManager)
        .setUpButtonRect(new Rectangle(312, 235, 31, 34))
        .setDownButtonRect(new Rectangle(178, 235, 31, 34))
        .setItemPosition(new Point(163, 238))
        .addItem(new AmericanoBeverage(), ImageId.TEXT_AMERICANO)
        .addItem(new EspressoBeverage(), ImageId.TEXT_ESPRESSO)
        .addItem(new LatteMacchiatoBeverage(), ImageId.TEXT_LATTE_MACCHIATO)
        .addItem(new BlackTeaBeverage(), ImageId.TEXT_BLACK_TEA)
        .addItem(new GreenTeaBeverage(), ImageId.TEXT_GREEN_TEA)
        .addItem(new YellowTeaBeverage(), ImageId.TEXT_YELLOW_TEA)
        .build();
  }
  
  private static Spinner.Builder<Integer> createCondimentBuilderPrototype(ResourceManager rm) {
	  return new Spinner.Builder<Integer>()
		        .setResourceManager(rm)
		        .addItem(0, ImageId.CHAR_0)
		        .addItem(1, ImageId.CHAR_1)
		        .addItem(2, ImageId.CHAR_2)
		        .addItem(3, ImageId.CHAR_3);
  }
  
  private void createWindow() {
    window = new JFrame("Automatic Beverage Machine");
    window.setUndecorated(true);
    
    window.setSize(400, 500);
    
    window.addKeyListener(this);
    window.addMouseListener(this);
    window.add(this);

    window.setLocationRelativeTo(null);
  }
  
  @Override
  public void paint(Graphics g) {
    g.drawImage(machineImage, 0, 0, null);
    
    if (submittedDrinkId != null && drawDrink) {
      try {
        g.drawImage(resourceManager.getImage(submittedDrinkId), 
            182, 425, null);
      } catch (IOException e) {
        logger.error("Unable to load drink image: " + e);
      }
    }

    try {
      beverageSpinner.paint(g);
      milkSpinner.paint(g);
      sugarSpinner.paint(g);
    } catch (IOException e) {
      logger.error("Unable to load spinner image: " + e);
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    logger.debug("MouseClicked: " + e.getX() + "," + e.getY());
    
    Point point = e.getPoint();
    buttons.forEach(button -> button.executeIfContains(point));
    beverageSpinner.executeIfContains(point);
    milkSpinner.executeIfContains(point);
    sugarSpinner.executeIfContains(point);
    
    // Redraw the images.
    repaint();
    revalidate();
  }
  
  
  //// MouseEventListener events ////

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mousePressed(MouseEvent e) {
  }

  @Override
  public void mouseReleased(MouseEvent e) {
  }

  
  //// KeyEventListener events ////
  
  @Override
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  
  //// BeverageControllerObserver events ////
  
  @Override
  public void onStateChanged(BeverageController controller, BeverageController.State newState) {
    logger.info("Beverage Controller reports that its state has changed to " + newState);
  }

  @Override
  public void onOrderReceived(BeverageController controller, BeverageOrder order) {
    logger.info("Beverage Controller reports that is has received an order: " + order);
    
    SwingUtilities.invokeLater(() -> {
      submittedDrinkId = beverageToId.get(order.beverage().getClass());
      drawDrink = false;
      
      // Redraw the image.
      repaint();
      revalidate();
    });
  }

  @Override
  public void onTooManyCondimentsOrdered(BeverageController controller, String message) {
    logger.info("Beverage Controller reports that too many condiments were included in the order."
          + " Message: " + message);
  }

  @Override
  public void onOrderCompleted(BeverageController controller, CompletedOrder completedOrder) {
    logger.info(String.format("Beverage Controller reports that beverage is ready at %s. Order " 
          + "hardware command was %s", completedOrder.finishedAtTime(), 
          completedOrder.recipe().hardwareCommand()));
    
    SwingUtilities.invokeLater(() -> {
      drawDrink = true;
      
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
