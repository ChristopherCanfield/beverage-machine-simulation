package edu.bu.met.cs665.bev.controller;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import edu.bu.met.cs665.bev.hardware.MockHardwareInterface;

public class BeverageControllerTest {

  private MockHardwareInterface hardwareInterface;
  private HotDrinkBeverageController controller;
  
  @Before
  public void beforeEach() {
    hardwareInterface = new MockHardwareInterface(200);
    controller = new HotDrinkBeverageController(hardwareInterface);
  }
  
  @Test
  public void state_initial() {
    assertEquals(HotDrinkBeverageController.State.READY, controller.state());
  }
  
  @Test 
  public void state_whileBrewing() {
    assertEquals(HotDrinkBeverageController.State.READY, controller.state());
    
    BeverageOrder order = new BeverageOrder(new AmericanoBeverage());
    controller.submitOrder(order);
    assertEquals(HotDrinkBeverageController.State.MAKING_DRINK, controller.state());
  }
  
  @Test
  public void makeBeverage() {
    BeverageOrder order = new BeverageOrder(new AmericanoBeverage());
    order.addCondiment(new MilkCondiment());
    controller.submitOrder(order);
  }
  
  
  ////Observer Tests ////
  
  /**
  * Add an observer.
  */
  @Test
  public void addObserver() {
   assertEquals(0, controller.observerCount());
   controller.addObserver(new TrackingBeverageControllerObserver());
   assertEquals(1, controller.observerCount());
  }
  
  /**
  * Attempt to remove an observer that has not been added.
  */
  @Test
  public void removeObserver_NoObservers() {
   assertEquals(0, controller.observerCount());
   
   BeverageControllerObserver observer = new TrackingBeverageControllerObserver();
   controller.removeObserver(observer);
   assertEquals(0, controller.observerCount());
  }
  
  /**
  * Add and then remove an observer.
  */
  @Test
  public void removeObserver_AddThenRemove() {
   assertEquals(0, controller.observerCount());
   
   BeverageControllerObserver observer = new TrackingBeverageControllerObserver();
   controller.addObserver(observer);
   assertEquals(1, controller.observerCount());
   controller.removeObserver(observer);
   assertEquals(0, controller.observerCount());
  }
}
