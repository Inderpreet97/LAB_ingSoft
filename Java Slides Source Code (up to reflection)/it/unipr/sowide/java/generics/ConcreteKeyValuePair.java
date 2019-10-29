package it.unipr.sowide.java.generics;

public class ConcreteKeyValuePair<K, V> implements KeyValuePair<K, V>
{
  private final K key;
  private final V value;

  public ConcreteKeyValuePair(final K k, final V v)
  {
    this.key   = k;
    this.value = v;
  }

  @Override
  public K getKey()
  {
    return this.key;
  }

  @Override
  public V getValue()
  {
    return this.value;
  }
}
