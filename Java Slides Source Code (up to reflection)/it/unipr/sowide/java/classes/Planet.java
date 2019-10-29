package it.unipr.sowide.java.classes;

public enum Planet
{
  MERCURY(3.303e+23, 2.4397e6),
  VENUS(4.869e+24, 6.0518e6),
  EARTH(5.976e+24, 6.37814e6),
  MARS(6.421e+23, 3.3972e6),
  JUPITER(1.9e+27, 7.1492e7),
  SATURN(5.688e+26, 6.0268e7),
  URANUS(8.686e+25, 2.5559e7),
  NEPTUNE(1.024e+26, 2.4746e7),
  PLUTO(1.27e+22, 1.137e6);

  private final double mass; // in kilograms
  private final double radius; // in meters

  public static final double G = 6.67300E-11;

  /**
   * Class constructor.
   *
   * @param m  the mass value.
   * @param r  the radius length.
   *
  **/
  Planet(final double m, final double r)
  {
    this.mass = m;
    this.radius = r;
  }

  /**
   * Gets the surface gravity value.
   *
   * @return the value.
   *
  **/
  public double surfaceGravity()
  {
    return G * this.mass / (this.radius * this.radius);
  }

  /**
   * Gets the surface weight value.
   *
   * @param m  the mass value.
   *
   * @return the value.
   *
  **/
  public double surfaceWeight(final double m)
  {
    return m * surfaceGravity();
  }

  /**
   * Computes the surface weight of the different planets.
   *
   * @param args  the method does not requires arguments.
   *
  **/
  public static void main(final String[] args)
  {
    double earthWeight = Double.parseDouble(args[0]);

    double mass = earthWeight / EARTH.surfaceGravity();

    for (Planet p : Planet.values())
    {
      System.out.printf("Your weight on %s is %f%n",
          p, p.surfaceWeight(mass));
    }
  }
}
