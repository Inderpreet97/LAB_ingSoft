package it.unipr.sowide.java.concurrency;

public class NonVolatileDemo extends Thread
{
  private boolean keepRunning = true;

  public NonVolatileDemo()
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
    NonVolatileDemo demo = new NonVolatileDemo();

    demo.start();
    Thread.sleep(1000);

    demo.keepRunning = false;
  }
}
