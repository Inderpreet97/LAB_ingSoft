package it.unipr.sowide.java.io;

import java.util.regex.Pattern;

public class CheckRegex
{
  public static void main(String[] args)
  {
    String str=  "[0-9]{11,11}_[0-9]{11,11}_.*\\.pdf";

    if (Pattern.compile(str).matcher("11111111111_22222222222_rrrr.pdf").matches()) {
           System.out.println("yes");
       } else {
           System.out.println("no");
       }

  }

}
