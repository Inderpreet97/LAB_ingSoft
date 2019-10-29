package it.unipr.sowide.java.classes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
*
* The annotation {@code TypePreamble} defines the main
* information about a document.
*
**/

@Target(ElementType.TYPE)
public @interface TypePreamble
{
  /**
   * Gets the author's first and last names.
   *
   * @return the first and last names.
   *
  **/
  String author();

  /**
   * Gets the document creation date.
   *
   * @return the creation date.
   *
  **/
  String date();

  /**
   * Gets the last revision version.
   *
   * @return the revision version.
   *
  **/
  int currentRevision() default 1;

  /**
   * Gets the last modification date.
   *
   * @return the modification date.
   *
  **/
  String lastModified() default "N/A";

  /**
   * Gets the first and last names of the last modifier of the document.
   *
   * @return the modifier first and last names.
   *
  **/
  String lastModifiedBy() default "N/A";

  /**
   * Gets the first and last names of the reviewers.
   *
   * @return the reviewers' first and last names.
   *
  **/
  String[] reviewers();
}
