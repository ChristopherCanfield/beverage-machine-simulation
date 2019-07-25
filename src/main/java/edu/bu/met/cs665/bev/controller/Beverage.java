package edu.bu.met.cs665.bev.controller;

import java.util.Objects;

/**
 * Base class for beverages. A beverage provides information needed to brew itself. Beverages must
 * be immutable.
 * 
 * @author Christopher D. Canfield
 */
public abstract class Beverage {
  private final String name;
  
  protected Beverage(String name) {
    this.name = Objects.requireNonNull(name);
  }
  
  /**
   * The beverage's name, suitable for displaying to a user.
   * @return the beverage's name.
   */
  public String name() {
    return name;
  }
  
  /**
   * The beverage's recipe.
   * @return the beverage's recipe.
   */
  protected abstract Recipe recipe();
}
