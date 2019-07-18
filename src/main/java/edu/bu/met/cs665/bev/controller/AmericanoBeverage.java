package edu.bu.met.cs665.bev.controller;

public class AmericanoBeverage extends CoffeeBeverage {
  private final Recipe recipe;
  
  public AmericanoBeverage() {
    super("Americano");
    
    Recipe.Builder rb = new Recipe.Builder();
    recipe = rb.setTypeIndicator(typeIndicator())
      .setSubtypeIndicator("A")
      .setTemperatureFahrenheit(210)
      .build();
  }

  @Override
  Recipe recipe() {
    return recipe;
  }
}
