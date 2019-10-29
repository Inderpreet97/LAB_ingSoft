package it.unipr.sowide.java.classes;

public class MountainBike extends Bicycle
{
  private int seatHeight;

  /**
   * Class constructor.
   *
   * @param c  the cadence value.
   * @param s  the speed value.
   * @param g  the gear value.
   * @param h  the seat height.
   *
  **/
  public MountainBike(final int c, final int s, final int g, final int h)
  {
    super(c, s, g);

    this.seatHeight = h;
  }

  /**
   * Sets the new seat height.
   *
   * @param newValue  the new seat height value.
   *
  **/
  public void setHeight(final int newValue)
  {
    this.seatHeight = newValue;
  }

  /** {@inheritDoc} **/
  @Override
  public String toString()
  {
    return super.toString() + " seat height:" + this.seatHeight;
  }
}
