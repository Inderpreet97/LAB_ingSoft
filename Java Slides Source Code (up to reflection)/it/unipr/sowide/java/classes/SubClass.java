package it.unipr.sowide.java.classes;

/**
 *
 * The class {@code SubClass} is used for showing the behavior static
 * methods with the same name in a superclass and in a subclass.
 *
 * @see SuperClass
 *
**/

public class SubClass extends SuperClass
{
  /**
   * Prints a message.
   *
  **/
  public static void staticMethod()
  {
    System.out.println("SubClass: inside staticMethod");
  }

  /**
   * Starts the demo.
   *
   * @param args  the method does not requires arguments.
   *
  **/
  public static void main(final String[] args)
  {
    /*
    SuperClass superSuper = new SuperClass();
    SuperClass superSub = new SubClass();
    SubClass subSub = new SubClass();
    */

    // Static methods should not called by instances.

    /*
    superSuper.staticMethod();
    superSub.staticMethod();
    subSub.staticMethod();
    */

    // Use ClassName

    SuperClass.staticMethod();
    SubClass.staticMethod();
  }
}
