package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskDemo extends Application
{
  @Override
  public void start(final Stage primaryStage)
  {
    final Task<Void> task = new Task<>()
    {
      @Override
      protected Void call() throws Exception
      {
        final int max = 50;

        for (int i = 1; i <= max; i++)
        {
          if (isCancelled())
          {
            break;
          }

          updateProgress(i, max);
          updateMessage(String.valueOf(i));

          Thread.sleep(100);
        }
        return null;
      }
    };

    ProgressBar progressBar = new ProgressBar();
    progressBar.setProgress(0);
    progressBar.progressProperty().bind(task.progressProperty());

    Label labelCount = new Label();
    labelCount.textProperty().bind(task.messageProperty());

    final Label labelState = new Label();

    Button btnStart        = new Button("Start Task");
    Button btnReadTaskState = new Button("Read Task State");

    btnStart.setOnAction((e) -> new Thread(task).start());

    btnReadTaskState.setOnAction((e) -> labelState.setText(
        task.getState().toString()));

    VBox vBox = new VBox();

    vBox.setPadding(new Insets(5, 5, 5, 5));
    vBox.setSpacing(5);
    vBox.getChildren().addAll(
        progressBar, labelCount, btnStart,
        btnReadTaskState, labelState);

    StackPane root = new StackPane();
    root.getChildren().add(vBox);

    Scene scene = new Scene(root, 300, 250);

    primaryStage.setTitle("java-buddy.blogspot.com");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(final String[] args)
  {
    launch(args);
  }
}
