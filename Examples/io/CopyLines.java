package it.unipr.sowide.java.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public final class CopyLines
{
  private static final String CONFIGURATION =
      "./src/main/resources/io/configuration.properties";

  private CopyLines()
  {
  }

  public static void main(final String[] args) throws IOException
  {
    BufferedReader inputStream  = null;
    PrintWriter    outputStream = null;

    try
    {
      File e = new File(CONFIGURATION);

      Properties p = new Properties();

      p.load(new FileInputStream(e));

      inputStream  = new BufferedReader(new FileReader(p.getProperty("input")));
      outputStream = new PrintWriter(new FileWriter(p.getProperty("output")));

      String l;

      while ((l = inputStream.readLine()) != null)
      {
        outputStream.println(l);
      }
    }
    finally
    {
      if (inputStream != null)
      {
        inputStream.close();
      }
      if (outputStream != null)
      {
        outputStream.close();
      }
    }
  }
}
