package it.unipr.sowide.java.io;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class WriteData
{
  public static final String DATAFILE =
      "./src/main/resources/io/invoicedata.bin";

  private static final double[] PRICES = {
      19.99, 9.99, 15.99, 3.99, 4.99};

  private static final int[] UNITS = {
      12, 8, 13, 29, 50};

  private static final String[] DESCS = {
      "Java T-shirt", "Java Mug", "Duke Juggling Dolls",
      "Java Pin", "Java Key Chain"};

  private WriteData()
  {
  }

  public static void main(final String[] args) throws IOException
  {
    DataOutputStream out = null;

    try
    {
      out = new DataOutputStream(new BufferedOutputStream(
                new FileOutputStream(DATAFILE)));

      for (int i = 0; i < PRICES.length; i++)
      {
        out.writeDouble(PRICES[i]);
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
