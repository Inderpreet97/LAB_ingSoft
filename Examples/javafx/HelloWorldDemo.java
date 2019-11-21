package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class HelloWorldDemo extends Application
{

  @Override
  public void start(final Stage primaryStage)
  {
    Button btn = new Button();

    btn.setText("Say 'Hello World'");
    btn.setOnAction(e -> System.out.println("Hello World!"));

    StackPane root = new StackPane();
    root.getChildren().add(btn);

    Scene scene = new Scene(root, 300, 250);

    primaryStage.setTitle("Hello World!");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(final String[] args)
  {
    launch(args);
  }
}
