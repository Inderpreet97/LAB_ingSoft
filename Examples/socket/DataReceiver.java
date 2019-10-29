package it.unipr.sowide.java.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class DataReceiver
{
  private static final String ADDRESS = "230.0.0.1";
  private static final int DPORT = 4446;
  private static final int SIZE = 256;

  public void receive()
  {
    try
    {
      MulticastSocket socket = new MulticastSocket(DPORT);

      socket.joinGroup(InetAddress.getByName(ADDRESS));

      byte[] buf = new byte[SIZE];

      DatagramPacket packet = new DatagramPacket(buf, buf.length);

      socket.receive(packet);

      System.out.println("Receiver received: " + new String(packet.getData()));

      socket.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public static void main(final String[] v)
  {
    new DataReceiver().receive();
  }
}
