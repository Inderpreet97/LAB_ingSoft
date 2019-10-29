package it.unipr.sowide.java.generics;

import java.util.ArrayList;
import java.util.List;


public final class GenericMethodDemo
{
  private GenericMethodDemo()
  {
  }

  static <T> void fromArrayToList(final T[] a, final List<T> l)
  {
    for (T e : a)
    {
      l.add(e);
    }
  }

  public static void main(final String[] args)
  {
    String[] array = new String[] {
        "firstkey", "firstvalue"
    };

    List<String> l = new ArrayList<>();

    fromArrayToList(array, l);

    int i = 0;

    for (String s : l)
    {
      System.out.println("element " + i++ + " is " + s);
    }
  }
}


