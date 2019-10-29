package it.unipr.sowide.java.exception;

public final class NonPositiveDoubleExceptionDemo
{
  private NonPositiveDoubleExceptionDemo()
  {
  }

  public static void main(final String[] args)
  {
    try
    {
      if (args.length > 0)
      {
        double d = Double.parseDouble(args[0]);

        System.out.println("logarith of: " + args[0] + " is " + log(d));
      }
    }
    catch (NonPositiveDoubleException e)
    {
      System.out.println("The exception was: " + e.getMessage());
    }
  }

  public static double log(final double d) throws NonPositiveDoubleException
  {
    if (d > 0)
    {
      return Math.log(d);
    }
    else
    {
      throw new NonPositiveDoubleException();
    }
  }
}
