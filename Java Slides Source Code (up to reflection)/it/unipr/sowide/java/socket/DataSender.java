package it.unipr.sowide.java.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DataSender
{
  private static final int SPORT = 4444;
  private static final String ADDRESS = "230.0.0.1";
  private static final int DPORT = 4446;

  public void send()
  {
    try
    {
      DatagramSocket socket = new DatagramSocket(SPORT);
      InetAddress    group  = InetAddress.getByName(ADDRESS);

      String s = "Hello\n";
      byte[] b = s.getBytes();

      DatagramPacket packet = new DatagramPacket(b, b.length, group, DPORT);

      socket.send(packet);

      socket.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public static void main(final String[] v)
  {
    new DataSender().send();
  }
}
