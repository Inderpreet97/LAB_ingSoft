package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class MultiTransformationDemo extends Application
{
  @Override
  public void start(final Stage stage)
  {
    Rectangle rectangle1 = new Rectangle(150, 100, Color.LIGHTGRAY);
    rectangle1.setStroke(Color.BLACK);
    Rectangle rectangle2 = new Rectangle(150, 100, Color.LIGHTBLUE);
    rectangle2.setStroke(Color.BLACK);
    Rectangle rectangle3 = new Rectangle(150, 100, Color.LIGHTCYAN);
    rectangle3.setStroke(Color.BLACK);

    Translate translate = new Translate(100, 40);

    rectangle1.getTransforms().addAll(translate);

    rectangle2.getTransforms().addAll(translate);
    rectangle2.setOpacity(0.5);

    rectangle3.getTransforms().addAll(translate);
    rectangle3.setOpacity(0.5);

    rectangle2.setTranslateX(100);
    rectangle2.setTranslateY(0);
    rectangle2.setRotate(30);
    rectangle2.setScaleX(1.2);
    rectangle2.setScaleY(1.2);

    rectangle3.getTransforms().addAll(new Scale(1.2, 1.2, 50, 25),
        new Rotate(50, 50, 25), new Translate(100, 0));

    Pane root = new Pane();
    root.setPrefSize(400, 350);
    root.getChildren().addAll(rectangle1, rectangle2, rectangle3);
    root.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;"
        + "-fx-border-insets: 5;" + "-fx-border-radius: 5;"
        + "-fx-border-color: blue;");

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle("A Multiple Transformations Example");
    stage.show();
  }

  public static void main(final String[] args)
  {
    Application.launch(args);
  }
}
