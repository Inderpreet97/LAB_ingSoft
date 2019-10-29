package it.unipr.sowide.java.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class AppendIntegerDemo
{
  private AppendIntegerDemo()
  {
  }

  public static void appendI(final List<Integer> l, final int n)
  {
    for (int i = 1; i <= n; i++)
    {
      l.add(i);
    }
  }

  /*
  public static void appendE(final List<? extends Integer> l, final int n)
  {
    for (int i = 1; i <= n; i++)
    {
      l.add(i);
    }
  }
  */

  public static void appendS(final List<? super Integer> l, final int n)
  {
    for (int i = 1; i <= n; i++)
    {
      l.add(i);
    }
  }

  public static void main(final String[] args)
  {
    ArrayList<Integer> i = new ArrayList<>(Arrays.asList(1, 3, 3));

    appendI(i, 5);

    appendS(i, 5);
  }
}
