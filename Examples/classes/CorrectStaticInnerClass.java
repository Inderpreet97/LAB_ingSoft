package it.unipr.sowide.java.classes;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * The class {@code CorrectStaticInnerClass}
 * presents an example of static inner class.
 *
**/

public class CorrectStaticInnerClass
{
  private int resolution;
  private int area;
  private Button button;

  /**
   * Class constructor.
   *
  **/
  public CorrectStaticInnerClass()
  {
    // resolution and button are set.

    button.addActionListener(new CaptureButtonListener(this));
  }

  private int getResolution()
  {
    return this.resolution;
  }

  private int getArea()
  {
    return this.area;
  }

  private static final class CaptureButtonListener implements ActionListener
  {
    private CorrectStaticInnerClass container;

    private CaptureButtonListener(final CorrectStaticInnerClass c)
    {
      this.container = c;
    }

    @Override
    public void actionPerformed(final ActionEvent e)
    {
      int pixel = this.container.getArea() / this.container.getResolution();

      System.out.println("image area " + this.container.getArea()
          + " resolution " + this.container.getResolution() + "pixel " + pixel);

      // Some other code.
    }
  }
}
