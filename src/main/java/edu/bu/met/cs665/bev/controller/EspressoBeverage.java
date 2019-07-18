package edu.bu.met.cs665.bev.controller;

public class EspressoBeverage extends Beverage {
  private final Recipe recipe;
  
  public EspressoBeverage() {
    super("Espresso");
    
    Recipe.Builder rb = new Recipe.Builder();
    recipe = rb.setTypeIndicator("C")
      .setSubtypeIndicator("E")
      .setTemperatureFahrenheit(200)
      .build();
  }

  @Override
  Recipe recipe() {
    return recipe;
  }
}
