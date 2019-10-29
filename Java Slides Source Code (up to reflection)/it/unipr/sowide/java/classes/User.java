package it.unipr.sowide.java.classes;

import java.io.Serializable;

/**
 *
 * The class {@code User} provides an implementation of a
 * simplified model of an employee that satisfies JavaBean conventions.
 *
**/
public class User implements Serializable
{
  private static final long serialVersionUID = 1L;

  private int id;
  private String firstName;
  private String lastName;
  private String email;

  /**
   * Class constructor.
   *
  **/
  public User()
  {
    this.id        = 0;
    this.firstName = "";
    this.lastName  = "";
    this.email     = "";
  }

  /**
   * Class constructor.
   *
   * @param i  the user's id.
   * @param f  the user'sfirst name.
   * @param l  the user's last name.
   * @param e  the user's email address.
   *
  **/
  public User(final int i, final String f, final String l, final String e)
  {
    this.id        = i;
    this.firstName = f;
    this.lastName  = l;
    this.email     = e;
  }

  /**
   * Gets the user's identifier.
   *
   * @return the identifier.
   *
  **/
  public int getId()
  {
    return this.id;
  }

  /**
   * Sets the user's identifier.
   *
   * @param i  the identifier.
   *
  **/
  public void setId(final int i)
  {
    this.id = i;
  }

  /**
   * Gets the user's first name.
   *
   * @return the salary.
   *
  **/
  public String getFirstName()
  {
    return this.firstName;
  }

  /**
   * Sets the user's first name.
   *
   * @param f  the first name.
   *
  **/
  public void setFirstName(final String f)
  {
    this.firstName = f;
  }

  /**
   * Gets  the user's last name.
   *
   * @return the last name.
   *
  **/
  public String getLastName()
  {
    return this.lastName;
  }

  /**
   * Sets the user's last name.
   *
   * @param l the last name.
   *
  **/
  public void setLastName(final String l)
  {
    this.lastName = l;
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
}
