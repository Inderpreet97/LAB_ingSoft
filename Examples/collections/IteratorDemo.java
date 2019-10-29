package it.unipr.sowide.java.collections;

import java.util.ArrayList;
import java.util.List;

public final class IteratorDemo
{
  private IteratorDemo()
  {
  }

  public static void main(final String[] args)
  {
    List<String> immutable = List.of("John", "Steve", "Jack");

    for (String s : immutable)
    {
      System.out.println("list name is " + s);
    }

    var names = new ArrayList<String>(immutable);

    var it = names.iterator();

    while (it.hasNext())
    {
      String s = it.next();

      if (s.equals("Steve"))
      {
        it.remove();
      }
    }

    String[] array = names.toArray(new String[names.size()]);

    for (String s : array)
    {
      System.out.println("array name is " + s);
    }
  }
}
