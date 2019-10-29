package it.unipr.sowide.java.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public final class CopyCharacters
{
  private CopyCharacters()
  {
  }

  public static void main(final String[] args) throws IOException
  {
    try (FileReader fi = new FileReader("./src/main/resources/io/input.txt");
         FileWriter fo = new FileWriter("./src/main/resources/io/output.txt"))
    {
      int c;

      while ((c = fi.read()) != -1)
      {
        fo.write(c);
      }
    }
  }
}
