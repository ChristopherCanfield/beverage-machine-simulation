package edu.bu.met.cs665.bev.controller;

public class LatteMachiatoBeverage extends CoffeeBeverage {
  private final Recipe recipe;
  
  protected LatteMachiatoBeverage() {
    super("Latte Machiato");
    
    Recipe.Builder rb = new Recipe.Builder();
    recipe = rb.setTypeIndicator(typeIndicator())
      .setSubtypeIndicator("LM")
      .setTemperatureFahrenheit(205)
      .build();
  }

  @Override
  Recipe recipe() {
    return recipe;
  }
}
