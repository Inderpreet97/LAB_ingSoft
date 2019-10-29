package it.unipr.sowide.java.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public final class ReadData
{
  public static final String DATAFILE =
      "./src/main/resources/io/invoicedata.bin";

  private ReadData()
  {
  }

  public static void main(final String[] args)
  {
    double total = 0.0;

    try (DataInputStream in = new DataInputStream(new BufferedInputStream(
        new FileInputStream(DATAFILE))))
    {
      double price;
      int    unit;
      String desc;

      while (true)
      {
        price = in.readDouble();
        unit  = in.readInt();
        desc  = in.readUTF();

        System.out.format("You ordered %d units of %s at $%.2f%n",
            unit, desc, price);

        total += unit * price;
      }
    }
    catch (EOFException e)
    {
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    System.out.format("For a TOTAL of: $%.2f%n", total);
  }
}
