package edu.bu.met.cs665.bev.controller;

/**
 * A beverage that allows its recipe and name values to be set. This trades a slight amount of type
 * safety for increased flexibility. For example, we can load beverage recipes from a text file or
 * database, and construct a set of ParameterizedBeverages from it.
 *
 * @author Christopher D. Canfield
 */
public class ParameterizedBeverage extends Beverage {
  private final Recipe recipe;

  public ParameterizedBeverage(String name, String typeIndicator, String subtypeIndicator,
          int temperatureFahrenheit) {
    super(name);

    recipe = new Recipe.Builder().setTypeIndicator(typeIndicator)
      .setSubtypeIndicator(subtypeIndicator)
      .setTemperatureFahrenheit(temperatureFahrenheit)
      .build();
  }

  @Override
  protected Recipe recipe() {
    return recipe;
  }
}
