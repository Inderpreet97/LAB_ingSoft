package it.unipr.sowide.java.generics;

public class StringIntegerPair
{
  private final String key;
  private final int    value;

  public StringIntegerPair(final String k, final int v)
  {
    this.key   = k;
    this.value = v;
  }

  public String getKey()
  {
    return this.key;
  }

  public int getValue()
  {
    return this.value;
  }
}
