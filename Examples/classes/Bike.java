package it.unipr.sowide.java.classes;

/**
 *
 * The interface {@code Bike} declares the methods
 * that a simple bicycle model must provide.
 *
**/

public interface Bike
{
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
