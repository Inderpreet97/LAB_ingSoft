package it.unipr.sowide.java.collections;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public final class QueueDemo
{
  private QueueDemo()
  {
  }

  private static void printQueue(final String n, final Queue<Task> l)
  {
    for (Task e : l)
    {
      System.out.println(n + " element is " + e.getRank());
    }

    System.out.println();
  }

  public static void main(final String[] args)
  {
    Queue<Task> tasks = new LinkedList<>();

    tasks.add(new Task("t1", 3));
    tasks.add(new Task("t2", 1));
    tasks.add(new Task("t2", 1));
    tasks.add(new Task("t3", 4));

    printQueue("LinkedList ", tasks);

    tasks = new PriorityQueue<>();

    tasks.add(new Task("t1", 3));
    tasks.add(new Task("t2", 1));
    tasks.add(new Task("t2", 1));
    tasks.add(new Task("t3", 4));

    printQueue("PriorityQueue ", tasks);

    class MyComparator implements Comparator<Task>
    {
      @Override
      public int compare(final Task x, final Task y)
      {
        if (x.getRank() == y.getRank())
        {
          return 0;
        }
        else if (x.getRank() > y.getRank())
        {
          return 1;
        }

        return -1;
      }
    }

    tasks = new PriorityQueue<Task>(new MyComparator());

    tasks.offer(new Task("t1", 3));
    tasks.offer(new Task("t2", 1));
    tasks.offer(new Task("t2", 1));
    tasks.offer(new Task("t3", 4));

    printQueue("PriorityQueue - comparator ", tasks);
  }
}
