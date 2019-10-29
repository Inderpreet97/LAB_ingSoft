package it.unipr.sowide.java.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class StandardIODemo
{
  private StandardIODemo()
  {
  }

  public static void main(final String[] args)
  {
    System.out.print("Enter your name: ");

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String userName = null;

    try
    {
      userName = br.readLine();
    }
    catch (IOException ioe)
    {
      System.out.println("IO error trying to read your name!");

      System.exit(1);
    }

    System.out.println("Thanks for the name, " + userName);
  }
}
