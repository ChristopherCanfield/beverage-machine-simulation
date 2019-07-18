package edu.bu.met.cs665.bev.controller;

import java.util.Objects;

public class Beverage {
  private final String name;
  private final Recipe recipe;
  
  protected Beverage(String name, Recipe recipe) {
    this.name = Objects.requireNonNull(name);
    this.recipe = Objects.requireNonNull(recipe);
  }
  
  public String name() {
    return name;
  }
  
  Recipe recipe() {
    return recipe;
  }
}
