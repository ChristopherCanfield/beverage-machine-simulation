package edu.bu.met.cs665;

import org.apache.log4j.Logger;
import edu.bu.met.cs665.bev.controller.BeverageController;
import edu.bu.met.cs665.bev.controller.BeverageControllerObserver;
import edu.bu.met.cs665.bev.controller.BeverageOrder;
import edu.bu.met.cs665.bev.controller.HotDrinkBeverageController;
import edu.bu.met.cs665.bev.controller.LatteMacchiatoBeverage;
import edu.bu.met.cs665.bev.hardware.CompletedOrder;
import edu.bu.met.cs665.bev.hardware.HardwareInterface;
import edu.bu.met.cs665.bev.hardware.MockHardwareInterface;

/**
 * Used to test the Hot Beverage Controller. Results are output to the logger.
 * 
 * @author Christopher D. Canfield
 */
public class Main implements BeverageControllerObserver {

  private static Logger logger = Logger.getLogger(Main.class);

  /**
   * A main method to run examples.
   * 
   * @param args not used
   */
  public static void main(String[] args) {
    logger.info("Main: Welcome to the BeverageController tests.");
    
    Main main = new Main();
    main.testBeverageController();
    
    logger.info("Main: Exiting BeverageController tests.");
  }
  
  /**
   * Tests the Hot Beverage Controller, and outputs the results to the logger.
   */
  public void testBeverageController() {
    logger.info("Main: Constructing the HardwareInterface");
    HardwareInterface hardwareInterface = new MockHardwareInterface(20);
    
    logger.info("Main: Constructing BeverageController");
    HotDrinkBeverageController controller = new HotDrinkBeverageController(hardwareInterface);
    logger.info("Main: Subscribing to BeverageController's events.");
    controller.addObserver(this);
    
    logger.info("Main: BeverageController's current state: " + controller.state());
    
    logger.info("Main: Constructing first order.");
    BeverageOrder order1 = new BeverageOrder(new LatteMacchiatoBeverage());
    logger.info("Main: Submitting first order to BeverageController: " + order1.toString());
    controller.submitOrder(order1);
    
    logger.info("Main: Waiting for drinks to be made.");
    try {
      if (hardwareInterface.waitForCompletion(50)) {
        logger.info("Main: All drinks made. Shutting down.");
      } else {
        logger.warn("Main: The drink orders are taking too long. Shutting down.");
      }
    } catch (InterruptedException e) {
      logger.warn("Main: Thread interrupted. Shutting down.");
    }
  }


  @Override
  public void onStateChanged(BeverageController controller, BeverageController.State newState) {
    logger.info("BeverageController's state changed: " + newState);
  }


  @Override
  public void onOrderReceived(BeverageController controller, BeverageOrder order) {
    logger.info("BeverageController received an order: " + order.toString());
  }


  @Override
  public void onTooManyCondimentsOrdered(BeverageController controller, String message) {
    logger.warn("BeverageController received too many condiments in order.\n\tMessage: " + message);
  }


  @Override
  public void onOrderCompleted(BeverageController controller, CompletedOrder completedOrder) {
    logger.info("BeverageController reports that the drink has been made.");
  }


  @Override
  public void onOrderFailed(BeverageController controller, Throwable throwable) {
    logger.error("BeverageController reports that the order failed: " + throwable.toString());
  }

}
