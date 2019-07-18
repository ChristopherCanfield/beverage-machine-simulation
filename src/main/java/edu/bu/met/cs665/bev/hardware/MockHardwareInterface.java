package edu.bu.met.cs665.bev.hardware;

import static com.google.common.base.Preconditions.checkArgument;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.NotImplementedException;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import edu.bu.met.cs665.bev.controller.Recipe;

/**
 * A hardware interface that does not attach to real hardware. Instead, calls to its methods are
 * logged, and mock values are returned.
 * 
 * @author Christopher D. Canfield
 */
public class MockHardwareInterface implements HardwareInterface, Callable<CompletedOrder> {
  private final int delayMilliseconds;
  private final ListeningScheduledExecutorService executor = MoreExecutors.listeningDecorator(Executors.newSingleThreadScheduledExecutor());
  
  /**
   * Constructs a new MockHardwareInterface that uses the specified delay in milliseconds to simulate
   * the time it takes to make a drink.
   * @param makeRecipeDelayMilliseconds the amount of time, in milliseconds, that makeRecipe will
   * take to return its Future.
   */
  public MockHardwareInterface(int makeRecipeDelayMilliseconds) {
    checkArgument(makeRecipeDelayMilliseconds >= 0, "The delay provided to the " + getClass().getSimpleName() + " must be >= 0.");
    this.delayMilliseconds = makeRecipeDelayMilliseconds;
  }
  
  @Override
  public ListenableFuture<CompletedOrder> makeRecipe(Recipe recipe) {
    return executor.schedule(this, delayMilliseconds, TimeUnit.MILLISECONDS);
  }

  @Override
  public CompletedOrder call() throws Exception {
    throw new NotImplementedException();
  }

}
