package it.unipr.sowide.java.generics;

public class StringStringPair
{
  private final String key;
  private final String value;

  public StringStringPair(final String k, final String v)
  {
    this.key   = k;
    this.value = v;
  }

  public String getKey()
  {
    return this.key;
  }

  public String getValue()
  {
    return this.value;
  }
}
