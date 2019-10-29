package it.unipr.sowide.java.generics;

import java.util.ArrayList;
import java.util.List;

public final class SourceTranslationDemo
{
  private SourceTranslationDemo()
  {
  }

  public static void main(final String[] args)
  {
    List<String> sl = new ArrayList<>();

    sl.add("zero");
    sl.add("one");
    sl.add("two");

    String min = "zzz";

    for (int i = 0; i < sl.size(); i++)
    {
      if (min.compareTo(sl.get(i)) > 0)
      {
        min = sl.get(i);
      }
    }

    System.out.println("min is " + min);

    /*
    List<Object> ol = new ArrayList<>();

    ol.add("zero");
    ol.add("one");
    ol.add("two");

    min = "zzz";

    for (int i = 0; i < ol.size(); i++)
    {
      if (min.compareTo(ol.get(i)) > 0)
      {
        min = ol.get(i);
      }
    }

    System.out.println("min is " + min);
    */

    List<Object> ol = new ArrayList<>();

    ol.add("zero");
    ol.add("one");
    ol.add("two");

    min = "zzz";

    for (int i = 0; i < ol.size(); i++)
    {
      if (min.compareTo((String) ol.get(i)) > 0)
      {
        min = (String) ol.get(i);
      }
    }

    System.out.println("min is " + min);
  }
}
