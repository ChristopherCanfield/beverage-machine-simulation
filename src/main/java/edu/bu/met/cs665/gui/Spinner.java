package edu.bu.met.cs665.gui;

import static com.google.common.base.Preconditions.checkState;

import edu.bu.met.cs665.gui.ResourceManager.ImageId;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Spinner<T> {
  // TODO (7/22/2019): This could be converted into a Swing Component.
  
  private final Button upButton;
  private final Button downButton;
  
  private final ResourceManager resourceManager;
  
  private final List<Item<T>> items;
  private int itemIndex = 0;
  private final Point itemPosition;
  
  private Image valueImage;
  
  private Spinner(Builder<T> builder) throws IOException {
    upButton = new Button(builder.upButtonRect, this::onUpButtonClicked);
    downButton = new Button(builder.downButtonRect, this::onDownButtonClicked);
    
    this.resourceManager = builder.resourceManager;
    // Clone the itemPosition, because Points are mutable.
    this.itemPosition = (Point) builder.itemPosition.clone();
    
    this.items = builder.items;

    valueImage = resourceManager.getImage(items.get(itemIndex).imageId);
  }
  
  private void onUpButtonClicked() {
    itemIndex++;
    if (itemIndex >= items.size()) {
      itemIndex = 0;
    }
  }
  
  private void onDownButtonClicked() {
    itemIndex--;
    if (itemIndex < 0) {
      itemIndex = items.size() - 1;
    }
  }
  
  /**
   * Executes this spinner's action if point is within the bounding box of the spinner's up or down
   * button.
   * @param point an x,y coordinate.
   */
  public void executeIfContains(Point point) {
    upButton.executeIfContains(point);
    downButton.executeIfContains(point);
  }

  /**
   * Draws the Spinner's value.
   * @param g a reference to the graphics context.
   */
  public void paint(Graphics g) {
    g.drawImage(valueImage, itemPosition.x, itemPosition.y, null);
  }
  
  
  //// Spinner Item ////
  
  private static class Item<T> {
    private final T value;
    private final ImageId imageId;
    
    public Item(T value, ImageId imageId) {
      this.value = Objects.requireNonNull(value);
      this.imageId = imageId;
    }
  }
  
  
  //// Spinner Builder ////
  
  /**
   * Used to construct a Spinner. All fields are required.
   * 
   * @author Christopher D. Canfield
   * @param <T> the item type associated with the spinner.
   */
  public static class Builder<T> {
    private Rectangle upButtonRect;
    private Rectangle downButtonRect;
    private ResourceManager resourceManager;
    private Point itemPosition;
    private List<Item<T>> items = new ArrayList<>();
    
    public Builder() {
    }

    public Builder<T> setUpButtonRect(Rectangle upButtonRect) {
      this.upButtonRect = upButtonRect;
      return this;
    }

    public Builder<T> setDownButtonRect(Rectangle downButtonRect) {
      this.downButtonRect = downButtonRect;
      return this;
    }

    public Builder<T> setResourceManager(ResourceManager resourceManager) {
      this.resourceManager = resourceManager;
      return this;
    }

    public Builder<T> setItemPosition(Point itemPosition) {
      this.itemPosition = itemPosition;
      return this;
    }
    
    public Builder<T> addItem(T value, ImageId imageId) {
      items.add(new Spinner.Item<T>(value, imageId));
      return this;
    }
    
    /**
     * Constructs a spinner using the arguments set in this builder. All fields are required.
     * @return the spinner.
     * @throws IOException if the specified images do not exist.
     * @throws IllegalStateException if all fields have not been set.
     */
    public Spinner<T> build() throws IOException {
      checkState(upButtonRect != null);
      checkState(downButtonRect != null);
      checkState(resourceManager != null);
      checkState(itemPosition != null);
      checkState(!items.isEmpty());
      
      return new Spinner<T>(this);
    }
  }
}
