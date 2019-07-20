package edu.bu.met.cs665.bev.controller;

import java.util.Objects;

/**
 * A condiment that allows its recipe and name values to be set. This trades a slight amount of 
 * type safety for increased flexibility. For example, we can load condiment data from a text file 
 * or database, and construct a set of ParameterizedCondiments from it.
 * 
 * @author Christopher D. Canfield
 */
public class ParameterizedCondiment implements Condiment {
  private final String name;
  private final String typeIndicator;
  
  public ParameterizedCondiment(String name, String typeIndicator) {
    this.name = Objects.requireNonNull(name);
    this.typeIndicator = Objects.requireNonNull(typeIndicator);
  }
  
  @Override
  public String name() {
    return name;
  }
  
  @Override
  public String typeIndicator() {
    return typeIndicator;
  }
}
