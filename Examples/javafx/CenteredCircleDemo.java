package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CenteredCircleDemo extends Application
{
  @Override
  public void start(final Stage primaryStage)
  {
    Pane pane = new Pane();

    Circle circle = new Circle();
    circle.centerXProperty().bind(pane.widthProperty().divide(2));
    circle.centerYProperty().bind(pane.heightProperty().divide(2));
    circle.setRadius(50);

    pane.widthProperty().addListener((e) -> {
      if (pane.getWidth() > 300)
      {
        System.out.println("with is too big!");
      }
    });

      pane.heightProperty().addListener((e) -> {
      if (pane.getHeight() > 300)
      {
        System.out.println("height is too big!");
      }
    });

    circle.setStroke(Color.BLACK);
    circle.setFill(Color.WHITE);
    pane.getChildren().add(circle);

    Scene scene = new Scene(pane, 200, 200);
    primaryStage.setTitle("Show Circle");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(final String args[])
  {
    launch(args);
  }
}
