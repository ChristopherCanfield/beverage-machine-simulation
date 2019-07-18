package edu.bu.met.cs665.bev.controller;

public class LatteMachiatoBeverage extends Beverage {
  private final Recipe recipe;
  
  protected LatteMachiatoBeverage() {
    super("Latte Machiato");
    
    Recipe.Builder rb = new Recipe.Builder();
    recipe = rb.setTypeIndicator("C")
      .setSubtypeIndicator("LM")
      .setTemperatureFahrenheit(200)
      .build();
  }

  @Override
  Recipe recipe() {
    return recipe;
  }
}
