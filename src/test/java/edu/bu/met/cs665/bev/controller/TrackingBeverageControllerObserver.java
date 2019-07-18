package edu.bu.met.cs665.bev.controller;

import edu.bu.met.cs665.bev.controller.BeverageController;
import edu.bu.met.cs665.bev.controller.BeverageControllerObserver;
import edu.bu.met.cs665.bev.controller.BeverageController.State;

/**
 * A beverage controller that tracks whether it has received notifications.
 * 
 * @author Christopher D. Canfield
 */
public class TrackingBeverageControllerObserver implements BeverageControllerObserver {
  private boolean onStateChangedCalled = false;
  
  boolean wasOnStateChangedCalled() {
    return onStateChangedCalled;
  }
  
  @Override
  public void onStateChanged(BeverageController controller, State newState) {
    onStateChangedCalled = true;
  }
  
  @Override
  public void onOrderReceived(BeverageController controller) {
    
  }
  
  @Override
  public void onOrderCompleted(BeverageController controller) {
    
  }
}
