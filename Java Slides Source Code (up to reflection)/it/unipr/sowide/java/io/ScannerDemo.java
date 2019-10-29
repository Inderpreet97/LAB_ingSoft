package it.unipr.sowide.java.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public final class ScannerDemo
{
  private static final String INPUTFILE = "./src/main/resources/io/input.txt";

  private ScannerDemo()
  {
  }

  public static void main(final String[] args) throws IOException
  {
    Scanner s = null;

    try
    {
      s = new Scanner(new BufferedReader(new FileReader(INPUTFILE)));

      while (s.hasNext())
      {
        System.out.println(s.next());
      }
    }
    finally
    {
      if (s != null)
      {
        s.close();
      }
    }
  }
}
