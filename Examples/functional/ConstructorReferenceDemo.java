package it.unipr.sowide.java.functional;

import java.util.Random;

public class ConstructorReferenceDemo
{
  private int num;

  public ConstructorReferenceDemo(final int n)
  {
    this.num = n;
  }

  public ConstructorReferenceDemo(final ConstructorReferenceDemo i)
  {
    this.num = i.getNum();
  }

  public int getNum()
  {
    return this.num;
  }

  public static void main(final String[] args)
  {
    final int max = 50;

    Random r = new Random();

    int num = r.nextInt(max);

    IntSupplier s1 = ConstructorReferenceDemo::new;

    ConstructorReferenceDemo newObj1 = s1.apply(num);

    System.out.println("new object has a instance value " + newObj1.num);

    ObjectSupplier s2 = ConstructorReferenceDemo::new;

    ConstructorReferenceDemo newObj = s2.apply(newObj1);

    System.out.println("new object has a instance value " + newObj.num);
  }
}
