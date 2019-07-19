package edu.bu.met.cs665.bev.controller;

import edu.bu.met.cs665.bev.hardware.CompletedOrder;

/**
 * A beverage controller that tracks whether it has received notifications.
 * 
 * @author Christopher D. Canfield
 */
class TrackingBeverageControllerObserver implements BeverageControllerObserver {
  private boolean onStateChangedCalled = false;
  private boolean onOrderReceivedCalled = false;
  private boolean onTooManyCondimentsOrderedCalled = false;
  private boolean onOrderCompletedCalled = false;
  private boolean onOrderFailedCalled = false;
  
  boolean wasOnStateChangedCalled() {
    return onStateChangedCalled;
  }
  
  boolean wasOnOrderReceivedCalled() {
    return onOrderReceivedCalled;
  }
  
  boolean wasOnTooManyCondimentsOrderedCalled() {
    return onTooManyCondimentsOrderedCalled;
  }
  
  boolean wasOnOrderCompletedCalled() {
    return onOrderCompletedCalled;
  }
  
  boolean wasOnOrderFailedCalled() {
    return onOrderFailedCalled;
  }
  
  @Override
  public void onStateChanged(BeverageController controller, BeverageController.State newState) {
    onStateChangedCalled = true;
  }

  @Override
  public void onOrderReceived(BeverageController controller, BeverageOrder order) {
    onOrderReceivedCalled = true;
  }

  @Override
  public void onTooManyCondimentsOrdered(BeverageController controller, String message) {
    onTooManyCondimentsOrderedCalled = true;
  }
  
  @Override
  public void onOrderCompleted(BeverageController controller, CompletedOrder completedOrder) {
    onOrderCompletedCalled = true;
  }

  @Override
  public void onOrderFailed(BeverageController controller, Throwable throwable) {
    onOrderFailedCalled = true;
  }
}
