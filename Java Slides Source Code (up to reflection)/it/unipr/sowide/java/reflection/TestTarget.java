package it.unipr.sowide.java.reflection;

import java.io.Serializable;

public class TestTarget implements Serializable, Runnable
{
  private static final long serialVersionUID = 1L;

  private String message;

  public TestTarget()
  {
    this.message = "default message";
  }

  public TestTarget(final String m)
  {
    this.message = m;
  }

  @Override
  public void run()
  {
    System.out.println("run the App!");
  }

  public void print()
  {
    System.out.println(this.message);
  }

  public void printClassName()
  {
    System.out.println(this.getClass().getName());
  }
}
