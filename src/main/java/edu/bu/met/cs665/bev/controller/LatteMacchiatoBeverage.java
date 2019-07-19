package edu.bu.met.cs665.bev.controller;

/**
 * Contains information required to brew a latte macchiato.
 * 
 * @author Christopher D. Canfield
 */
public class LatteMacchiatoBeverage extends CoffeeBeverage {
  private final Recipe recipe;
  
  public LatteMacchiatoBeverage() {
    super("Latte Macchiato");
    
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
