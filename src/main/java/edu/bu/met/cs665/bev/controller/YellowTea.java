package edu.bu.met.cs665.bev.controller;

public class YellowTea extends TeaBeverage {
  private final Recipe recipe;
  
  protected YellowTea() {
    super("Black Tea");
    
    Recipe.Builder rb = new Recipe.Builder();
    recipe = rb.setTypeIndicator(typeIndicator())
      .setSubtypeIndicator("BT")
      .setTemperatureFahrenheit(205)
      .build();
  }

  @Override
  Recipe recipe() {
    return recipe;
  }
}
