package edu.bu.met.cs665.bev.controller;

public class EspressoBeverage extends CoffeeBeverage {
  private final Recipe recipe;
  
  public EspressoBeverage() {
    super("Espresso");
    
    Recipe.Builder rb = new Recipe.Builder();
    recipe = rb.setTypeIndicator(typeIndicator())
      .setSubtypeIndicator("E")
      .setTemperatureFahrenheit(210)
      .build();
  }

  @Override
  Recipe recipe() {
    return recipe;
  }
}