package it.unipr.sowide.java.classes;

import java.io.Serializable;

/**
 *
 * The class {@code ImmutableMethods} provides
 * a simplified model of a user that offer immutable methods.
 *
**/

public class ImmutableMethods implements Serializable
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
  public ImmutableMethods(final int i,
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
  public final int getId()
  {
    return this.id;
  }

  /**
   * Gets the user's first name.
   *
   * @return the first name.
   *
  **/
  public final String getFirstName()
  {
    return this.firstName;
  }

  /**
   * Gets the user's last name.
   *
   * @return the last name.
   *
  **/
  public final String getLastName()
  {
    return this.lastName;
  }

  /**
   * Gets the user's email address.
   *
   * @return the address.
   *
  **/
  public final String getEmail()
  {
    return this.email;
  }
}
