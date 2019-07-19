package edu.bu.met.cs665.bev.controller;

/**
 * Coffee beverages can extend this class to simplify their implementation.
 * 
 * @author Christopher D. Canfield
 */
public abstract class CoffeeBeverage extends Beverage {

  protected CoffeeBeverage(String name) {
    super(name);
  }

  protected String typeIndicator() {
    return "C";
  }
}
