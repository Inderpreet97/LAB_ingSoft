package it.unipr.sowide.java.concurrency;

import java.util.Random;

public final class ThreadExtension extends Thread
{
  private static final int MAXTHREADS = 10;
  private static final int MAXTIME = 1000;

  private static final Random RANDOM  = new Random();

  private ThreadExtension()
  {
  }

  @Override
  public void run()
  {
    try
    {
      Thread.sleep(RANDOM.nextInt(MAXTIME));
    }
    catch (Exception e)
    {
      System.out.println("thread fails!");
    }

    System.out.println("thread ends!");
  }

  public static void main(final String[] args)
  {
    int n = RANDOM.nextInt(MAXTHREADS);

    System.out.println(n + " threads will be started!");

    for (int i = 0; i < n; i++)
    {
      new ThreadExtension().start();
    }
  }
}
