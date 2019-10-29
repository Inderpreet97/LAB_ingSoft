package it.unipr.sowide.java.reflection;

import java.lang.reflect.Array;

public final class ModifyArray
{
  private static final int[] INTEGERS = {1, 2, 3, 4, 5};
  private static final String[] STRINGS = {"a", "b", "c", "d", "e"};
  private static final int INTNEWSIZE = 10;
  private static final int STRINGNEWSIZE = 7;

  private ModifyArray()
  {
  }

  public static Object increaseeArraySize(final Object o, final int l)
  {
    Class<?> type  = o.getClass().getComponentType();
    Object   array = Array.newInstance(type, l);
    int length     = Array.getLength(o);

    System.arraycopy(o, 0, array, 0, length);

    return array;
  }

  private static void print(final Object o)
  {
    Class<?> c = o.getClass();

    if (!c.isArray())
    {
      return;
    }

    System.out.println("\nArray length: " + Array.getLength(o));

    for (int i = 0; i < Array.getLength(o); i++)
    {
      System.out.print(Array.get(o, i) + " ");
    }
  }

  public static void main(final String[] args)
  {
    print(INTEGERS);

    int[] newIntArray = (int[]) increaseeArraySize(INTEGERS, INTNEWSIZE);

    print(newIntArray);

    print(STRINGS);

    String[] newstringArray = (String[]) increaseeArraySize(STRINGS, STRINGNEWSIZE);

    print(newstringArray);
  }
}
