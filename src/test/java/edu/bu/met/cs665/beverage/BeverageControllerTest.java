package edu.bu.met.cs665.beverage;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class BeverageControllerTest {

  private BeverageController controller;
  
  @Before
  public void beforeEach() {
    controller = new BeverageController(new MockHardwareInterface());
  }
  
  
  //// Observer Tests ////
  
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
  
  public void makeBeverage() {
    controller.submitOrder();
  }
  
}
