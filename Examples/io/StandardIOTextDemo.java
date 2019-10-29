package it.unipr.sowide.java.io;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class StandardIOTextDemo
{
  private static final String END = "end";

  private StandardIOTextDemo()
  {
  }

  public static void main(final String[] args)
  {
    try (Scanner scanner = new Scanner(System.in))
    {
      String value;

      do
      {
        System.out.print("Please input a string (" + END + " to finish): ");

        value = scanner.next();
      }
      while (!value.equals(END));

    }
    catch (InputMismatchException e)
    {
      e.printStackTrace();

      return;
    }

    System.out.println("Test regular writing!");
  }
}
