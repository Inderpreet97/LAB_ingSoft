package it.unipr.sowide.java.io;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public final class NioDemo
{
  private static final String NIOPATH = "./src/main/resources/io/";

  private static final String NIOFILE = "input.txt";

  private NioDemo()
  {
  }

  public static void main(final String[] args)
  {
    Path sPath = Paths.get(NIOPATH);

    boolean pathExists = Files.exists(
        sPath, new LinkOption[] {LinkOption.NOFOLLOW_LINKS});

    if (pathExists)
    {
      Path dPath = Paths.get(NIOPATH, "nio");

      try
      {
        Files.createDirectory(dPath);
      }
      catch (FileAlreadyExistsException e)
      {
        System.out.println("directory " + dPath + "already exists!");
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }

      try
      {
        Files.copy(Paths.get(sPath.toString(), NIOFILE),
                   Paths.get(dPath.toString(), NIOFILE),
                   StandardCopyOption.REPLACE_EXISTING);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
