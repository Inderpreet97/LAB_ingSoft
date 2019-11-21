package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Rotation3DDemo extends Application
{
  @Override
  public void start(final Stage primaryStage)
  {
    Box box = new Box();

    box.setWidth(150.0);
    box.setHeight(150.0);
    box.setDepth(150.0);

    box.setTranslateX(200);
    box.setTranslateY(200);
    box.setTranslateZ(10);

    Rotate rxBox = new Rotate(0, 0, 0, 0, Rotate.X_AXIS);
    Rotate ryBox = new Rotate(0, 0, 0, 0, Rotate.Y_AXIS);
    Rotate rzBox = new Rotate(0, 0, 0, 0, Rotate.Z_AXIS);
    rxBox.setAngle(15);
    ryBox.setAngle(15);
    rzBox.setAngle(15);

    box.getTransforms().addAll(rxBox, ryBox, rzBox);

    Group root  = new Group(box);
    Scene scene = new Scene(root, 400, 400);

    primaryStage.setTitle("Drawing a cube");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(final String[] args)
  {
    launch(args);
  }
}
