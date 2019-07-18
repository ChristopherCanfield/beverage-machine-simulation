package edu.bu.met.cs665.bev.controller;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class RecipeTest {
  @Test
  public void buildSimpleRecipe() {
    Recipe.Builder builder = new Recipe.Builder();
    builder.setTypeIndicator("C");
    builder.setSubtypeIndicator("A");
    builder.setTemperatureFahrenheit(210);
    Recipe recipe = builder.build();
    
    assertEquals("C:A:210", recipe.hardwareCommand());
  }
}
