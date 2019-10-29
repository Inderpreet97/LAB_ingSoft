package it.unipr.sowide.java.classes;

/**
 *
 * The class {@code Employee} provides a simplified model of an employee.
 *
**/

public final class Fibonacci
{
  private Fibonacci()
  {
  }

  /**
   * Computes the Fibonacci number.
   *
   * @param n the input number.
   *
   * @return the Fibonacci number.
   *
  **/
  public static long fibonacci(final int n)
  {
    if (n <= 1)
    {
      return n;
    }
    else
    {
      return fibonacci(n - 1) + fibonacci(n - 2);
    }
  }

  /**
   * Computes the Fibonacci number of the input number from 1 to a max number.
   *
   * @param args  the max number.
   *
  **/
  public static void main(final String[] args)
  {
    var n = Integer.parseInt(args[0]);

    for (int i = 1; i <= n; i++)
    {
      System.out.println(i + ": " + fibonacci(i));
    }
  }
}
