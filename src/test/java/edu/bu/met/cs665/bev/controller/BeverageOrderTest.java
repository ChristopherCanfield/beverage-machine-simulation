package edu.bu.met.cs665.bev.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BeverageOrderTest {
  @Test
  public void constructor() {
    BeverageOrder order = new BeverageOrder(new AmericanoBeverage());
    assertEquals(AmericanoBeverage.class, order.beverage().getClass());
  }
  
  @Test
  public void addCondiments() {
    BeverageOrder order = new BeverageOrder(new BlackTeaBeverage());
    order.addCondiment(new MilkCondiment());
    order.addCondiment(new SugarCondiment());
    assertEquals(BlackTeaBeverage.class, order.beverage().getClass());
    assertEquals(2, order.condiments().size());
  }
  
  @Test
  public void toString_Americano() {
    BeverageOrder order = new BeverageOrder(new AmericanoBeverage());
    assertEquals("Americano", order.toString());
  }
  
  @Test
  public void toStringBlackTeaMilkMilk() {
    BeverageOrder order = new BeverageOrder(new BlackTeaBeverage());
    order.addCondiment(new MilkCondiment());
    order.addCondiment(new MilkCondiment());
    assertEquals("Black Tea with Milk, Milk", order.toString());
  }
  
  @Test
  public void toStringEspressoMilkSugarSugar() {
    BeverageOrder order = new BeverageOrder(new EspressoBeverage());
    order.addCondiment(new MilkCondiment());
    order.addCondiment(new SugarCondiment());
    order.addCondiment(new SugarCondiment());
    assertEquals("Espresso with Milk, Sugar, Sugar", order.toString());
  }
}
