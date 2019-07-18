package edu.bu.met.cs665.bev.controller;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CondimentTest {
  @Test
  public void parameterizedCondiment() {
    final String name = "Test";
    final String typeIndicator = "T";
    
    Condiment condiment = new ParameterizedCondiment(name, typeIndicator);
    assertEquals(name, condiment.name());
    assertEquals(typeIndicator, condiment.typeIndicator());
  }
  
  @Test
  public void milkCondiment() {
    Condiment condiment = new MilkCondiment();
    assertEquals("Milk", condiment.name());
    assertEquals("M", condiment.typeIndicator());
  }
  
  @Test
  public void sugarCondiment() {
    Condiment condiment = new SugarCondiment();
    assertEquals("Sugar", condiment.name());
    assertEquals("S", condiment.typeIndicator());
  }
}
