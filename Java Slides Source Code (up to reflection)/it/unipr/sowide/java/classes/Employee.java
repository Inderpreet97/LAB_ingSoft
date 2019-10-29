package it.unipr.sowide.java.classes;

import java.io.Serializable;

/**
 *
 * The abstract class {@code Employee} provides
 * a partial implementation of a simplified model of an employee.
 *
**/

public abstract class Employee implements Serializable
{
  private static final long serialVersionUID = 1L;

  private final int id;
  private final String firstName;
  private final String lastName;

  private String email;

  /**
   * Class constructor.
   *
   * @param i  the employee's id.
   * @param f  the employee's first name.
   * @param l  the employee's last name.
   * @param e  the employee's email address.
   *
  **/
  protected Employee(final int i, final String f,
      final String l, final String e)
  {
    this.id        = i;
    this.firstName = f;
    this.lastName  = l;
    this.email     = e;
  }

  /**
   * Computes the employee's salary.
   *
   * @return the salary.
   *
  **/
  public abstract double salary();

  /**
   * Gets the employee's first identifier.
   *
   * @return the identifier.
   *
  **/
  public int getId()
  {
    return this.id;
  }

  /**
   * Gets the employee's first name.
   *
   * @return the salary.
   *
  **/
  public String getFirstName()
  {
    return this.firstName;
  }

  /**
   * Gets  the employee's last name.
   *
   * @return the salary.
   *
  **/
  public String getLastName()
  {
    return this.lastName;
  }

  /**
   * Gets  the employee's email address.
   *
   * @return the address.
   *
  **/
  public String getEmail()
  {
    return this.email;
  }

  /**
   * Sets  the employee's email address.
   *
   * @param e  the email address.
   *
  **/
  public void setEmail(final String e)
  {
    this.email = e;
  }
}
