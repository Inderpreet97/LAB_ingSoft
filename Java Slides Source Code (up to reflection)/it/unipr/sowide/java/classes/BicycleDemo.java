package it.unipr.sowide.java.classes;

import java.util.Random;

/**
 *
 * The class {@code BicycleDemo} shows an execution that performs
 * some methods of the {@code Bicycle} class.
 *
**/

public final class BicycleDemo
{
  private static final int MAX = 50;

  private BicycleDemo()
  {
  }

  /**
   * Starts the demo.
   *
   * @param args  the method does not requires arguments.
   *
  **/
  public static void main(final String[] args)
  {
    Random r = new Random();

    Bicycle bike1 = new Bicycle();
    Bicycle bike2 = new Bicycle();

    bike1.changeCadence(r.nextInt(MAX));
    bike1.speedUp(r.nextInt(MAX));
    bike1.changeGear(r.nextInt(MAX));
    bike1.printStates();
    bike1.applyBrakes(r.nextInt(MAX));
    bike1.printStates();

    bike2.changeCadence(r.nextInt(MAX));
    bike2.speedUp(r.nextInt(MAX));
    bike2.changeGear(r.nextInt(MAX));
    bike2.printStates();
    bike2.applyBrakes(r.nextInt(MAX));
    bike2.printStates();
  }
}


