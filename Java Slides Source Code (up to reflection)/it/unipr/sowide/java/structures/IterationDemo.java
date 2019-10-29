package it.unipr.sowide.java.structures;

import java.util.Random;

/**
 *
 * The {@code IterationDemo} class defines a demo
 * of the use of the iteration statements.
 *
**/

public final class IterationDemo
{
  private IterationDemo()
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
    final int range = 100;
    final int max = 50;
    final int end = 0;

    Random random = new Random();

    int i = random.nextInt(range);

    while (i != end)
    {
      if (i <= max)
      {
        System.out.println("value is " + i);
      }

      i = random.nextInt(range);
    }

    do
    {
      i = random.nextInt(range);

      if (i > max)
      {
        continue;
      }
      else if (i == end)
      {
        break;
      }

      System.out.println("value is " + i);
    }
    while (true);

    int[] array = new int[random.nextInt(range)];

    for (int e = 0; e < array.length; e++)
    {
      array[e] = random.nextInt(range);
    }

    int sum = 0;

    for (int e = 0; e < array.length; e++)
    {
      sum += array[e];
    }

    System.out.println("sum is " + sum);

    sum = 0;

    for (final int v : array)
    {
      sum += v;
    }

    System.out.println("sum is " + sum);
  }
}
