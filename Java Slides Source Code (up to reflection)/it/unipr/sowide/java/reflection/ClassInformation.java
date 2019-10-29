package it.unipr.sowide.java.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class ClassInformation
{
  private ClassInformation()
  {
  }

  public static void main(final String[] args)
  {
    try
    {
      Class<?> c = Class.forName("it.unipr.sowide.java.reflection.TestTarget");

      System.out.println("Class name: " + c.getName());
      System.out.println("Class simple name: " + c.getSimpleName());
      System.out.println("Superclass name: " + c.getSuperclass());
      System.out.println("Package name: " + c.getPackageName());
      System.out.println();

      for (Class<?> i : c.getInterfaces())
      {
        System.out.println("Interface: " + i);
      }
      System.out.println();

      for (Constructor<?> i : c.getDeclaredConstructors())
      {
        System.out.println("Constructor: " + i);
      }
      System.out.println();

      Set<Method> methods = new HashSet<>();
      methods.addAll(Arrays.asList(c.getMethods()));
      methods.addAll(Arrays.asList(c.getDeclaredMethods()));
      methods.forEach((m) -> System.out.println("Method: " + m));
      System.out.println();

      Set<Field> fields = new HashSet<>();
      fields.addAll(Arrays.asList(c.getFields()));
      fields.addAll(Arrays.asList(c.getDeclaredFields()));
      fields.forEach((f) -> System.out.println("Field: " + f));
      System.out.println();

      for (Annotation a : c.getAnnotations())
      {
        System.out.println("Annotation: " + a);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
