package it.unipr.sowide.java.structures;

import java.util.Random;

/**
 *
 * The {@code ArithmeticDemo} class defines a demo
 * of the use of the arithmetic operators.
 *
**/

public final class ArithmeticDemo
{
  private ArithmeticDemo()
  {
  }

  /**
   * Performs some typical arithmetic operations.
   *
   * @param args  the method does not requires arguments.
   *
  **/
  public static void main(final String[] args)
  {
    final int max = 50;

    Random r = new Random();

    int x = r.nextInt(max) + 1;
    int y = r.nextInt(max) + 1;

    int result = x + y;
    System.out.println(x + " + " + y + " = " + result);
    result = x - y;
    System.out.println(x + " - " + y + " = " + result);
    result = x * y;
    System.out.println(x + " * " + y + " = " + result);
    result = x / y;
    System.out.println(x + " / " + y + " = " + result);
    result = x % y;
    System.out.println(x + " % " + y + " = " + result);

    x = r.nextInt(max) + 1;
    y = r.nextInt(max) + 1;

    result = x;
    result += y;
    System.out.println(x + " += " + y + " = " + result);
    result = x;
    result %= x;
    System.out.println(x + " %= " + y + " = " + result);

    System.out.println(
        "initial " + result + " ++initial " + ++result + " final " + result);
    System.out.println(
        "initial " + result + " initial++ " + result++ + " final " + result);
    System.out.println(
        "initial " + result + " --initial " + --result + " final " + result);
    System.out.println(
        "initial " + result + " initial-- " + result-- + " final " + result);
  }
}
