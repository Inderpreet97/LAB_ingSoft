package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Shear;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class ShearingDemo extends Application
{
  @Override
  public void start(final Stage stage)
  {
    Rectangle rectangle1 = new Rectangle(200, 100, Color.LIGHTGRAY);
    rectangle1.setStroke(Color.BLACK);
    Rectangle rectangle2 = new Rectangle(200, 100, Color.LIGHTGREEN);
    rectangle2.setStroke(Color.BLACK);
    Rectangle rectangle3 = new Rectangle(200, 100, Color.LIGHTGREEN);
    rectangle3.setStroke(Color.BLACK);

    Translate translate = new Translate(100, 100);

    rectangle1.getTransforms().addAll(translate);

    rectangle2.getTransforms().addAll(translate);
    rectangle2.setOpacity(0.5);
    rectangle2.getTransforms().addAll(new Shear(0.5, 0.5));

    rectangle3.getTransforms().addAll(translate);
    rectangle3.setOpacity(0.25);
    rectangle3.getTransforms().addAll(new Shear(-0.25, -0.25));

    Pane root = new Pane(rectangle1, rectangle2, rectangle3);

    root.setPrefSize(400, 400);
    root.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
      + "-fx-border-width: 2;" + "-fx-border-insets: 5;"
      + "-fx-border-radius: 5;" + "-fx-border-color: blue;");

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle("Shear Transformation Demo");
    stage.show();
  }

  public static void main(final String[] args)
  {
    Application.launch(args);
  }
}
