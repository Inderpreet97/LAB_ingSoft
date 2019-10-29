package it.unipr.sowide.java.io;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.Calendar;

public final class WriteObjects
{
  public static final String DATAFILE =
      "./src/main/resources/io/invoiceobject.bin";

  private static final BigDecimal[] PRICES = {
      new BigDecimal("19.99"), new BigDecimal("9.99"),
      new BigDecimal("15.99"), new BigDecimal("3.99"),
      new BigDecimal("4.99")};

  private static final int[] UNITS = {
      12, 8, 13, 29, 50};

  private static final String[] DESCS = {
      "Java T-shirt", "Java Mug", "Duke Juggling Dolls",
      "Java Pin", "Java Key Chain"};

  private WriteObjects()
  {
  }

  public static void main(final String[] args) throws IOException
  {
    ObjectOutputStream out = null;

    try
    {
      out = new ObjectOutputStream(new BufferedOutputStream(
                new FileOutputStream(DATAFILE)));

      out.writeObject(Calendar.getInstance());

      for (int i = 0; i < PRICES.length; i++)
      {
        out.writeObject(PRICES[i]);
        out.writeInt(UNITS[i]);
        out.writeUTF(DESCS[i]);
      }
    }
    finally
    {
      out.close();
    }
  }
}
