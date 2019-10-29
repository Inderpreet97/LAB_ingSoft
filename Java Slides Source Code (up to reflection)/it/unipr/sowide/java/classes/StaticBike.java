package it.unipr.sowide.java.classes;

public interface StaticBike
{
  int GEAR = 50;

  /**
   * Sets the new cadence value.
   *
   * @param newValue  the new cadence value.
   *
  **/
  void changeCadence(int newValue);

  /**
   * Sets the new gear value.
   *
   * @param newValue  the new gear value.
   *
  **/
  void changeGear(int newValue);

  /**
   * Gets the gear value.
   *
   * @return the value.
   *
  **/
  static int staticGear()
  {
    return GEAR;
  }

  /**
   * Increments the speed value.
   *
   * @param increment  the speed increment value.
   *
  **/
  void speedUp(int increment);

  /**
   * Decrements the speed value.
   *
   * @param decrement  the speed decrement value.
   *
  **/
  void applyBrakes(int decrement);
}

