package it.unipr.sowide.java.structures;

/**
 *
 * The {@code ArrayDemo} class defines a demo of the use of arrays.
 *
**/

public final class ArrayDemo
{
  private ArrayDemo()
  {
  }

  /**
   * Starts the demo.
   *
   * @param args  the method does not requires arguments.
   *
  **/
  public static void main(final String[] args)
  {
    final int size = 4;

    final int s1 = 100;
    final int s2 = 200;
    final int s3 = 300;
    final int s4 = 400;

    int[] array = new int[size];

    int i = 0;

    array[i++] = s1;
    array[i++] = s2;
    array[i++] = s3;
    array[i] = s4;

    i = 0;

    System.out.println("Element at index 0: " + array[i++]);
    System.out.println("Element at index 1: " + array[i++]);
    System.out.println("Element at index 2: " + array[i++]);
    System.out.println("Element at index 3: " + array[i++]);

    System.out.println();

    array = new int[] {s1, s2, s3, s4};

    for (int j = 0; i < array.length; j++)
    {
      System.out.println("Element at index " + j + ": " + array[j]);
    }

    System.out.println();

    int[][] matrix = new int[2][2];

    System.arraycopy(array, 0, matrix[0], 0, 2);
    System.arraycopy(array, 2, matrix[1], 0, 2);

    System.out.println("Element at index 0, 0: " + matrix[0][0]);
    System.out.println("Element at index 0, 1: " + matrix[0][1]);
    System.out.println("Element at index 1, 0: " + matrix[1][0]);
    System.out.println("Element at index 1, 1: " + matrix[1][1]);

    System.out.println();

    for (int j = 0; j < matrix.length; j++)
    {
      for (int k = 0; k < matrix[j].length; k++)
      {
        System.out
            .println("Element at index " + j + ", " + k + ": " + matrix[j][k]);
      }
    }

    System.out.println();

    matrix = new int[][] {{s1, s2, s3, s4}, {s1, s2, s3, s4}};

    int j = 0;

    for (int[] a : matrix)
    {
      int k = 0;

      for (int v : a)
      {
        System.out.println("Element at index " + k++ + ", " + j + ": " + v);
      }

      System.out.println();

      j++;
    }
  }
}
