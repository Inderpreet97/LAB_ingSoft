package it.unipr.sowide.java.generics;

import java.util.List;

public final class ShapeDemo
{
  private ShapeDemo()
  {
  }

  public static void drawList(final List<Shape> l)
  {
    for (Shape s : l)
    {
      s.draw();
    }
  }

  public static void drawAllList(final List<?> l)
  {
    for (Object t : l)
    {
      System.out.println("shape is " + t.toString());
    }
  }

  public static void drawSubClassList(final List<? extends Shape> l)
  {
    for (Shape t : l)
    {
      t.draw();
    }
  }

  /*
  public static void drawSuperClassList(final List<? super Shape> l)
  {
    for (Shape t : l)
    {
      t.draw();
    }
  }
  */

  public static void main(final String[] args)
  {
    List<Shape> l = List.of(new Shape(), new Shape());

    drawList(l);
    drawAllList(l);
    drawSubClassList(l);
  }
}
