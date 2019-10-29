package it.unipr.sowide.java.collections;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public final class MapDemo
{
  private MapDemo()
  {
  }

  private static <K, V> void printMap(final String n, final Map<K, V> l)
  {
    for (Entry<K, V> e : l.entrySet())
    {
      System.out
          .println(n + " element key " + e.getKey() + " value " + e.getValue());
    }

    System.out.println();
  }

  public static void main(final String[] args)
  {
    Map<String, Integer> immutable = Map.of("John", 1, "Steve", 2, "Jack", 3);

    printMap("immutable", immutable);

    Map<String, Integer> keyVals = new HashMap<>(immutable);

    keyVals.put("Mary", 4);
    keyVals.remove("Bob");

    printMap("KeyVals", keyVals);

    keyVals = new TreeMap<>();

    keyVals.put("John", 1);
    keyVals.put("Mary", 2);
    keyVals.put("bob", 3);

    printMap("keyVals", keyVals);

    class MyComparator implements Comparator<String>
    {
      @Override
      public int compare(final String x, final String y)
      {
        if (x.equals(y))
        {
          return 0;
        }
        else if (x.compareTo(y) > 0)
        {
          return -1;
        }

        return 1;
      }
    }

    keyVals = new TreeMap<>(new MyComparator());

    keyVals.put("John", 1);
    keyVals.put("Mary", 2);
    keyVals.put("bob", 3);

    printMap("keyVals", keyVals);
  }

}
