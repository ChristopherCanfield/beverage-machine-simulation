package edu.bu.met.cs665.bev.hardware;

import com.google.common.util.concurrent.ListenableFuture;
import edu.bu.met.cs665.bev.controller.Recipe;

/**
 * An interface into the brewing hardware.
 * 
 * @author Christopher D. Canfield
 */
public interface HardwareInterface {
  
  /**
   * Passes the specified recipe to the hardware, which then creates it.
   * @param recipe the recipe to make.
   * @return a future that will contain the completed order, once the drink has been made.
   */
  ListenableFuture<CompletedOrder> makeRecipe(Recipe recipe);
}
