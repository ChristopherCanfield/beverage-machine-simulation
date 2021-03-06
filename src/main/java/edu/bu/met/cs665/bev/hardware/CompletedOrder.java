package edu.bu.met.cs665.bev.hardware;

import edu.bu.met.cs665.bev.controller.Recipe;

import java.time.Instant;

public class CompletedOrder {
  private String hardwareInterfaceName;
  private Recipe recipe;
  private Instant finishedAtTime;
  
  CompletedOrder(HardwareInterface hardwareInterface, Recipe recipe, 
      Instant finishedAtTime) {
    this.hardwareInterfaceName = hardwareInterface.getClass().getSimpleName();
    this.recipe = recipe;
    this.finishedAtTime = finishedAtTime;
  }
  
  public String hardwareName() {
    return hardwareInterfaceName;
  }
  
  public Recipe recipe() {
    return recipe;
  }
  
  public Instant finishedAtTime() {
    return finishedAtTime;
  }
}
