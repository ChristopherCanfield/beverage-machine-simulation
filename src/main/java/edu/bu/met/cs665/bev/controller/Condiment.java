package edu.bu.met.cs665.bev.controller;

/**
 * The interface for beverage condiments.
 * 
 * @author Christopher D. Canfield
 */
public interface Condiment {
  /**
   * Returns the user-readable name of the condiment.
   * @return the user-readable name of the condiment
   */
  String name();
  
  /**
   * Returns the condiment's type indicator.
   * @return the condiment's type indicator.
   */
  String typeIndicator();
}
