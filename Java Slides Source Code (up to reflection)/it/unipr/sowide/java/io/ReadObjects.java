package it.unipr.sowide.java.io;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.util.Calendar;

public final class ReadObjects
{
  public static final String DATAFILE =
      "./src/main/resources/io/invoiceobject.bin";

  private ReadObjects()
  {
  }

  public static void main(final String[] args)
      throws IOException, ClassNotFoundException
  {
    ObjectInputStream in = null;

    try
    {
      in = new ObjectInputStream(new BufferedInputStream(
               new FileInputStream(DATAFILE)));

      Calendar date = null;

      BigDecimal p;
      int u;
      String d;

      date = (Calendar) in.readObject();

      System.out.format("On %tA, %<tB %<te, %<tY:%n", date);

      try
      {
        while (true)
        {
          p = (BigDecimal) in.readObject();
          u = in.readInt();
          d = in.readUTF();

          System.out.format("You ordered %d units of %s at $%.2f%n", u, d, p);
        }
      }
      catch (EOFException e)
      {
      }
    }
    finally
    {
      in.close();
    }
  }
}
