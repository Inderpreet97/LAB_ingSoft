package it.unipr.sowide.java.classes;

/**
 *
 * The class {@code Bicycle} provides a simplified model of a bicycle.
 *
**/

public class Bicycle
{
  private int cadence;
  private int speed;
  private int gear;

  /**
   * Class constructor.
   *
  **/
  public Bicycle()
  {
    this.cadence = 0;
    this.speed   = 0;
    this.gear    = 0;
  }

  /**
   * Class constructor.
   *
   * @param c  the cadence value.
   * @param s  the speed value.
   * @param g  the gear value.
   *
  **/
  public Bicycle(final int c,
      final int s, final int g)
  {
    this.cadence = c;
    this.speed   = s;
    this.gear    = g;
  }

  /**
   * Sets the new cadence value.
   *
   * @param newValue  the new cadence value.
   *
  **/
  public void changeCadence(final int newValue)
  {
    this.cadence = newValue;
  }

  /**
   * Sets the new gear value.
   *
   * @param newValue  the new gear value.
   *
  **/
  public void changeGear(final int newValue)
  {
    this.gear = newValue;
  }

  /**
   * Increments the speed value.
   *
   * @param increment  the speed increment value.
   *
  **/
  public void speedUp(final int increment)
  {
    this.speed += increment;
  }

  /**
   * Decrements the speed value.
   *
   * @param decrement  the speed decrement value.
   *
  **/
  public void applyBrakes(final int decrement)
  {
    this.speed -= decrement;
  }

  /**
   * Gets the cadence, speed and gear values.
   *
   * @return the values.
   *
  **/
  @Override
  public String toString()
  {
    return "cadence:" + this.cadence  + " speed:"
           + this.speed + " gear:" + this.gear;
  }

  /**
   * Prints the cadence, speed and gear values.
   *
  **/
  void printStates()
  {
    System.out.println(toString());
  }
}
