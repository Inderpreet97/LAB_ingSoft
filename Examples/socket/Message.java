package it.unipr.sowide.java.socket;

import java.io.Serializable;

public final class Message implements Serializable
{
  private static final long serialVersionUID = 1L;

  private User user;
  private String content;

  public Message(final User u, final String c)
  {
    this.user    = u;
    this.content = c;
  }

  public User getUser()
  {
    return this.user;
  }

  public String getContent()
  {
    return this.content;
  }

  public void setContent(final String c)
  {
    this.content = c;
  }
}
