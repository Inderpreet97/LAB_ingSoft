package it.unipr.sowide.java.functional;

import java.util.Random;

public final class StaticMethodReferenceDemo
{
  private StaticMethodReferenceDemo()
  {
  }

  private static boolean numCheck(final IntPredicate p, final int n)
  {
    return p.check(n);
  }

  public static void main(final String[] args)
  {
    final int max = 50;

    Random r = new Random();

    int num = r.nextInt(max) - max / 2;

    IntPredicate intPredicate = number -> (number % 2) == 0;

    System.out.println("Lambda expression: " + num + " is even: "
        + numCheck(intPredicate, num));

    System.out.println("Static method reference: " + num + " is even: "
        + numCheck(IntPredicatesChecker::isEven, num));

    intPredicate = number -> number > 0;

    System.out.println("Lambda expression: " + num + " is positive: "
        + numCheck(intPredicate, num));

    System.out.println("Static method reference: " + num + " is positive: "
        + numCheck(IntPredicatesChecker::isPositive, num));
  }
}
