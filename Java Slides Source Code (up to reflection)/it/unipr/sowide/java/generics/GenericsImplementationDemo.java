package it.unipr.sowide.java.generics;

public final class GenericsImplementationDemo
{
  private GenericsImplementationDemo()
  {
  }

  /*
  public static <T> T newTest()
  {
    return new T();
  }
  */

  public static void main(final String[] args)
  {
    var instance1 = new ConcreteKeyValuePair<String, Integer>("key", 1);

    System.out.println("class of instance1 is " + instance1.getClass());

    var instance2 = new ConcreteKeyValuePair<String, String>("key", "value");

    System.out.println("class of instance2 is " + instance2.getClass());
    /*
    if (instance1 instanceof ConcreteKeyValuePair<String, Integer>)
    {
      System.out.println("key " + instance1.getKey());
    }
    */

    if (instance1 instanceof ConcreteKeyValuePair<?, ?>)
    {
      System.out.println("key " + instance1.getKey());
    }

    /*
    ConcreteKeyValuePair<String, Integer>[] array =
        new ConcreteKeyValuePair<String, Integer>[10];
    */
  }
}
