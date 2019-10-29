package it.unipr.sowide.java.structures;

import java.util.Random;

/**
 *
 * The {@code ConditionalDemo} class defines a demo of the use
 * of the conditional statements and of the relational operators.
 *
**/

public final class ConditionalDemo
{
  private ConditionalDemo()
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
    Random random = new Random();

    final int range = 100;

    int v1 = random.nextInt(range);
    int v2 = random.nextInt(range);
    int v3 = random.nextInt(range);

    System.out.println("v1 value is " + v1);
    System.out.println("v2 value is " + v2);

    if (v1 == v2)
    {
      System.out.println("v1 == v2");
    }
    else
    {
      System.out.println("v1 != v2");

      if (v1 > v2)
      {
        System.out.println("v1 > v2");
      }
      else if (v1 < v2)
      {
        System.out.println("v1 < v2");
      }
    }

    boolean b = (v1 >= v2) && ((v1 <= v3) || !(v2 < v3));

    System.out.println("(v1 >= v2) && ((v1 <= v3) || !(v2 < v3)) is " + b);

    v3 = (v2 > v1) ? v2 : v1;

    System.out.println("v3 is " + v3);
  }
}
