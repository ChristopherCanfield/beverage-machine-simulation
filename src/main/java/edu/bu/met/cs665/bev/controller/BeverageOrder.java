package edu.bu.met.cs665.bev.controller;

import static java.util.Objects.requireNonNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BeverageOrder {
  private final Beverage beverage;
  private final List<ParameterizedCondiment> condiments = new ArrayList<>();
  
  public BeverageOrder(Beverage beverage) {
    this.beverage = requireNonNull(beverage);
  }
  
  /**
   * Adds the specified condiment to this order.
   * @param condiment the condiment to add.
   */
  public void addCondiment(ParameterizedCondiment condiment) {
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
  public Collection<ParameterizedCondiment> condiments() {
    return Collections.unmodifiableCollection(condiments);
  }
}
