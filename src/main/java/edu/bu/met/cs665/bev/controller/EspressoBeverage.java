package edu.bu.met.cs665.bev.controller;

/**
 * Contains information required to brew an espresso.
 * 
 * @author Christopher D. Canfield
 */
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
  protected Recipe recipe() {
    return recipe;
  }
}
