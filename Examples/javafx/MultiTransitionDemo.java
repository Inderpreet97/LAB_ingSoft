package it.unipr.sowide.java.javafx;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MultiTransitionDemo extends Application
{
  @Override
  public void start(final Stage primaryStage)
  {
    Button btn   = new Button("Runner");
    Group  group = new Group(btn);
    Scene  scene = new Scene(group, 600, 600);

    Path path = new Path();

    MoveTo moveTo = new MoveTo(100, 150);

    CubicCurveTo cubicCurveTo = new CubicCurveTo(400, 40, 175, 250, 500, 150);

    path.getElements().add(moveTo);
    path.getElements().add(cubicCurveTo);

    PathTransition pathTransition = new PathTransition();

    pathTransition.setDuration(Duration.seconds(5));
    pathTransition.setNode(btn);
    pathTransition.setPath(path);

    pathTransition.setAutoReverse(false);

    RotateTransition rotateTransition =
        new RotateTransition(Duration.seconds(5), btn);

    rotateTransition.setNode(btn);
    rotateTransition.setByAngle(120f);

    FadeTransition fadeTransition =
        new FadeTransition(Duration.seconds(5), btn);

    fadeTransition.setFromValue(1.0);
    fadeTransition.setToValue(0.5);

    ParallelTransition parallelTransition = new ParallelTransition(
        pathTransition, rotateTransition, fadeTransition);
    SequentialTransition sequentialTransition =
        new SequentialTransition(pathTransition, parallelTransition);

    sequentialTransition.play();

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(final String[] args)
  {
    Application.launch(args);
  }
}
