package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SliderDemo extends Application
{
  @Override
  public void start(final Stage stage)
  {
    Slider one = new Slider(-100, 100, 20);
    one.setShowTickMarks(true);
    one.setShowTickLabels(true);
    Slider scale = new Slider(-1, 1, 0.5);
    scale.setMajorTickUnit(0.2);
    scale.setMinorTickCount(1);
    scale.setShowTickMarks(true);
    scale.setShowTickLabels(true);
    Slider two = new Slider(-100, 100, 0);
    two.setShowTickMarks(true);
    two.setShowTickLabels(true);

    DoubleProperty oneValue   = one.valueProperty();
    DoubleProperty twoValue   = two.valueProperty();
    DoubleProperty scaleValue = scale.valueProperty();

    twoValue.set(oneValue.get() * scaleValue.get());

    oneValue.addListener((observable, oldValue, newValue) -> {
      twoValue.set(newValue.doubleValue() * scaleValue.get());
    });

    scaleValue.addListener((observable, oldValue, newValue) -> {
      twoValue.set(oneValue.get() * newValue.doubleValue());
    });

    twoValue.addListener((observable, oldValue, newValue) -> {
      double theScale = scaleValue.get();
      if (theScale != 0)
      {
        oneValue.set(newValue.doubleValue() / scaleValue.get());
      }
      else
      {
        twoValue.set(0);
      }
    });

    VBox box = new VBox(5, one, scale, two);
    box.setPrefWidth(500);
    box.setStyle("-fx-border-color: transparent; -fx-border-width: 20px;");
    stage.setScene(new Scene(box));
    stage.show();
  }

  public static void main(final String[] args)
  {
    launch(args);
  }
}
