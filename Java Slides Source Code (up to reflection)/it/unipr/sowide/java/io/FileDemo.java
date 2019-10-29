package it.unipr.sowide.java.io;

import java.io.File;

public final class FileDemo
{
  public static final String FILE =
      "./src/main/java/it/unipr/sowide/java/classes";

  private FileDemo()
  {
  }

  public static void main(final String[] args)
  {
    File file = new File(FILE);

    if (file.exists())
    {
      System.out.println("File name: " + file.getName());
      System.out.println("Absolute path: " + file.getAbsolutePath());
      System.out.println("Writeable: " + file.canWrite());
      System.out.println("Readable " + file.canRead());
      System.out.println("File size in bytes " + file.length());

      if (file.isDirectory())
      {
        for (File f : file.listFiles())
        {
          System.out.println("file name is " +  f.getName());
        }
      }
    }
    else
    {
      System.out.println("The file does not exist.");
    }
  }
}
