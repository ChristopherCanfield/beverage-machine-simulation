package edu.bu.met.cs665.bev.controller;

public abstract class CoffeeBeverage extends Beverage {

  protected CoffeeBeverage(String name) {
    super(name);
  }

  protected String typeIndicator() {
    return "C";
  }
}
