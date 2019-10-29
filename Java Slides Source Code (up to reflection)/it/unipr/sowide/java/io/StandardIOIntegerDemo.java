package it.unipr.sowide.java.io;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class StandardIOIntegerDemo
{
  private StandardIOIntegerDemo()
  {
  }

  public static void main(final String[] args)
  {
    int value = 0;

    try (Scanner scanner = new Scanner(System.in))
    {
      do
      {
        System.out.print("Please input a value between 0 and 100: ");

        value = scanner.nextInt();
      }
      while ((value > 0) && (value < 100));

    }
    catch (InputMismatchException e)
    {
      System.out.println("An integer is expected!");

      return;
    }

    System.out.println("Test regular writing!");
  }
}
