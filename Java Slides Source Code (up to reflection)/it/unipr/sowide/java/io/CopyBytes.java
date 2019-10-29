package it.unipr.sowide.java.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class CopyBytes
{
  private CopyBytes()
  {
  }

  public static void main(final String[] args) throws IOException
  {
    FileInputStream  fi = null;
    FileOutputStream fo = null;

    try
    {
      fi = new FileInputStream("./src/main/resources/io/input.txt");
      fo = new FileOutputStream("./src/main/resources/io/output.txt");

      int c;

      while ((c = fi.read()) != -1)
      {
        fo.write(c);
      }
    }
    finally
    {
      if (fi != null)
      {
        fi.close();
      }
      if (fo != null)
      {
        fo.close();
      }
    }
  }
}
