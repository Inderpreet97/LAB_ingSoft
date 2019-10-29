package it.unipr.sowide.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public final class CallMethod
{
  private CallMethod()
  {
  }

  public static void main(final String[] args)
  {
    try
    {
      Class<?> c = Class.forName("it.unipr.sowide.java.reflection.TestTarget");

      Method method = c.getMethod("print", new Class<?>[0]);

      for (Constructor<?> i : c.getConstructors())
      {
        if (i.getGenericParameterTypes().length == 0)
        {
          method.invoke(i.newInstance());
        }
        else if (i.getGenericParameterTypes().length == 1)
        {
          method.invoke(i.newInstance("specific message"));
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
