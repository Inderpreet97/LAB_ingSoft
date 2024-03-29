package it.unipr.sowide.java.concurrency;

import java.util.Random;

public final class ThreadWithLambda
{
  private static final int MAXTHREADS = 10;
  private static final int MAXTIME = 1000;

  private static final Random RANDOM  = new Random();

  private ThreadWithLambda()
  {
  }

  public static void main(final String[] args)
  {
    int n = RANDOM.nextInt(MAXTHREADS);

    System.out.println(n + " threads will be started!");

    Runnable runnable = () -> {
      try
      {
        Thread.sleep(RANDOM.nextInt(MAXTIME));
      }
      catch (Exception e)
      {
        System.out.println("thread fails!");
      }

      System.out.println("thread ends!");
    };

    for (int i = 0; i < n; i++)
    {
      new Thread(runnable).start();
    }
  }
}
