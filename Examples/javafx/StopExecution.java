package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StopExecution extends Application
{
  TextArea textArea  = new TextArea();
  Label statusLabel  = new Label("Not Started...");
  Button startButton = new Button("Start");
  Button exitButton  = new Button("Exit");

  @Override
  public void start(final Stage stage)
  {
    startButton.setOnAction((e) -> runTask());
    exitButton.setOnAction((e) -> stage.close());

    HBox buttonBox = new HBox(5, startButton, exitButton);
    VBox root      = new VBox(10, statusLabel, buttonBox, textArea);

    root.setStyle("-fx-padding: 10;"
        + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;"
        + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;"
        + "-fx-border-color: blue;");

    Scene scene = new Scene(root, 400, 300);
    stage.setScene(scene);
    stage.setTitle("A simple Concurrency Example");
    stage.show();
  }

  public void runTask()
  {
    for (int i = 1; i <= 10; i++)
    {
      try
      {
        String status = "Processing " + i + " of " + 10;
        statusLabel.setText(status);
        textArea.appendText(status + "\n");
        Thread.sleep(1000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args)
  {
    Application.launch(args);
  }
}
