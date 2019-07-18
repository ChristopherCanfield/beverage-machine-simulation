package edu.bu.met.cs665.bev.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
  
  private Recipe buildRecipe1() {
    Recipe.Builder builder = new Recipe.Builder();
    builder.setTypeIndicator("C");
    builder.setSubtypeIndicator("A");
    builder.setTemperatureFahrenheit(210);
    return builder.build();
  }
  
  private Recipe buildRecipe2() {
    Recipe.Builder builder = new Recipe.Builder();
    builder.setTypeIndicator("T");
    builder.setSubtypeIndicator("A");
    builder.setTemperatureFahrenheit(210);
    return builder.build();
  }
  
  @Test
  public void equals_equivalent() {
    Recipe recipeA = buildRecipe1();
    Recipe recipeB = buildRecipe1();
    
    assertEquals(recipeA, recipeB);
  }
  
  @Test
  public void equals_notEquivalent() {
    Recipe recipeA = buildRecipe1();
    Recipe recipeB = buildRecipe2();
    
    assertNotEquals(recipeA, recipeB);
  }
  
  @Test
  public void equals_same() {
    Recipe recipe = buildRecipe1();
    assertEquals(recipe, recipe);
  }
  
  @Test
  public void hashCode_equivalent() {
    Recipe recipeA = buildRecipe1();
    Recipe recipeB = buildRecipe1();
    
    assertEquals(recipeA.hashCode(), recipeB.hashCode());
  }
  
  @Test
  public void hashCode_notEquivalent() {
    Recipe recipeA = buildRecipe1();
    Recipe recipeB = buildRecipe2();
    
    assertNotEquals(recipeA.hashCode(), recipeB.hashCode());
  }
  
  /**
   * Ensure that the hashcode is always the same.
   */
  @Test
  public void hashCode_same() {
    Recipe recipe = buildRecipe1();
    
    assertEquals(recipe.hashCode(), recipe.hashCode());
  }
}
