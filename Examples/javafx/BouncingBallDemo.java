package it.unipr.sowide.java.javafx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class BouncingBallDemo extends Application
{
  private double stepX = 7;
  private double stepY = 3;

  @Override
  public void start(final Stage stage)
  {
    Pane   canvas = new Pane();
    Scene  scene  = new Scene(canvas, 300, 300, Color.ALICEBLUE);
    Circle ball   = new Circle(10, Color.CADETBLUE);

    ball.relocate(5, 5);
    canvas.getChildren().add(ball);

    EventHandler<ActionEvent> handler = (e) -> {
      ball.setLayoutX(ball.getLayoutX() + stepX);
      ball.setLayoutY(ball.getLayoutY() + stepY);

      Bounds bounds = canvas.getBoundsInLocal();

      if (ball.getLayoutX() <= (bounds.getMinX() + ball.getRadius())
          || ball.getLayoutX() >= (bounds.getMaxX() - ball.getRadius()))
      {
        stepX = -stepX;
      }

      if ((ball.getLayoutY() >= (bounds.getMaxY() - ball.getRadius()))
          || (ball.getLayoutY() <= (bounds.getMinY() + ball.getRadius())))
      {
        stepY = -stepY;
      }
    };

    Timeline timeline = new Timeline(
        new KeyFrame(Duration.millis(20), handler));

    stage.setTitle("Animated Ball");
    stage.setScene(scene);
    stage.show();

    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();
  }

  public static void main(final String[] args)
  {
    launch();
  }
}
