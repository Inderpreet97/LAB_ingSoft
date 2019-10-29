package it.unipr.sowide.java.exception;

public class NonPositiveDoubleException extends Exception
{
  private static final long serialVersionUID = 1L;

  public NonPositiveDoubleException()
  {
  }

  @Override
  public String getMessage()
  {
    return "A positive double number was expected!";
  }
}
