package it.unipr.sowide.java.classes;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * The class {@code InnerClass} presents an example of inner class.
 *
**/

public class InnerClass
{
  private int resolution;
  private Button button;

  /**
   * Class constructor.
   *
  **/
  public InnerClass()
  {
    // resolution and button are set.

    addListener();
  }

  private void capture(final int r)
  {
    // some code
  }

  private class CaptureButtonListener implements ActionListener
  {
    @Override
    public void actionPerformed(final ActionEvent e)
    {
      capture(resolution); // use of the resolution value in the capture code
    }
  }

  private void addListener()
  {
    button.addActionListener(new CaptureButtonListener());
  }
}
