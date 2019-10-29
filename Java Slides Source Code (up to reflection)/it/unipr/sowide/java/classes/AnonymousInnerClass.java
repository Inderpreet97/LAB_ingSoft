package it.unipr.sowide.java.classes;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * The class {@code AnonymousInnerClass}
 * presents an example of anonymous inner class.
 *
**/

public class AnonymousInnerClass
{
  private int resolution;
  private Button button;

  /**
   * Class constructor.
   *
  **/
  public AnonymousInnerClass()
  {
    // resolution and button are set.

    addListener();
  }

  private void capture(final int r)
  {
    // some code
  }

  private ActionListener a = new ActionListener()
  {
    @Override
    public void actionPerformed(final ActionEvent e)
    {
      capture(resolution); // use of the resolution value in the capture code
    }
  };

  private void addListener()
  {
    button.addActionListener(a);
  }
}

