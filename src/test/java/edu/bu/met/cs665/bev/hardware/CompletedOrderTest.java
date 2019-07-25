package edu.bu.met.cs665.bev.hardware;

import static org.junit.Assert.assertEquals;
import edu.bu.met.cs665.bev.controller.Recipe;
import java.time.Instant;
import org.junit.Before;
import org.junit.Test;

public class CompletedOrderTest {
  private static final HardwareInterface hardwareInterface = new MockHardwareInterface(0);
  private static final Recipe recipe = new Recipe.Builder()
      .setTypeIndicator("C")
      .setSubtypeIndicator("A")
      .setTemperatureFahrenheit(205)
      .build();
  private static final Instant FINISHED_TIME = Instant.now();

  private CompletedOrder completedOrder;

  @Before
  public void beforeEach() {
    completedOrder = new CompletedOrder(hardwareInterface,
        recipe,
        FINISHED_TIME);
  }

  @Test
  public void hardwareName() {
    assertEquals(hardwareInterface.getClass().getSimpleName(), completedOrder.hardwareName());
  }

  @Test
  public void recipe() {
    assertEquals(recipe, completedOrder.recipe());
  }

  @Test
  public void finishedAtTime() {
    assertEquals(FINISHED_TIME, completedOrder.finishedAtTime());
  }
}
