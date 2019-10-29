package it.unipr.sowide.java.structures;

import java.util.Random;

/**
 *
 * The {@code BitDemo} class defines a demo
 * of the use of the bitwise operators.
 *
**/

public final class BitDemo
{
  private BitDemo()
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
    final int max = 50;

    Random r = new Random();

    int x = r.nextInt(max);
    int y = r.nextInt(max);

    int result = ~x; // apply "invert" operator
    System.out.println("~" + x + " = " + result);

    result = x | y; // apply the "or" operator
    System.out.println(x + " | " + y + " = " + result);

    result = x & y; // apply the "and" operator
    System.out.println(x + " & " + y + " = " + result);

    result = x ^ y; // apply the "xor" operator
    System.out.println(x + " ^ " + y + " = " + result);

    result = x << y; // apply "shift left" operator
    System.out.println(x + " << " + y + " = " + result);

    result = x >> y; // apply "shift right" operator
    System.out.println(x + " >> " + y + " = " + result);
  }
}
