package it.unipr.sowide.java.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public final class ScannerDoubleDemo
{
  public static final String FILE =
      "./src/main/resources/io/numbers.txt";

  private ScannerDoubleDemo()
  {
  }

  public static void main(final String[] args) throws IOException
  {
    double sum = 0;

    Scanner s = null;

    try
    {
      s = new Scanner(new BufferedReader(new FileReader(FILE)));

      s.useDelimiter("\\s+");
      s.useLocale(Locale.ITALY);

      while (s.hasNext())
      {
        if (s.hasNextDouble())
        {
          sum += s.nextDouble();
        }
      }
    }
    finally
    {
      s.close();
    }

    System.out.format("sum is %f", sum);
  }
}
