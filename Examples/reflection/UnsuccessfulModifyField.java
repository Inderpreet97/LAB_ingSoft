package it.unipr.sowide.java.reflection;

import java.lang.reflect.Field;

public final class UnsuccessfulModifyField
{
  private UnsuccessfulModifyField()
  {
  }

  public static void main(final String[] args)
  {
    try
    {
      Class<?> c = Class.forName("it.unipr.sowide.java.reflection.TestTarget");

      Object o = c.getConstructor().newInstance();
      Field  f = c.getDeclaredField("message");

      System.out.println("Type of message field: " + f.getType());
      System.out.println("Initial value of message field: " + f.get(o));

      f.set(o, "new message");
      System.out.println("Final value of message field: " + f.get(o));
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
