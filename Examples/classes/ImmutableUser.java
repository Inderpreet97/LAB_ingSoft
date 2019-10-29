package it.unipr.sowide.java.classes;

import java.io.Serializable;

/**
 *
 * The class {@code ImmutableUser} provides
 * an immutable and simplified model of a user.
 *
**/

public final class ImmutableUser implements Serializable
{
  private static final long serialVersionUID = 1L;

  private final int id;
  private final String firstName;
  private final String lastName;
  private final String email;

  /**
   * Class constructor.
   *
   * @param i  the user's id.
   * @param f  the user'sfirst name.
   * @param l  the user's last name.
   * @param e  the user's email address.
   *
  **/
  public ImmutableUser(final int i,
      final String f, final String l, final String e)
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
   * Gets the user's first name.
   *
   * @return the first name.
   *
  **/
  public String getFirstName()
  {
    return this.firstName;
  }

  /**
   * Gets the user's last name.
   *
   * @return the last name.
   *
  **/
  public String getLastName()
  {
    return this.lastName;
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
}
