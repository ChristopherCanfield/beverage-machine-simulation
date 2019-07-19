package edu.bu.met.cs665.bev.controller;

/**
 * Tea beverages can extend this class to simplify their implementation.
 * 
 * @author Christopher D. Canfield
 */
public abstract class TeaBeverage extends Beverage {

  protected TeaBeverage(String name) {
    super(name);
  }

  protected String typeIndicator() {
    return "T";
  }
}
