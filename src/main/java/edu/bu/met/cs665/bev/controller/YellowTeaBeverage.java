package edu.bu.met.cs665.bev.controller;

/**
 * Contains information required to brew a yellow tea.
 * 
 * @author Christopher D. Canfield
 */
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
  protected Recipe recipe() {
    return recipe;
  }
}
