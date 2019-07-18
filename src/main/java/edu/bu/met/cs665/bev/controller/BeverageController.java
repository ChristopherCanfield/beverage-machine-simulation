package edu.bu.met.cs665.bev.controller;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import org.checkerframework.checker.nullness.qual.Nullable;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import edu.bu.met.cs665.bev.hardware.CompletedOrder;
import edu.bu.met.cs665.bev.hardware.HardwareInterface;

public class BeverageController implements FutureCallback<CompletedOrder> {
  private static final int MAX_MILK = 3;
  private static final int MAX_SUGAR = 3;
  
  public enum State {
    READY,
    MAKING_DRINK
  }
  
  private final HardwareInterface hardwareInterface;
  private State state = State.READY;
  
  // While an array list would be a logical choice here, I used a hashset to prevent an observer 
  // from being stored and notified multiple times.
  // This is thread-safe, because the HardwareInterface may cause events that are reported on other threads.
  private Set<BeverageControllerObserver> observers = Collections.newSetFromMap(
      new ConcurrentHashMap<BeverageControllerObserver, Boolean>());
  
  
  /**
   * Constructs a new BeverageController that uses the specified hardware interface.
   * @param hardwareInterface an interface into the underlying beverage hardware.
   */
  public BeverageController(HardwareInterface hardwareInterface) {
    this.hardwareInterface = hardwareInterface;
  }
  
  /**
   * Returns the current state of the beverage controller.
   * @return the current state of the beverage controller.
   */
  public State state() {
    return state;
  }
  
  
  
  public void submitOrder(BeverageOrder order) {
    notifyObservers(observer -> observer.onOrderReceived(this, order));
    changeState(State.MAKING_DRINK);
    
    BeverageOrder verifiedOrder = verifyOrder(order);
    
    ListenableFuture<CompletedOrder> orderFuture = hardwareInterface.makeRecipe(verifiedOrder.toRecipe());
    Futures.addCallback(orderFuture, this, MoreExecutors.directExecutor());
  }
  
  /**
   * Ensures that the order does not exceed the maximum number of condiments. If the order does 
   * exceed the max number of condiments, it alerts any observers to the problems, and creates a
   * new order that is capped at the max condiments. Change this method when the maximum number of
   * any condiment is changed.
   * @param order the order sent to this controller.
   * @return the original order, or a revised order, if necessary.
   */
  private BeverageOrder verifyOrder(BeverageOrder order) {
    int milkOrder = 0;
    int sugarOrder = 0;
    
    for (Condiment condiment : order.condiments()) {
      if (condiment instanceof MilkCondiment) {
        milkOrder++;
      } else if (condiment instanceof SugarCondiment) {
        sugarOrder++;
      }
    }
    
    if (milkOrder > MAX_MILK || sugarOrder > MAX_SUGAR) {
      int finalMilkOrder = Math.min(milkOrder, MAX_MILK);
      int finalSugarOrder = Math.min(sugarOrder, MAX_SUGAR);
      
      String message = String.format("Too many condiments in order. Milk: %d; Sugar: %d. Adjusting order to %d milk & %d sugar.", 
          milkOrder, sugarOrder, finalMilkOrder, finalSugarOrder);
      notifyObservers(observer -> observer.onTooManyCondimentsOrdered(this, message));
      
      BeverageOrder revisedOrder = new BeverageOrder(order.beverage());
      addCondimentsToOrder(revisedOrder, new MilkCondiment(), finalMilkOrder);
      addCondimentsToOrder(revisedOrder, new SugarCondiment(), finalSugarOrder);
      
      return revisedOrder;
    } else {
      return order;
    }
  }
  
  private static void addCondimentsToOrder(BeverageOrder order, Condiment condiment, int count) {
    for (int i = 0; i < count; i++) {
      order.addCondiment(condiment);
    }
  }
  
  
  //// Callbacks for hardware interface. ////
  
  @Override
  public void onSuccess(@Nullable CompletedOrder result) {
    notifyObservers(observer -> observer.onOrderCompleted(this, result));
    changeState(State.READY);
  }

  @Override
  public void onFailure(Throwable t) {
    notifyObservers(observer -> observer.onOrderFailed(this, t));
    changeState(State.READY);
  }
  
  
  ////Observer functionality ////
    
  /**
  * Adds the specified observer to this beverage controller.
  * @param observer the observer to add.
  */
  public void addObserver(BeverageControllerObserver observer) {
   observers.add(observer);
  }
  
  /**
  * Removes the specified observer from this beverage controller. If the 
  * @param observer
  */
  public void removeObserver(BeverageControllerObserver observer) {
   observers.remove(observer);
  }
  
  /**
  * The number of observers attached to this controller. Primarily intended for testing.
  * @return the number of observers attached to this controller.
  */
  int observerCount() {
   return observers.size();
  }
  
  private void notifyObservers(Consumer<BeverageControllerObserver> action) {
   observers.forEach(action);
  }
  
  private void changeState(State newState) {
   state = newState;
   notifyObservers(observer -> observer.onStateChanged(this, newState));
  }
  
  ////////
}
