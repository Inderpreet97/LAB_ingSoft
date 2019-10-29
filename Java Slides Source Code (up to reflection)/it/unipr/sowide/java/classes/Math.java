package it.unipr.sowide.java.classes;

/**
*
* The class {@code Math} defines some mathematical methods.
*
**/

public interface Math
{
  /**
   * Computes the square of an integer number.
   *
   * @param v  the integer value.
   *
   * @return the square value.
   *
  **/
  default int square(int v)
  {
    return v * v;
  }

  /**
   * Identifies the minimum between two integer numbers.
   *
   * @param f  the first integer value.
   * @param s  the second integer value.
   *
   * @return the minimum integer value.
   *
  **/
  default int min(int f, int s)
  {
    return (f < s) ? f : s;
  }

  /**
   * Computes the logarithm of a double number.
   *
   * @param v  the double value.
   *
   * @return the logarithm value.
   *
  **/
  double log(double v);
}
