package it.unipr.sowide.java.collections;

public class Task implements Comparable<Task>
{
  private String name;
  private int    rank;

  public Task(final String n, final int r)
  {
    this.name = n;
    this.rank = r;
  }

  public String getName()
  {
    return this.name;
  }

  public int getRank()
  {
    return this.rank;
  }

  @Override
  public int compareTo(final Task t)
  {
    if (this.rank == t.getRank())
    {
      return 0;
    }
    else if (this.rank > t.getRank())
    {
      return -1;
    }

    return 1;
  }
}
