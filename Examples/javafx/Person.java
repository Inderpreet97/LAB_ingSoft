package it.unipr.sowide.java.javafx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person
{
  private SimpleIntegerProperty id;
  private SimpleStringProperty name;
  private SimpleIntegerProperty age;

  public Person(final int i, final String n, final int a)
  {
    this.id   = new SimpleIntegerProperty(i);
    this.name = new SimpleStringProperty(n);
    this.age  = new SimpleIntegerProperty(a);
  }

  public int getId()
  {
    return id.get();
  }

  public void setId(final int i)
  {
    this.id.set(i);
  }

  public String getName()
  {
    return name.get();
  }

  public void setName(final String n)
  {
    this.name.set(n);
  }

  public int getAge()
  {
    return age.get();
  }

  public void setAge(final int a)
  {
    this.age.set(a);
  }

  @Override
  public String toString()
  {
    return "id: " + id.get() + " - " + "name: "
           + name.get() + "age: " + age.get();
  }
}
