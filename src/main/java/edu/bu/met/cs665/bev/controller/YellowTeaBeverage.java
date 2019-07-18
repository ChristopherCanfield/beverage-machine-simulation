package edu.bu.met.cs665.bev.controller;

public class YellowTeaBeverage extends TeaBeverage {
  private final Recipe recipe;
  
  public YellowTeaBeverage() {
    super("Yellow Tea");
    
    Recipe.Builder rb = new Recipe.Builder();
    recipe = rb.setTypeIndicator(typeIndicator())
      .setSubtypeIndicator("YT")
      .setTemperatureFahrenheit(170)
      .build();
  }

  @Override
  Recipe recipe() {
    return recipe;
  }
}
