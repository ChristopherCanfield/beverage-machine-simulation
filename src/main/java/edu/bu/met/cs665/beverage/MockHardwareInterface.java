package edu.bu.met.cs665.beverage;

import java.util.concurrent.Future;
import org.apache.commons.lang.NotImplementedException;

/**
 * A hardware interface that does not attach to real hardware. Instead, calls to its methods are
 * logged, and mock values are returned.
 * 
 * @author Christopher D. Canfield
 */
public class MockHardwareInterface implements HardwareInterface {

  @Override
  public Future<CompletedOrder> makeRecipe(Recipe recipe) {
    throw new NotImplementedException();
  }

}
