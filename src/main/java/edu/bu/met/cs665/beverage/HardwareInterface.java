package edu.bu.met.cs665.beverage;

import java.util.concurrent.Future;

/**
 * An interface into the brewing hardware.
 * 
 * @author Christopher D. Canfield
 */
public interface HardwareInterface {
  
  /**
   * Passes the specified recipe to the hardware, which creates the beverage.
   * @param recipe the recipe to make.
   * @return a future that will contain the completed order, once the drink has been made.
   */
  Future<CompletedOrder> makeBeverage(Recipe recipe);
}
