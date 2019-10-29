package it.unipr.sowide.java.concurrency;

import java.util.Random;

public final class ThreadWithRunnable
{
  private static final int MAXTHREADS = 10;
  private static final int MAXTIME = 1000;

  private static final Random RANDOM  = new Random();

  private ThreadWithRunnable()
  {
  }

  public static void main(final String[] args)
  {
    class MyRunnable implements Runnable
    {
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
    }

    int n = RANDOM.nextInt(MAXTHREADS);

    System.out.println(n + " threads will be started!");

    for (int i = 0; i < n; i++)
    {
      new Thread(new MyRunnable()).start();
    }
  }
}
