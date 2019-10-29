package it.unipr.sowide.java.structures;

import java.util.Random;

import it.unipr.sowide.java.classes.Day;

/**
 *
 * The {@code BitDemo} class defines a demo
 * of the use of the bitwise operators.
 *
**/

public final class SwitchDemo
{
  private SwitchDemo()
  {
  }

  /**
   * Starts the demo.
   *
   * @param args  the method does not requires arguments.
   *
  **/
  public static void main(final String[] args)
  {
    final int max = 7;

    Random r = new Random();

    Day day = Day.values()[r.nextInt(max)];

    switch (day)
    {
      case MONDAY:
      case FRIDAY:
      case SUNDAY:
        System.out.println(day + " number of words is " + 6);
        break;
      case TUESDAY:
        System.out.println(day + " number of words is " + 7);
        break;
      case THURSDAY:
      case SATURDAY:
        System.out.println(day + " number of words is " + 8);
        break;
      default: // WEDNESDAY
        System.out.println(day + " number of words is " + 9);
        break;
    }

    day = Day.values()[r.nextInt(max)];

    int wordsNumber;

    switch (day)
    {
      case MONDAY:
      case FRIDAY:
      case SUNDAY:
        wordsNumber = 6;
        break;
      case TUESDAY:
        wordsNumber = 7;
        break;
      case THURSDAY:
      case SATURDAY:
        wordsNumber = 8;
        break;
      default: // WEDNESDAY
        wordsNumber = 9;
        break;
    }

    System.out.println(day + " number of words is " + wordsNumber);
    /*
    day = Day.values()[r.nextInt(max)];

    switch (day)
    {
      case MONDAY, FRIDAY, SUNDAY -> System.out
          .println(day + " number of words is " + 6);
      case TUESDAY -> System.out.println(day + " number of words is " + 7);
      case THURSDAY, SATURDAY -> System.out
          .println(day + " number of words is " + 8);
      case WEDNESDAY -> System.out.println(day + " number of words is " + 9);
    }

    day = Day.values()[r.nextInt(max)];

    wordsNumber = switch (day)
    {
      case MONDAY, FRIDAY, SUNDAY -> 6;
      case TUESDAY -> 7;
      case THURSDAY, SATURDAY -> 8;
      case WEDNESDAY -> 9;
    };

    System.out.println(day + " number of words is " + wordsNumber); */
  }
}
