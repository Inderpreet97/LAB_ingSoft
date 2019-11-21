package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class ServiceDemo extends Application {

    MyService myService;

    @Override
    public void start(Stage primaryStage) {

        final ProgressBar progressBar = new ProgressBar();
        final Label labelCount = new Label();
        final Label labelState = new Label();
        final Label labelSucceeded = new Label();

        myService = new MyService();

        myService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                labelSucceeded.setText("OnSucceeded");
            }
        });

        myService.setOnRunning(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                labelSucceeded.setText("OnRunning");
            }
        });

        myService.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {
                labelSucceeded.setText("OnFailed");
            }
        });

        progressBar.progressProperty().bind(myService.progressProperty());
        labelCount.textProperty().bind(myService.messageProperty());

        Button btnStart = new Button("Start Service");
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                myService.start();
            }
        });

        Button btnReadTaskState = new Button("Read Service State");
        btnReadTaskState.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                labelState.setText(myService.getState().toString());
            }
        });

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(5, 5, 5, 5));
        vBox.setSpacing(5);
        vBox.getChildren().addAll(
                progressBar,
                labelCount,
                btnStart,
                btnReadTaskState,
                labelState,
                labelSucceeded);

        StackPane root = new StackPane();
        root.getChildren().add(vBox);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("java-buddy.blogspot.com");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private class MyService extends Service<Void> {

        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    int max = 50;
                    for (int i = 1; i <= max; i++) {
                        if (isCancelled()) {
                            break;
                        }

                        updateProgress(i, max);
                        updateMessage(String.valueOf(i));

                        Thread.sleep(100);
                    }
                    return null;
                }
            };
        }
    }
}

