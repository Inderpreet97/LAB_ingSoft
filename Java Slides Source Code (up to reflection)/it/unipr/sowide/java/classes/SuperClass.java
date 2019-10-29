package it.unipr.sowide.java.classes;

/**
 *
 * The class {@code SuperClass} is used for showing the behavior static
 * methods with the same name in a superclass and in a subclass.
 *
 * @see SubClass
 *
**/

public class SuperClass
{
  /**
   * Class constructor.
   *
  **/
  protected SuperClass()
  {
  }

  /**
   * Prints a message.
   *
  **/
  public static void staticMethod()
  {
    System.out.println("SuperClass: inside staticMethod");
  }
}
