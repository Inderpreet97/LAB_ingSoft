package it.unipr.sowide.java.concurrency;

public class VolatileDemo extends Thread
{
  private volatile boolean keepRunning = true;

  public VolatileDemo()
  {
    this.keepRunning = true;
  }

  @Override
  public void run()
  {
    System.out.print("\n Thread terminated in ");

    long t = System.currentTimeMillis();

    while (keepRunning)
    {
    }

    System.out.println((System.currentTimeMillis() - t) + " milliseconds");
  }

  public static void main(final String[] args) throws InterruptedException
  {
    VolatileDemo demo = new VolatileDemo();

    demo.start();
    Thread.sleep(1000);

    demo.keepRunning = false;
  }
}
