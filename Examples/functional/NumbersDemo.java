package it.unipr.sowide.java.functional;

import java.util.Arrays;
import java.util.List;

public final class NumbersDemo
{
  private NumbersDemo()
  {
  }

  public static void main(final String[] args)
  {
    List<Integer> values = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    values.stream().filter(v -> ((v % 2) == 0))
        .forEach(v -> System.out.println(v));

    values.stream().filter(n -> (n < 5)).forEach(System.out::println);

    int sum = values.stream().reduce(0, Integer::sum);

    System.out.println("Sum is: " + sum);
  }
}
