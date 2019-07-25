package edu.bu.met.cs665.bev.controller;

/**
 * Contains information required to brew a green tea.
 * 
 * @author Christopher D. Canfield
 */
public class GreenTeaBeverage extends TeaBeverage {
  private final Recipe recipe;
  
  public GreenTeaBeverage() {
    super("Green Tea");
    
    Recipe.Builder rb = new Recipe.Builder();
    recipe = rb.setTypeIndicator(typeIndicator())
      .setSubtypeIndicator("GT")
      .setTemperatureFahrenheit(175)
      .build();
  }

  @Override
  protected Recipe recipe() {
    return recipe;
  }
}
