package edu.bu.met.cs665.bev.controller;

public class GreenTea extends TeaBeverage {
  private final Recipe recipe;
  
  protected GreenTea() {
    super("Green Tea");
    
    Recipe.Builder rb = new Recipe.Builder();
    recipe = rb.setTypeIndicator(typeIndicator())
      .setSubtypeIndicator("GT")
      .setTemperatureFahrenheit(175)
      .build();
  }

  @Override
  Recipe recipe() {
    return recipe;
  }
}
