package it.unipr.sowide.java.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public final class ListDemo
{
  private ListDemo()
  {
  }

  private static <T> void printList(final String n, final List<T> l)
  {
    for (T e : l)
    {
      System.out.println(n + " element is " + e);
    }

    System.out.println();
  }

  public static void main(final String[] args)
  {
    List<String> immutable = List.of("John", "Steve", "Jack");

    printList("immutable names", immutable);

    List<String> names = immutable.subList(0, 2);

    printList("immutable sublist of names", names);

    names = new ArrayList<String>(names);

    names.add("Mary");
    names.remove("Steve");
    names.add(names.get(0));

    printList("ArrayList names after add and remove", names);

    LinkedList<Integer> numbers = new LinkedList<>();

    numbers.add(10);
    numbers.addFirst(25);
    numbers.addLast(12);
    numbers.add(13);
    numbers.add(numbers.getLast());

    printList("LinkedList numbers", numbers);

    numbers.addAll(numbers);

    printList("LinkedList numbers after addAll", numbers);

    Stack<Integer> stack = new Stack<>();

    for (int n : stack)
    {
      stack.add(n);
    }

    printList("stack numbers", stack);

    stack.push(111);
    stack.add(stack.peek());

    printList("stack numbers after pust and add", stack);
  }

}
