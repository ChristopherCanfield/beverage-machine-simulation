package edu.bu.met.cs665;

import edu.bu.met.cs665.bev.controller.AmericanoBeverage;
import edu.bu.met.cs665.bev.controller.BeverageController;
import edu.bu.met.cs665.bev.controller.BeverageControllerObserver;
import edu.bu.met.cs665.bev.controller.BeverageOrder;
import edu.bu.met.cs665.bev.controller.GreenTeaBeverage;
import edu.bu.met.cs665.bev.controller.HotDrinkBeverageController;
import edu.bu.met.cs665.bev.controller.LatteMacchiatoBeverage;
import edu.bu.met.cs665.bev.controller.MilkCondiment;
import edu.bu.met.cs665.bev.controller.SugarCondiment;
import edu.bu.met.cs665.bev.controller.YellowTeaBeverage;
import edu.bu.met.cs665.bev.hardware.CompletedOrder;
import edu.bu.met.cs665.bev.hardware.HardwareInterface;
import edu.bu.met.cs665.bev.hardware.MockHardwareInterface;

import org.apache.log4j.Logger;

/**
 * Automated interface used to test the Hot Beverage Controller. Results are output to the logger.
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

  private BeverageController controller;
  private HardwareInterface hardwareInterface;

  /**
   * Tests the Hot Beverage Controller, and outputs the results to the logger.
   */
  public void testBeverageController() {
    logger.info("Main: Constructing the HardwareInterface");
    hardwareInterface = new MockHardwareInterface(20);

    logger.info("Main: Constructing BeverageController");
    controller = new HotDrinkBeverageController(hardwareInterface);
    logger.info("Main: Subscribing to BeverageController's events.");
    controller.addObserver(this);

    logger.info("");
    testBeverageOrderLatteMachiato();

    logger.info("");
    testBeverageOrderGreenTeaMilkMilk();

    logger.info("");
    testBeverageOrderAmericanoMilkSugar();

    logger.info("");
    testBeverageOrderYellowTeaMilkMilkMilkMilk();

    logger.info("");
    logger.info("Main: Waiting for remaining drinks to be made.");
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

  private void testBeverageOrderLatteMachiato() {
    logger.info("Main: Constructing new order.");
    BeverageOrder order = new BeverageOrder(new LatteMacchiatoBeverage());
    testBeverageOrder(order);
  }

  private void testBeverageOrderGreenTeaMilkMilk() {
    logger.info("Main: Constructing new order.");
    BeverageOrder order = new BeverageOrder(new GreenTeaBeverage());
    order.addCondiment(new MilkCondiment());
    order.addCondiment(new MilkCondiment());
    testBeverageOrder(order);
  }

  private void testBeverageOrderAmericanoMilkSugar() {
    logger.info("Main: Constructing new order.");
    BeverageOrder order = new BeverageOrder(new AmericanoBeverage());
    order.addCondiment(new MilkCondiment());
    order.addCondiment(new SugarCondiment());
    testBeverageOrder(order);
  }

  private void testBeverageOrderYellowTeaMilkMilkMilkMilk() {
    logger.info("Main: Constructing new order.");
    BeverageOrder order = new BeverageOrder(new YellowTeaBeverage());
    order.addCondiment(new MilkCondiment());
    order.addCondiment(new MilkCondiment());
    order.addCondiment(new MilkCondiment());
    order.addCondiment(new MilkCondiment());
    testBeverageOrder(order);
  }

  private void testBeverageOrder(BeverageOrder order) {
    logger.info("Main: BeverageController's current state is " + controller.state());
    logger.info("Main: Submitting order to BeverageController: " + order);
    controller.submitOrder(order);
  }


  @Override
  public void onStateChanged(BeverageController controller, BeverageController.State newState) {
    logger.info("BeverageController's state changed: " + newState);
  }


  @Override
  public void onOrderReceived(BeverageController controller, BeverageOrder order) {
    logger.info("BeverageController received an order: " + order);
  }


  @Override
  public void onTooManyCondimentsOrdered(BeverageController controller, String message) {
    logger.warn("BeverageController received too many condiments in order.\n\tMessage: " + message);
  }


  @Override
  public void onOrderCompleted(BeverageController controller, CompletedOrder completedOrder) {
    logger.info(String.format("BeverageController reports that the drink was made at %s. Hardware "
        + "command was %s.", completedOrder.finishedAtTime(),
        completedOrder.recipe().hardwareCommand()));
  }


  @Override
  public void onOrderFailed(BeverageController controller, Throwable throwable) {
    logger.error("BeverageController reports that the order failed: " + throwable.toString());
  }

}
