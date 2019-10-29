package it.unipr.sowide.java.exception;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Random;

public final class GroupedAutoclosableDemo
{
  private static final int MAX = 50;

  private static final String[] WORDS = {"cat", "dog", "bird", "mouse"};

  private GroupedAutoclosableDemo()
  {
  }

  public static void main(final String[] args)
  {
    Random r = new Random();

    try (StringWriter w = new StringWriter(r.nextInt(MAX) - r.nextInt(MAX)))
    {
      String animal;

      do
      {
        animal = WORDS[r.nextInt(WORDS.length)];

        w.append(animal);
      }
      while (animal.equals("mouse"));
    }
    catch (IllegalArgumentException | IOException i)
    {
      System.out.println("writer initial size cannot be negatitive "
          + "or error in the closing operation");
    }
  }
}
