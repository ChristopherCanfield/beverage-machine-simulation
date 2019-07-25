package edu.bu.met.cs665.bev.controller;

/**
 * Contains information required to brew an americano.
 * 
 * @author Christopher D. Canfield
 */
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
  protected Recipe recipe() {
    return recipe;
  }
}
