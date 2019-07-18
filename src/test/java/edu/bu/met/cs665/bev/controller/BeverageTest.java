package edu.bu.met.cs665.bev.controller;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class BeverageTest {
  @Test
  public void parameterizedBeverage() {
    final String name = "Test";
    final String typeIndicator = "TI";
    final String subtypeIndicator = "SI";
    final int temperatureFahrenheit = 100;
    Beverage bev = new ParameterizedBeverage(name, typeIndicator, subtypeIndicator, temperatureFahrenheit);
    
    Recipe recipe = new Recipe.Builder()
        .setTypeIndicator(typeIndicator)
        .setSubtypeIndicator(subtypeIndicator)
        .setTemperatureFahrenheit(temperatureFahrenheit)
        .build();
    
    assertEquals(name, bev.name());
    assertEquals(recipe, bev.recipe());
  }
  
  
  //// Teas ////
  
  @Test
  public void blackTeaBeverage() {
    Beverage bev = new BlackTeaBeverage();
    assertEquals("Black Tea", bev.name());
    assertEquals("T:BT:205", bev.recipe().hardwareCommand());
  }
  
  @Test
  public void greenTeaBeverage() {
    Beverage bev = new GreenTeaBeverage();
    assertEquals("Green Tea", bev.name());
    assertEquals("T:GT:175", bev.recipe().hardwareCommand());
  }
  
  @Test
  public void yellowTeaBeverage() {
    Beverage bev = new YellowTeaBeverage();
    assertEquals("Yellow Tea", bev.name());
    assertEquals("T:YT:170", bev.recipe().hardwareCommand());
  }
  
  
  //// Coffees ////
  
  @Test
  public void americanoBeverage() {
    Beverage bev = new AmericanoBeverage();
    assertEquals("Americano", bev.name());
    assertEquals("C:A:210", bev.recipe().hardwareCommand());
  }
  
  @Test
  public void espressoBeverage() {
    Beverage bev = new EspressoBeverage();
    assertEquals("Espresso", bev.name());
    assertEquals("C:E:210", bev.recipe().hardwareCommand());
  }
  
  @Test
  public void LatteMachiatoBeverage() {
    Beverage bev = new LatteMachiatoBeverage();
    assertEquals("Latte Machiato", bev.name());
    assertEquals("C:LM:205", bev.recipe().hardwareCommand());
  }
}
