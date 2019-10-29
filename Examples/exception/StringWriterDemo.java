package it.unipr.sowide.java.exception;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Random;

public final class StringWriterDemo
{
  private static final int MAX = 50;

  private static final String[] WORDS = {"cat", "dog", "bird", "mouse"};

  private StringWriterDemo()
  {
  }

  public static void main(final String[] args)
  {
    Random r = new Random();

    StringWriter w = null;

    try
    {
      w = new StringWriter(r.nextInt(MAX) - r.nextInt(MAX));

      String animal;

      do
      {
        animal = WORDS[r.nextInt(WORDS.length)];

        w.append(animal);
      }
      while (animal.equals("mouse"));
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("writer initial size cannot be negatitive");
    }
    finally
    {
      if (w != null)
      {
        try
        {
          w.close();
        }
        catch (IOException e)
        {
          System.out.println("Error in the closing operation");
        }
      }
    }
  }
}
