package it.unipr.sowide.java.exception;

public final class ExceptionDemo
{
  private static final String INTEGERS = "12,34,aa,56,4b,12";

  private ExceptionDemo()
  {
  }

  public static void main(final String[] args)
  {
    for (String s : INTEGERS.split(","))
    {
      try
      {
        System.out.println("integer value is " + Integer.parseInt(s));
      }
      catch (NumberFormatException e)
      {
        System.out.println("value " + s + " is not an integer!");
      }
    }
  }
}
