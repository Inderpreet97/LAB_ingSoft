package it.unipr.sowide.java.reflection;

import java.lang.reflect.Constructor;

public final class CreateInstance
{
  private CreateInstance()
  {
  }

  public static void main(final String[] args)
  {
    try
    {
      Class<?> c = Class.forName("it.unipr.sowide.java.reflection.TestTarget");

      for (Constructor<?> i : c.getConstructors())
      {
        if (i.getGenericParameterTypes().length == 0)
        {
          i.newInstance();
        }
        else if (i.getGenericParameterTypes().length == 1)
        {
          i.newInstance("specific message");
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
