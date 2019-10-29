package it.unipr.sowide.java.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

public final class ConstructorInformation
{
  private ConstructorInformation()
  {
  }

  public static void main(final String[] args)
  {
    try
    {
      Class<?> c = Class.forName("it.unipr.sowide.java.reflection.TestTarget");

      for (Constructor<?> e : c.getDeclaredConstructors())
      {
        System.out.println("Constructor: " + e);

        System.out.println("Constructor name: " + e.getName());
        System.out.println("Declaring class: " + e.getDeclaringClass());
        System.out.println("Modifiers: " + e.getModifiers());

        for (Parameter p : e.getParameters())
        {
          System.out.println("Parameter: " + p);
        }
        System.out.println();

        for (Class<?> t : e.getParameterTypes())
        {
          System.out.println("Type: " + t);
        }
        System.out.println();

        for (Annotation a : e.getAnnotations())
        {
          System.out.println("Annotation: " + a);
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
