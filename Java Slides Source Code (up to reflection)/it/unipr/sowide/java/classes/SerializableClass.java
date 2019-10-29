package it.unipr.sowide.java.classes;

import java.io.Serializable;

/**
 *
 * The class {@code SerializableClass } provides an implementation of the access
 * information of a user.
 *
**/
public class SerializableClass implements Serializable
{
  private static final long serialVersionUID = 1L;

  private String login;
  private transient String password;
  private String email;
  private int accesses;

  /**
   * Class constructor.
   *
   * @param l  the user's login name.
   * @param p  the user's password.
   * @param e  the user's email address.
   *
  **/
  public SerializableClass(final String l, final String p, final String e)
  {
    this.login = l;
    this.password  = p;
    this.email     = e;

    this.accesses = 0;
  }

  /**
   * Gets the user's login name.
   *
   * @return the login name.
   *
  **/
  public String getLogin()
  {
    return this.login;
  }

  /**
   * Sets the user's first name.
   *
   * @param l  the first name.
   *
  **/
  public void setLogin(final String l)
  {
    this.login = l;
  }

  /**
   * Sets the user's password.
   *
   * @param p the password.
   *
  **/
  public void setPassword(final String p)
  {
    this.password = p;
  }

  /**
   * Gets the user's email address.
   *
   * @return the address.
   *
  **/
  public String getEmail()
  {
    return this.email;
  }

  /**
   * Sets the user's email address.
   *
   * @param e  the address.
   *
  **/
  public void setEmail(final String e)
  {
    this.email = e;
  }

  /**
   * Gets the number of user's system accesses.
   *
   * @return the number.
   *
  **/
  public int getAccesses()
  {
    return this.accesses;
  }

  /**
   * Performs user's login.
   *
   * @param l  the login name.
   * @param p  the login password.
   *
   * @return {@code true} if the operation is successful.
   *
  **/
  public boolean enter(final String l, final String p)
  {
    if (this.login.contentEquals(l) && this.password.equals(p))
    {
      return true;
    }

    return false;
  }
}
