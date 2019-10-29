package it.unipr.sowide.java.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class MethodInformation
{
  private MethodInformation()
  {
  }

  public static void main(final String[] args)
  {
    try
    {
      Class<?> c = Class.forName("it.unipr.sowide.java.reflection.TestTarget");

      Set<Method> methods = new HashSet<>();

      methods.addAll(Arrays.asList(c.getMethods()));
      methods.addAll(Arrays.asList(c.getDeclaredMethods()));

      for (Method m : methods)
      {
        System.out.println("Method name: " + m.getName());
        System.out.println("Return type: " + m.getReturnType());
        System.out.println("Declaring class: " + m.getDeclaringClass());
        System.out.println("Modifiers: " + m.getModifiers());

        for (Parameter p : m.getParameters())
        {
          System.out.println("Parameter: " + p);
        }

        for (Class<?> t : m.getParameterTypes())
        {
          System.out.println("Type: " + t);
        }

        for (Annotation a : m.getAnnotations())
        {
          System.out.println("Annotation: " + a);
        }

        System.out.println();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
