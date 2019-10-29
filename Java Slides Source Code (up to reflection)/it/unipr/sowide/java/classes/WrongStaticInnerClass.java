package it.unipr.sowide.java.classes;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * The class {@code WrongStaticInnerClass}
 * presents an wrong example of static inner class.
 *
**/

public class WrongStaticInnerClass
{
  private int resolution;
  private int area;
  private Button button;

  /**
   * Class constructor.
   *
  **/
  public WrongStaticInnerClass()
  {
    // resolution and button are set.

    button.addActionListener(new CaptureButtonListener());

    System.out.println("image area " + this.area
        + " this.resolution " + resolution);
  }

  private static class CaptureButtonListener implements ActionListener
  {
    @Override
    public void actionPerformed(final ActionEvent e)
    {
      // int pixel = area / resolution;

      // System.out.println("image area " + area
      // + " resolution " + resolution + "pixel " + pixel);

      // Some other code.
    }
  }
}
