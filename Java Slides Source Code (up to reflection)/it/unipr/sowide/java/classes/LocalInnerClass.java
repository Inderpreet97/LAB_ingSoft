package it.unipr.sowide.java.classes;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * The class {@code localInnerClass} presents an example of local inner class.
 *
**/

public class LocalInnerClass
{
  private int resolution;
  private Button button;

  /**
   * Class constructor.
   *
  **/
  public LocalInnerClass()
  {
    // resolution and button are set.

    addListener();
  }

  private void capture(final int r)
  {
    // some code
  }

  private void addListener()
  {
    class CaptureButtonListener implements ActionListener
    {
      @Override
      public void actionPerformed(final ActionEvent e)
      {
        capture(resolution); // use of the resolution value in the capture code
      }
    }

    button.addActionListener(new CaptureButtonListener());
  }
}
