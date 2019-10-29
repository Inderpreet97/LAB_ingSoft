package it.unipr.sowide.java.io;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;

public final class ConsoleDemo
{
  private ConsoleDemo()
  {
  }

  public static void main(final String[] args) throws IOException
  {
    Console c = System.console();

    if (c == null)
    {
      System.err.println("No console.");
      System.exit(1);
    }

    String login    = c.readLine("Enter your login: ");
    char[] password = c.readPassword("Enter your old password: ");

    if (verify(login, password))
    {
      boolean noMatch = true;

      do
      {
        char[] newPassword1 = c.readPassword("Enter your new password: ");
        char[] newPassword2 = c.readPassword("Enter new password again: ");

        noMatch = !Arrays.equals(newPassword1, newPassword2);

        if (noMatch)
        {
          c.format("Passwords don't match. Try again.%n");
        }
        else
        {
          change(login, newPassword1);
          c.format("Password for %s changed.%n", login);
          noMatch = false;
        }
      }
      while (noMatch);
    }
  }

  static boolean verify(final String login, final char[] password)
  {
    return true;
  }

  static void change(final String login, final char[] password)
  {
  }
}
