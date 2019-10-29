package it.unipr.sowide.java.collections;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public final class SetDemo
{
  private SetDemo()
  {
  }

  private static <T> void printSet(final String n, final Set<T> l)
  {
    for (T e : l)
    {
      System.out.println(n + " element is " + e);
    }

    System.out.println();
  }

  public static void main(final String[] args)
  {
    Set<String> immutable = Set.of("John", "Steve", "Jack");

    printSet("immutable", immutable);

    Set<String> names = new HashSet<String>(immutable);

    names.add("Mary");
    names.remove("Steve");

    printSet("names", names);

    TreeSet<Integer> numbers = new TreeSet<>();

    numbers.add(10);
    numbers.add(13);
    numbers.add(5);

    printSet("numbers", numbers);

    class MyComparator implements Comparator<Integer>
    {
      @Override
      public int compare(final Integer x, final Integer y)
      {
        if (x == y)
        {
          return 0;
        }
        else if (x > y)
        {
          return -1;
        }

        return 1;
      }
    }

    numbers = new TreeSet<>(new MyComparator());

    numbers.add(10);
    numbers.add(13);
    numbers.add(5);

    printSet("numbers", numbers);

    numbers.addAll(numbers);

    printSet("numbers", numbers);
  }

}
