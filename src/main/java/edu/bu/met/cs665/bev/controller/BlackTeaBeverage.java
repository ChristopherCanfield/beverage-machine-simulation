package edu.bu.met.cs665.bev.controller;

/**
 * Contains information required to brew a black tea.
 * 
 * @author Christopher D. Canfield
 */
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
  protected Recipe recipe() {
    return recipe;
  }
}
