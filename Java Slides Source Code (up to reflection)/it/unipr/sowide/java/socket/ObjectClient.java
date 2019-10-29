package it.unipr.sowide.java.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

public class ObjectClient
{
  private static final int SPORT = 4444;
  private static final String SHOST = "localhost";
  private String[] greetings = {"hello", "hi", "ciao", "hey", "aloha",
      "shalom"};

  public void send()
  {
    try
    {
      Socket  client = new Socket(SHOST, SPORT);
      Random  r      = new Random();
      Message m      = new Message(new User(
          "Agostino", "Poggi", "agostino.poggi@unipr.it", "agostino"), "");

      ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
      ObjectInputStream  is = new ObjectInputStream(client.getInputStream());

      while (true)
      {
        // Sends messages until it receives an �end� message

        m.setContent(greetings[r.nextInt(greetings.length)]);

        System.out.format(" Client sends: %s to Server", m.getContent());

        os.writeObject(m);
        os.flush();

        Object o = is.readObject();

        if ((o != null) && (o instanceof Message))
        {
          Message n = (Message) o;

          System.out.format(" and received: %s from Server\n",
              n.getContent());

          if (n.getContent().equals("end"))
          {
            break;
          }
        }
      }

      client.close();
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  public static void main(final String[] v)
  {
    new ObjectClient().send();
  }
}
