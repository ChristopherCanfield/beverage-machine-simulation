package edu.bu.met.cs665.beverage;

public interface BeverageControllerObserver {
  void onStateChanged(BeverageController controller, BeverageController.State newState);
}
