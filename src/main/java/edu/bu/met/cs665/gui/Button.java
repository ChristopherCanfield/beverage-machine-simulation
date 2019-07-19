package edu.bu.met.cs665.gui;

import java.awt.Point;
import java.awt.Rectangle;

public class Button {
  private Rectangle boundingBox;
  private Runnable action;
  
  public Button(int x, int y, int width, int height, Runnable action) {
    boundingBox = new Rectangle(x, y, width, height);
    this.action = action;
  }
  
  public void executeIfContains(Point point) {
    if (boundingBox.contains(point)) {
      action.run();
    }
  }
}
