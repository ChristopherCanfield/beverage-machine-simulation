package edu.bu.met.cs665.bev.controller;

public class BlackTeaBeverage extends TeaBeverage {
  private final Recipe recipe;
  
  public BlackTeaBeverage() {
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
