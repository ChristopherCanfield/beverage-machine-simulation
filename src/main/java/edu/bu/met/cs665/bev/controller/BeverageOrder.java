package edu.bu.met.cs665.bev.controller;

import static java.util.Objects.requireNonNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A beverage order, which includes the type of drink to make, and, optionally, the condiments to
 * include.
 * 
 * @author Christopher D. Canfield
 */
public class BeverageOrder {
  private final Beverage beverage;
  private final List<Condiment> condiments = new ArrayList<>();
  
  public BeverageOrder(Beverage beverage) {
    this.beverage = requireNonNull(beverage);
  }
  
  /**
   * Adds the specified condiment to this order. The beverage order does not have a limit on 
   * condiments, but specific BeverageController implementations may.
   * @param condiment the condiment to add.
   */
  public void addCondiment(Condiment condiment) {
    requireNonNull(condiment);
    condiments.add(condiment);
  }
  
  /**
   * @return the beverage.
   */
  public Beverage beverage() {
    return beverage;
  }
  
  /**
   * Returns an unmodifiable collection of this order's condiments.
   * @return an unmodifiable collection of this order's condiments
   */
  public Collection<Condiment> condiments() {
    return Collections.unmodifiableCollection(condiments);
  }
  
  /**
   * Returns a recipe that represents this beverage order.
   */
  Recipe toRecipe() {
    Recipe.Builder rb = new Recipe.Builder(beverage.recipe());
    
    for (Condiment condiment : condiments) {
      rb.addCondiment(condiment);
    }
    
    return rb.build();
  }
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(beverage.name());
    
    if (!(condiments.isEmpty())) {
      boolean firstCondiment = true;
      for (Condiment condiment : condiments) {
        if (firstCondiment) {
          sb.append(" with ");
          sb.append(condiment);
          firstCondiment = false;
        } else {
          sb.append(", ");
          sb.append(condiment.name());
        }
      }
    }
    
    return sb.toString();
  }
}
