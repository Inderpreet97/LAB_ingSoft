package it.unipr.sowide.java.generics;

public final class StringIntegerConcretePair
    implements KeyValuePair<String, Integer>
{
  private final String  key;
  private final Integer value;

  public StringIntegerConcretePair(final String k, final Integer v)
  {
    this.key   = k;
    this.value = v;
  }

  @Override
  public String getKey()
  {
    return this.key;
  }

  @Override
  public Integer getValue()
  {
    return this.value;
  }
}
