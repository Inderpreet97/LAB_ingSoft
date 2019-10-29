package it.unipr.sowide.java.generics;

import java.util.ArrayList;
import java.util.List;

public final class KeyValuePairDemo
{
  private KeyValuePairDemo()
  {
  }

  @SafeVarargs
  public final <K, V> List<KeyValuePair<K, V>> toList(
      final KeyValuePair<K, V>... a)
  {
    List<KeyValuePair<K, V>> l = new ArrayList<>();

    for (KeyValuePair<K, V> p : a)
    {
      l.add(p);

      System.out.println("value is " + p.toString());
    }

    return l;
  }

  public static void main(final String[] args)
  {
    KeyValuePairDemo demo = new KeyValuePairDemo();

    List<KeyValuePair<String, Integer>> l1 = demo.toList(
        new ConcreteKeyValuePair<String, Integer>("firstkey", 1),
        new ConcreteKeyValuePair<String, Integer>("secondkey", 2));

    List<KeyValuePair<String, String>> l2 = demo.toList(
        new ConcreteKeyValuePair<String, String>("firstkey", "firstvalue"),
        new ConcreteKeyValuePair<String, String>("secondkey", "secondvalue"));

    for (KeyValuePair<String, Integer> e : l1)
    {
      System.out.println("key " + e.getKey() + " value " + e.getValue());
    }

    for (KeyValuePair<String, String> e : l2)
    {
      System.out.println("key " + e.getKey() + " value " + e.getValue());
    }
  }
}


