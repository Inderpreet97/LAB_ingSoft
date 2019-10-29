package it.unipr.sowide.java.concurrency;

import java.util.ArrayList;
import java.util.List;

public class SynchronizedMethodWaitSignalBuffer implements Buffer
{
  private List<String> elements;

  private final int length;

  public SynchronizedMethodWaitSignalBuffer(final int l)
  {
    this.elements = new ArrayList<>();

    this.length = l;
  }

  @Override
  public int size()
  {
    return this.elements.size();
  }

  @Override
  public synchronized String get()
  {
    while (this.elements.size() == 0)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }

    notifyAll();

    return this.elements.remove(0);
  }

  @Override
  public synchronized void put(final String s)
  {
    while (this.elements.size() == this.length)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }

    this.elements.add(s);
    notifyAll();
  }
}
