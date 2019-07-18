package edu.bu.met.cs665.bev.controller;

import java.util.Objects;

public abstract class Beverage {
  private final String name;
  
  protected Beverage(String name) {
    this.name = Objects.requireNonNull(name);
  }
  
  public String name() {
    return name;
  }
  
  abstract Recipe recipe();
}
