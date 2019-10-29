package it.unipr.sowide.java.classes;

public final class VarArgs
{
  /**
   * Class constructor.
   *
  **/
  private VarArgs()
  {
  }

  private static void manageArray(final int... v)
  {
    System.out.println("array length is " + v.length);
  }

  /* It is in conflict with the previous method.
  private static void manageArray(final int[] v)
  {
    System.out.println("array length is " + v.length);
  }
  */

  /**
   * Starts the demo.
   *
   * @param args  the method does not requires arguments.
   *
  **/
  public static void main(final String[] args)
  {
    final int val1 = 1;
    final int val2 = 2;
    final int val3 = 3;

    manageArray(val1, val2, val3);

    int[] a = new int[] {val1, val2, val3};

    manageArray(a);
  }
}
