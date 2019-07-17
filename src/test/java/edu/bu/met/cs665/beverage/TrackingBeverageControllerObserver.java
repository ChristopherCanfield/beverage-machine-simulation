package edu.bu.met.cs665.beverage;

import edu.bu.met.cs665.beverage.BeverageController.State;

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
}