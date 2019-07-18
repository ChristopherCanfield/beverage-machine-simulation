package edu.bu.met.cs665;

import org.apache.log4j.Logger;
import edu.bu.met.cs665.bev.controller.BeverageController;
import edu.bu.met.cs665.bev.controller.BeverageController.State;
import edu.bu.met.cs665.bev.controller.BeverageControllerObserver;
import edu.bu.met.cs665.bev.controller.BeverageOrder;
import edu.bu.met.cs665.bev.controller.LatteMachiatoBeverage;
import edu.bu.met.cs665.bev.hardware.CompletedOrder;
import edu.bu.met.cs665.bev.hardware.MockHardwareInterface;

public class Main implements BeverageControllerObserver {

  private static Logger logger = Logger.getLogger(Main.class);


  /**
   * A main method to run examples.
   * 
   * @param args not used
   */
  public static void main(String[] args) {
    Main main = new Main();
    main.testBeverageController();
  }
  
  public void testBeverageController() {
    logger.info("Main: Constructing BeverageController");
    BeverageController controller = new BeverageController(new MockHardwareInterface(5));
    logger.info("Main: Subscribing to BeverageController's events.");
    controller.addObserver(this);
    
    logger.info("Main: BeverageController's current state: " + controller.state());
    
    logger.info("Main: Constructing first order.");
    BeverageOrder order1 = new BeverageOrder(new LatteMachiatoBeverage());
    logger.info("Main: Submitting first order to BeverageController: " + order1.toString());
    controller.submitOrder(order1);
  }


  @Override
  public void onStateChanged(BeverageController controller, State newState) {
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
