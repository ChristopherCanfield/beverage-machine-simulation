package edu.bu.met.cs665.bev.controller;

/**
 * Interface for beverage controllers.
 * 
 * @author Christopher D. Canfield
 */
public interface BeverageController {
  /**
   * Beverage controller states.
   * 
   * @author Christopher D. Canfield
   */
  public enum State {
    READY,
    MAKING_DRINK
  }
  
  /**
   * Returns the current state of the beverage controller.
   * @return the current state of the beverage controller.
   */
  public State state();
  
  /**
   * Submits a drink order to the BeverageController. The controller will notify its observers of
   * its progress using the BeverageControllerObserver callback methods.
   * @param order the drink order to process.
   */
  public void submitOrder(BeverageOrder order);
  
  /**
   * Adds the specified observer to this beverage controller.
   * @param observer the observer to add.
   */
   public void addObserver(BeverageControllerObserver observer);
   
   /**
   * Removes the specified observer from this beverage controller. If the 
   * @param observer the observer to remove.
   */
   public void removeObserver(BeverageControllerObserver observer);
}
