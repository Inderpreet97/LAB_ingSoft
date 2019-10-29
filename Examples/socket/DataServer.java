package it.unipr.sowide.java.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class DataServer
{
  private static final int SPORT = 4444;

  public void reply()
  {
    try
    {
      ServerSocket server = new ServerSocket(SPORT);

      Socket client = server.accept();

      BufferedReader   is = new BufferedReader(
          new InputStreamReader(client.getInputStream()));
      DataOutputStream os = new DataOutputStream(client.getOutputStream());

      System.out.println("Server received: " + is.readLine());

      os.writeBytes("Done\n");

      client.close();
      server.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public static void main(final String[] v)
  {
    new DataServer().reply();
  }
}
