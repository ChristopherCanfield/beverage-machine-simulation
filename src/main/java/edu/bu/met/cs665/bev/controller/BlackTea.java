package edu.bu.met.cs665.bev.controller;

public class BlackTea extends TeaBeverage {
  private final Recipe recipe;
  
  protected BlackTea() {
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
