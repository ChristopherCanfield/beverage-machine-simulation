package edu.bu.met.cs665.bev.controller;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static java.util.Objects.requireNonNull;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
  private final String hardwareCommand;
  
  private Recipe(Recipe.Builder builder) {
    StringBuilder sb = new StringBuilder();
    sb.append(builder.typeIndicator);
    sb.append(":");
    sb.append(builder.subtypeIndicator);
    sb.append(":");
    sb.append(builder.temperatureFahrenheit);
    
    if (!builder.condiments.isEmpty()) {
      for (Condiment condiment : builder.condiments) {
        sb.append(":");
        sb.append(condiment.typeIndicator());
      }
    }
    
    hardwareCommand = sb.toString();
  }
  
  public String hardwareCommand() {
    return hardwareCommand;
  }
  
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Recipe)) {
      return false;
    }
    
    Recipe otherRecipe = (Recipe) other;
    return hardwareCommand().equals(otherRecipe.hardwareCommand);
  }
  
  @Override
  public int hashCode() {
    return hardwareCommand.hashCode();
  }
  
  /**
   * Used the construct a Recipe. Everything is required except condiments.
   * 
   * @author Christopher D. Canfield
   */
  public static class Builder {
    private String typeIndicator;
    private String subtypeIndicator;
    private static final int INVALID_TEMP = -1000;
    private int temperatureFahrenheit = INVALID_TEMP;
    private List<Condiment> condiments = new ArrayList<>();

    public Builder() {
    }
    
    /**
     * Sets the type indicator. Required.
     * @param typeIndicator the recipe's type indicator.
     * @return reference to this builder.
     */
    public Builder setTypeIndicator(String typeIndicator) {
      this.typeIndicator = requireNonNull(typeIndicator);
      return this;
    }
    
    /**
     * Sets the subtype indicator. Required.
     * @param subtypeIndicator the recipe's subtype indicator.
     * @return reference to this builder.
     */
    public Builder setSubtypeIndicator(String subtypeIndicator) {
      this.subtypeIndicator = requireNonNull(subtypeIndicator);
      return this;
    }
    
    /**
     * Sets the temperature, in Fahrenheit. Must be within the range of 0 and 300, inclusive. Required.
     * @param temperature the temperature, in Fahrenheit.
     * @return reference to this builder.
     */
    public Builder setTemperatureFahrenheit(int temperature) {
      // Sanity checks. The machine may not actually be capable of reaching these extremes.
      checkArgument(temperature >= 0);
      checkArgument(temperature <= 300);
      
      this.temperatureFahrenheit = temperature;
      return this;
    }
    
    /**
     * Adds a condiment. Optional.
     * @param condiment the condiment to add.
     * @return reference to this builder.
     */
    public Builder addCondiment(Condiment condiment) {
      condiments.add(requireNonNull(condiment));
      return this;
    }
    
    /**
     * Constructs the recipe.
     * @return a recipe using the settings provided to this builder.
     * @throws IllegalStateException when the typeIndicator, subtypeIndicator, or temperatureFahrenheit have not been set.
     */
    public Recipe build() {
      checkState(typeIndicator != null, "typeIndicator must be set in the Recipe Builder before calling build().");
      checkState(subtypeIndicator != null, "subtypeIndicator must be set in the Recipe Builder before calling build().");
      checkState(temperatureFahrenheit != INVALID_TEMP, "temperatureFahrenheit must be set in the Recipe Builder before calling build().");
      
      return new Recipe(this);
    }
  }
}
