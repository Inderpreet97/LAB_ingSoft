package it.unipr.sowide.java.concurrency;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SynchronizedMethodRaceDemo extends Thread
{
  private static final int THREADS = 100;
  private static final int OPERATIONS = 1000;
  private static final int COREPOOL = 5;
  private static final int MAXPOOL = 100;
  private static final long IDLETIME = 5000;
  private static final long SLEEPTIME = 10;

  private static Shared shared = new Shared();

  protected static class Shared
  {
    protected int value;

    protected Shared()
    {
      this.value = 0;
    }

    public synchronized void increment()
    {
      this.value++;
    }
  }

  @Override
  public void run()
  {
    for (int i = 0; i < OPERATIONS; i++)
    {
      shared.increment();
    }
  }

  public static void main(final String[] args)
  {
    ThreadPoolExecutor pool = new ThreadPoolExecutor(
        COREPOOL, MAXPOOL, IDLETIME, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>());

    for (int j = 0; j < THREADS; j++)
    {
      pool.execute(new SynchronizedMethodRaceDemo());
    }

    while (pool.getActiveCount() > 0)
    {
      try
      {
        Thread.sleep(SLEEPTIME);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }

    System.out.println("\n shared value is: " + shared.value);
  }
}
