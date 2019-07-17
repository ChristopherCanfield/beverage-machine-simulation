package edu.bu.met.cs665.beverage;

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
  void onOrderReceived(BeverageController controller);
  
  /**
   * Called when the observed controller completes an order.
   * @param controller the controller that copmleted an order.
   */
  void onOrderCompleted(BeverageController controller);
}
