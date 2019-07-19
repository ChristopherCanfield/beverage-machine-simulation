package edu.bu.met.cs665.gui;

import java.awt.Point;
import java.awt.Rectangle;

/**
 * A clickable area, which can perform an action if a click occurs within its bounds.
 * 
 * @author Christopher D. Canfield
 */
public class Button {
  private Rectangle boundingBox;
  private Runnable action;
  
  public Button(int x, int y, int width, int height, Runnable action) {
    boundingBox = new Rectangle(x, y, width, height);
    this.action = action;
  }
  
  /**
   * Executes this button's action if point is within the bounding box of this button.
   * @param point an x,y coordinate.
   */
  public void executeIfContains(Point point) {
    if (boundingBox.contains(point)) {
      action.run();
    }
  }
}
