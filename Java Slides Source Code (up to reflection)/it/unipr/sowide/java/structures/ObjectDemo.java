package it.unipr.sowide.java.structures;

/**
 *
 * The {@code ObjectyDemo} class defines a demo of the use of objects.
 *
**/

public final class ObjectDemo
{
  private ObjectDemo()
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
    int result = 1 + Integer.valueOf(2);

    String string = "header";

    string = string + " and tail";

    final int i = 4;

    Object[] o = {string, Integer.valueOf(result), i};

    System.out.println("value of array o is  " + o);

    System.out.println("value of element 0 of o is  " + o[0]);

    if (o[0] instanceof String)
    {
      string = (String) o[0];
    }

    System.out.println("value of element 1 of o is  " + o[1]);
    System.out.println("value of element 2 of o is  " + o[2]);

    if (o[2] instanceof Integer)
    {
      result = (int) o[2];
    }
  }
}
