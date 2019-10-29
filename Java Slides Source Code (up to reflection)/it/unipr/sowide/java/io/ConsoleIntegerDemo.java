package it.unipr.sowide.java.io;

import java.io.Console;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class ConsoleIntegerDemo
{
  private ConsoleIntegerDemo()
  {
  }

  public static void main(final String[] args)
  {
    Console console = System.console();

    if (console != null)
    {
      int value = 0;

      try (Scanner scanner = new Scanner(console.reader()))
      {
        do
        {
          console.printf("Please input a value between 0 and 100: ");

          value = scanner.nextInt();

        }
        while ((value > 0) && (value < 100));
      }
      catch (InputMismatchException e)
      {
        System.out.println("An integer is expected!");

        return;
      }

      console.writer().println("Test regular writing!");
    }
  }
}
