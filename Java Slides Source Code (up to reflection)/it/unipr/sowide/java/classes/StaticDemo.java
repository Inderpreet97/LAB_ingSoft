package it.unipr.sowide.java.classes;

import java.util.Random;

public final class StaticDemo
{
  private static final double MAX = 0.5;

  private static int instances;

  static
  {
    instances = 0;
  }

  private StaticDemo()
  {
    instances++;
  }

  /**
   * Gets the current number of the instances of the class.
   *
   * @return the number.
   *
  **/
  public static int getInstances()
  {
    return instances;
  }

  /**
   * Creates a random number of instances of the class.
   *
   * @param args  the method does not requires arguments.
   *
  **/
  public static void main(final String[] args)
  {
    Random r = new Random();

    while (r.nextDouble() < MAX)
    {
      new StaticDemo();

      System.out.println("number of instances is " + getInstances());
    }
  }
}

