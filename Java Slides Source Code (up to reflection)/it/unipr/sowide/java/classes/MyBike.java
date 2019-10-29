package it.unipr.sowide.java.classes;

/**
*
* The class {@code MyBike} provides a simplified model
* of a bicycle implementing {@code Bike} the interface.
**/

public class MyBike implements Bike
{
  private int cadence;
  private int speed;
  private int gear;

  /**
   * Class constructor.
   *
   * @param c  the cadence value.
   * @param s  the speed value.
   * @param g  the gear value.
   *
  **/
  public MyBike(final int c,
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
  @Override
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
  @Override
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
  @Override
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
  @Override
  public void applyBrakes(final int decrement)
  {
    this.speed -= decrement;
  }

  @Override
  public String toString()
  {
    return "cadence:" + this.cadence + " speed:"
           + this.speed + " gear:" + this.gear;
  }

  /** {@inheritDoc} **/
  void printStates()
  {
    System.out.println(toString());
  }
}
