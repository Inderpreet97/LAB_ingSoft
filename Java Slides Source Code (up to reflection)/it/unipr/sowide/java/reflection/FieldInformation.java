package it.unipr.sowide.java.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class FieldInformation
{
  private FieldInformation()
  {
  }

  public static void main(final String[] args)
  {
    try
    {
      Class<?> c = Class.forName("it.unipr.sowide.java.reflection.TestTarget");

      Set<Field> fields = new HashSet<>();

      fields.addAll(Arrays.asList(c.getFields()));
      fields.addAll(Arrays.asList(c.getDeclaredFields()));

      for (Field f : fields)
      {
        System.out.println("Field name: " + f.getName());
        System.out.println("Field type: " + f.getType());
        System.out.println("Declaring class: " + f.getDeclaringClass());
        System.out.println("Modifiers: " + f.getModifiers());

        for (Annotation a : f.getAnnotations())
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
