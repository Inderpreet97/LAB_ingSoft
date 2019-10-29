package it.unipr.sowide.java.concurrency;

import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class BufferDemo
{
  private static final int COREPOOL = 5;
  private static final int MAXPOOL = 100;
  private static final long IDLETIME = 5000;
  private static final int PRODUCTS = 1000;
  private static final long SLEEPTIME = 10;
  private static final int BUFFERSIZE = 10;
  private static final int NODES = 10;

  private BufferDemo()
  {
  }

  public static void main(final String[] args)
  {
    System.out.println("Enter:");
    System.out.println(
        " w for using a buffer with wait and signal syncronization");
    System.out.println(
        " b for using a buffer with blocking queue syncronization");

    Scanner scanner = new Scanner(System.in);

    String s = scanner.next();

    scanner.close();

    Buffer b;

    if (s.equals("w"))
    {
      b = new SynchronizedMethodWaitSignalBuffer(BUFFERSIZE);
    }
    else
    {
      b = new BlockingQueueBuffer(BUFFERSIZE);
    }

    ThreadPoolExecutor pool = new ThreadPoolExecutor(
        COREPOOL, MAXPOOL, IDLETIME, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>());

    for (int j = 0; j < NODES; j++)
    {
      pool.execute(new Producer(b, PRODUCTS));
      pool.execute(new Consumer(b, PRODUCTS));
    }

    while (pool.getActiveCount() > 0)
    {
      try
      {
        Thread.sleep(SLEEPTIME);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }

    pool.shutdown();
  }
}
