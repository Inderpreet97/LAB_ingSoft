package it.unipr.sowide.java.classes;

public class Hound extends Dog
{
  /**
   * Simulates the sniffing of the dog.
   *
  **/
  public void sniff()
  {
    System.out.println("sniff");
  }

  /** {@inheritDoc} **/
  @Override
  public void bark()
  {
    System.out.println("bowl");
  }

  /**
   * Simulates a sequence of rough noises of the dog.
   *
   * @param n  the sequence length.
   *
  **/
  public void bark(final int n)
  {
    for (int i = 0; i < n; i++)
    {
      System.out.println("woof");
    }
  }
}
