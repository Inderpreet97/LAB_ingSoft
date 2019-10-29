package it.unipr.sowide.java.concurrency;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueueBuffer implements Buffer
{
  private ArrayBlockingQueue<String> elements;

  public BlockingQueueBuffer(final int l)
  {
    this.elements = new ArrayBlockingQueue<String>(l);
  }

  @Override
  public int size()
  {
    return this.elements.size();
  }

  @Override
  public String get()
  {
    try
    {
      return this.elements.take();
    }
    catch (InterruptedException e)
    {
      return null;
    }
  }

  @Override
  public void put(final String s)
  {
    try
    {
      this.elements.put(s);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
}
