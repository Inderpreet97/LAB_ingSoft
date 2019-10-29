package it.unipr.sowide.java.classes;

import java.io.Serializable;

/**
 *
 * The class {@code Circle} provides the geometric information about a circle.
 *
**/
public class Circle implements Serializable
{
  private static final long serialVersionUID = 1L;

  private double cx;
  private double cy;
  private double radius;

  /**
   * Class constructor.
   *
  **/
  public Circle()
  {
    this.cx = 0;
    this.cy = 0;

    this.radius = 1;
  }

  /**
   * Class constructor.
   *
   * @param x  the x-axis coordinate.
   * @param y  the y-axis coordinate.
   * @param r  the radius length.
   *
  **/
  public Circle(final double x, final double y, final double r)
  {
    this.cx = x;
    this.cy = y;

    this.radius = r;
  }

  /* It is in conflict with the next constructor.
  public Circle(final Circle c)
  {
    this(c.getX(), c.getY(), c.getRadius());
  }
  */

  /**
   * Class constructor.
   *
   * @param c  another circle.
   *
   * It creates a copy of a preexisting circle.
   *
  **/
  public Circle(final Circle c)
  {
    this.cx = c.getX();
    this.cy = c.getY();

    this.radius = c.getRadius();
  }

  /**
   * Gets the x-axis coordinate.
   *
   * @return the x-axis coordinate.
   *
  **/
  public double getX()
  {
    return this.cx;
  }

  /**
   * Gets the y-axis coordinate.
   *
   * @return the y-axis coordinate.
   *
  **/
  public double getY()
  {
    return this.cy;
  }

  /**
   * Gets the radius length.
   *
   * @return the length.
   *
  **/
  public double getRadius()
  {
    return this.radius;
  }
}

