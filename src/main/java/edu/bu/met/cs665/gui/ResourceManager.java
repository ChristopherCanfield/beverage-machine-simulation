package edu.bu.met.cs665.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * Loads and stores images.
 * 
 * @author Christopher D. Canfield
 */
public class ResourceManager {
  public enum ImageId {
    MACHINE("beverage-machine.png"),
    MACHINE_WITH_DRINK("beverage-machine-with-drink.png"),
    CHAR_0("char-0.png"),
    CHAR_1("char-1.png"),
    CHAR_2("char-2.png"),
    CHAR_3("char-3.png"),
    TEXT_LATTE_MACCHIATO("text-latte-macchiato.png"),
    TEXT_AMERICANO("text-americano.png"),
    TEXT_ESPRESSO("text-espresso.png"),
    TEXT_GREEN_TEA("text-green-tea.png"),
    TEXT_BLACK_TEA("text-black-tea.png"),
    TEXT_YELLOW_TEA("text-yellow-tea.png");
    
    private final String path;
    
    private ImageId(String path) {
      this.path = path;
    }
    
    String path() {
      return path;
    }
  }
  
  private Map<ImageId, Image> images = new HashMap<>();
  
  /**
   * Returns the specified image. If the image has not already been loaded, it will be loaded from
   * disk. Once loaded, a reference to the image is cache in this resource manager.
   * @param imageId the image's ID, from the ImageId enum.
   * @return the image.
   * @throws IOException if the image can't be loaded.
   */
  public Image getImage(ImageId imageId) throws IOException {
    Image image = images.get(imageId);
    if (image == null) {
      image = loadImage(imageId);
    }
    return image;
  }
  
  private BufferedImage loadImage(ImageId imageId) throws IOException {
    InputStream imageInputStream = getClass().getClassLoader().getResourceAsStream(imageId.path());
    BufferedImage image = ImageIO.read(imageInputStream);
    images.put(imageId, image);
    return image;
  }
}
