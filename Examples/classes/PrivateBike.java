package it.unipr.sowide.java.classes;

import java.util.Random;

/**
 *
 * The interface {@code PrivateBike} defines the methods
 * that a simple bicycle model must provide.
 *
**/

public interface PrivateBike
{
  /**
   * The default gear value.
  **/
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
  default int defaultGear()
  {
    return computeGear();
  }

  /**
   * Computes the gear value.
   *
   * @return the value.
   *
  **/
  private int computeGear()
  {
    Random r = new Random();

    int v = r.nextInt(GEAR);

    if (v < GEAR / 2)
    {
      return GEAR / 2;
    }

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

