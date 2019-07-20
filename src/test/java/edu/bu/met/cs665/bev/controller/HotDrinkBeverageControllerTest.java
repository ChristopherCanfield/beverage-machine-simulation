package edu.bu.met.cs665.bev.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import edu.bu.met.cs665.bev.hardware.MockHardwareInterface;

import org.junit.Before;
import org.junit.Test;

public class HotDrinkBeverageControllerTest {

  private MockHardwareInterface hardwareInterface;
  private HotDrinkBeverageController controller;
  private TrackingBeverageControllerObserver observer;
  
  private HotDrinkBeverageController controllerNoInitialObservers;
  
  @Before
  public void beforeEach() {
    hardwareInterface = new MockHardwareInterface(150);
    controllerNoInitialObservers = new HotDrinkBeverageController(hardwareInterface);
    controller = new HotDrinkBeverageController(hardwareInterface);
    observer = new TrackingBeverageControllerObserver();
    controller.addObserver(observer);
    
    assertFalse(observer.wasOnOrderCompletedCalled());
    assertFalse(observer.wasOnOrderFailedCalled());
    assertFalse(observer.wasOnOrderReceivedCalled());
    assertFalse(observer.wasOnStateChangedCalled());
    assertFalse(observer.wasOnTooManyCondimentsOrderedCalled());
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
  public void makeBeverage_noCondiments() {
    BeverageOrder order = new BeverageOrder(new AmericanoBeverage());
    controller.submitOrder(order);
  }
  
  @Test
  public void makeBeverage_allKnownTypes() {
    Beverage[] beverages = {
      new AmericanoBeverage(),
      new EspressoBeverage(),
      new LatteMacchiatoBeverage(),
      new BlackTeaBeverage(),
      new GreenTeaBeverage(),
      new YellowTeaBeverage()
    };
    
    for (Beverage beverage : beverages) {
      BeverageOrder order = new BeverageOrder(beverage);
      controller.submitOrder(order);
    }
  }
  
  @Test
  public void makeBeverage_withMilkAndSugar() throws InterruptedException {
    BeverageOrder order = new BeverageOrder(new GreenTeaBeverage());
    order.addCondiment(new MilkCondiment());
    order.addCondiment(new SugarCondiment());
    controller.submitOrder(order);
    hardwareInterface.waitForCompletion(200);
    
    assertTrue(observer.wasOnOrderCompletedCalled());
  }
  
  @Test
  public void makeBeverage_tooManyMilks() throws InterruptedException {
    BeverageOrder order = new BeverageOrder(new BlackTeaBeverage());
    order.addCondiment(new MilkCondiment());
    order.addCondiment(new MilkCondiment());
    order.addCondiment(new MilkCondiment());
    order.addCondiment(new MilkCondiment());
    controller.submitOrder(order);
    
    assertTrue(observer.wasOnTooManyCondimentsOrderedCalled());
    
    hardwareInterface.waitForCompletion(200);
    assertTrue(observer.wasOnOrderCompletedCalled());
  }
  
  @Test
  public void makeBeverage_tooManySugars() throws InterruptedException {
    BeverageOrder order = new BeverageOrder(new BlackTeaBeverage());
    order.addCondiment(new SugarCondiment());
    order.addCondiment(new SugarCondiment());
    order.addCondiment(new SugarCondiment());
    order.addCondiment(new SugarCondiment());
    controller.submitOrder(order);
    
    assertTrue(observer.wasOnTooManyCondimentsOrderedCalled());
    
    hardwareInterface.waitForCompletion(200);
    assertTrue(observer.wasOnOrderCompletedCalled());
  }
  
  
  ////Observer Tests ////
  
  /**
  * Add an observer.
  */
  @Test
  public void addObserver() {
    assertEquals(0, controllerNoInitialObservers.observerCount());
    controllerNoInitialObservers.addObserver(new TrackingBeverageControllerObserver());
    assertEquals(1, controllerNoInitialObservers.observerCount());
  }
  
  /**
  * Attempt to remove an observer that has not been added.
  */
  @Test
  public void removeObserver_NoObservers() {
    assertEquals(0, controllerNoInitialObservers.observerCount());
     
    BeverageControllerObserver observer = new TrackingBeverageControllerObserver();
    controllerNoInitialObservers.removeObserver(observer);
    assertEquals(0, controllerNoInitialObservers.observerCount());
  }
  
  /**
  * Add and then remove an observer.
  */
  @Test
  public void removeObserver_AddThenRemove() {
    assertEquals(0, controllerNoInitialObservers.observerCount());
       
    BeverageControllerObserver observer = new TrackingBeverageControllerObserver();
    controllerNoInitialObservers.addObserver(observer);
    assertEquals(1, controllerNoInitialObservers.observerCount());
    controllerNoInitialObservers.removeObserver(observer);
    assertEquals(0, controllerNoInitialObservers.observerCount());
  }
}
