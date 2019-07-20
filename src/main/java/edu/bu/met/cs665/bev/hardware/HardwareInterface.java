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
  
  /**
   * Returns the number of orders that are waiting to be completed.
   * @return the number of orders that are waiting to be completed.
   */
  public int ordersPending();
  
  /**
   * Shuts down the hardware interface, and waits for any already submitted orders to be processed, 
   * as long they can be completed before the timeoutMilliseconds expires.
   * @param timeoutMilliseconds the maximum amount of time, in milliseconds, to wait for the orders
   *        to finish.
   * @return true if all orders were processed, or false otherwise.
   * @throws InterruptedException the thread was interrupted.
   */
  public boolean waitForCompletion(int timeoutMilliseconds) throws InterruptedException;
}
