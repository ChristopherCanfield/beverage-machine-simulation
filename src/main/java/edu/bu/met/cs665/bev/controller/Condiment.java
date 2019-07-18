package edu.bu.met.cs665.bev.controller;

import java.util.Objects;

public class Condiment {
  private final String name;
  private final String typeIndicator;
  
  protected Condiment(String name, String typeIndicator) {
    this.name = Objects.requireNonNull(name);
    this.typeIndicator = Objects.requireNonNull(typeIndicator);
  }
  
  public String name() {
    return name;
  }
  
  String typeIndicator() {
    return typeIndicator;
  }
}
