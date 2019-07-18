package edu.bu.met.cs665.bev.hardware;

import java.util.concurrent.Future;
import org.apache.commons.lang.NotImplementedException;
import edu.bu.met.cs665.bev.controller.Recipe;

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
