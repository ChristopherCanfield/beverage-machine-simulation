package edu.bu.met.cs665.bev.controller;

public abstract class TeaBeverage extends Beverage {

  protected TeaBeverage(String name) {
    super(name);
  }

  protected String typeIndicator() {
    return "T";
  }
}
