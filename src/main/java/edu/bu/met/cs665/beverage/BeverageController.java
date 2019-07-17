package edu.bu.met.cs665.beverage;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class BeverageController {
  
  public enum State {
    READY,
    MAKING_DRINK
  }
  
  private State state;
  
  // While an array list would be a logical choice here, I used a hashset to prevent an observer 
  // from being stored notified multiple times.
  private Set<BeverageControllerObserver> observers = new HashSet<>();
  
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
  
  public void makeBeverage() {
    changeState(State.MAKING_DRINK);
  }
}
