package edu.bu.met.cs665.bev.controller;

import static java.util.Objects.requireNonNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BeverageOrder {
  private final Beverage beverage;
  private final List<Condiment> condiments = new ArrayList<>();
  
  public BeverageOrder(Beverage beverage) {
    this.beverage = requireNonNull(beverage);
  }
  
  /**
   * Adds the specified condiment to this order.
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
}
