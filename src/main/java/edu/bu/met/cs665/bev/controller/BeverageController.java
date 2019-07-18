package edu.bu.met.cs665.bev.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import org.apache.commons.lang.NotImplementedException;
import edu.bu.met.cs665.bev.hardware.HardwareInterface;

public class BeverageController {
  
  public enum State {
    READY,
    MAKING_DRINK
  }
  
  private final HardwareInterface hardwareInterface;
  private State state = State.READY;
  
  // While an array list would be a logical choice here, I used a hashset to prevent an observer 
  // from being stored and notified multiple times.
  private Set<BeverageControllerObserver> observers = new HashSet<>();
  
  
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
  
  
  
  public void submitOrder() {
    notifyObservers(observer -> observer.onOrderReceived(this));
    changeState(State.MAKING_DRINK);
    
    // TODO (2019-07-17): Implement this.
    
//    Future<CompletedOrder> completedOrderFuture = hardwareInterface.makeRecipe(recipe);
    
    throw new NotImplementedException();
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
