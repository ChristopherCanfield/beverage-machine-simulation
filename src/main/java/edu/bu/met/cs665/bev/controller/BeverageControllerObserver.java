package edu.bu.met.cs665.bev.controller;

import edu.bu.met.cs665.bev.hardware.CompletedOrder;

/**
 * BeverageControllerObservers can receive notifications from BeverageControllers when various 
 * events occur.
 * 
 * @author Christopher D. Canfield
 */
public interface BeverageControllerObserver {
  
  /**
   * Called when the observed controller's state changes.
   * @param controller the controller that had a change in state.
   * @param newState the new state of the controller.
   */
  void onStateChanged(BeverageController controller, BeverageController.State newState);
  
  /**
   * Called when the observed controller receives an order.
   * @param controller the controller that received an order.
   */
  void onOrderReceived(BeverageController controller, BeverageOrder order);
  
  /**
   * Called when too many condiments have been ordered.
   * @param controller the controller that received the order.
   * @param message a message that contains information about the order problem.
   */
  void onTooManyCondimentsOrdered(BeverageController controller, String message);
  
  /**
   * Called when the observed controller completes an order.
   * @param controller the controller that completed an order.
   * @param completedOrder the completed order info.
   */
  void onOrderCompleted(BeverageController controller, CompletedOrder completedOrder);
  
  /**
   * Called when the order has failed.
   * @param controller the controller that received the order.
   * @param throwable a throwable representing the failure.
   */
  void onOrderFailed(BeverageController controller, Throwable throwable);
}
