package it.unipr.sowide.java.generics;

import java.math.BigInteger;
import java.util.List;

public class BiggerInteger extends BigInteger
{
  private static final long serialVersionUID = 1L;

  public BiggerInteger(final String v)
  {
    super(v);
  }

  /*
  public static <T extends Comparable<T>> T max(final List<T> c)
  {
    T v = null;
    // ... method code ...
    return v;
  }
  */

  public static <T> T max(final List<? extends Comparable<? super T>> c)
  {
    T v = null;

    // ... method code ...

    return v;
  }
}
