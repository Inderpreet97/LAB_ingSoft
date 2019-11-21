package it.unipr.sowide.java.javafx;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class VideoMediaDemo extends Application
{
  private static final String MEDIAFILE =
      "src/main/resources/javafx/SampleVideo1280x720-20mb.mp4";

  @Override
  public void start(final Stage stage)
  {
    final Media media = new Media(Paths.get(MEDIAFILE).toUri().toString());

    final MediaPlayer player = new MediaPlayer(media);

    player.setAutoPlay(true);

    player.setStartTime(Duration.minutes(1));
    player.setStopTime(Duration.minutes(2));

    final MediaView mediaView = new MediaView(player);
    mediaView.setFitWidth(800);
    mediaView.setFitHeight(600);
    mediaView.setSmooth(true);

    DropShadow dropshadow = new DropShadow();
    dropshadow.setOffsetY(5.0);
    dropshadow.setOffsetX(5.0);
    dropshadow.setColor(Color.WHITE);

    mediaView.setEffect(dropshadow);

    Button playButton = new Button("Play");
    Button stopButton = new Button("Stop");

    playButton.setOnAction((e) -> {
      if (player.getStatus() == Status.PLAYING)
      {
        player.stop();
        player.play();
      }
      else
      {
        player.play();
      }
    });

    stopButton.setOnAction((e) -> player.stop());

    HBox controlBox = new HBox(5, playButton, stopButton);

    VBox root = new VBox(5, mediaView, controlBox);

    root.setStyle("-fx-padding: 10;"
        + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;"
        + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;"
        + "-fx-border-color: blue;");

    Scene scene = new Scene(root, 840, 525);

    stage.setScene(scene);
    stage.setTitle("Video Media Demo");
    stage.show();
  }

  public static void main(final String[] args)
  {
    Application.launch(args);
  }
}
