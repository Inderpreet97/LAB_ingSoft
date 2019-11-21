package it.unipr.sowide.java.javafx;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class AudioMediaDemo extends Application
{
  private static final String MEDIAFILE =
      "src/main/resources/javafx/MP3Sample-2MG.mp3";

  @Override
  public void start(final Stage stage)
  {
    Button playButton = new Button("Play");
    Button stopButton = new Button("Stop");

    final Slider cycleSlider = new Slider(1, 5, 1);
    cycleSlider.setMajorTickUnit(1);
    cycleSlider.setShowTickLabels(true);

    final Slider volumeSlider = new Slider(0.0, 1.0, 0.5);
    volumeSlider.setMajorTickUnit(0.1);
    volumeSlider.setShowTickLabels(true);

    final Slider rateSlider = new Slider(0, 8, 4);
    rateSlider.setMajorTickUnit(1);
    rateSlider.setShowTickLabels(true);

    final Slider balanceSlider = new Slider(-1.0, 1.0, 0.0);
    balanceSlider.setMajorTickUnit(0.2);
    balanceSlider.setShowTickLabels(true);

    final Slider panSlider = new Slider(-1.0, 1.0, 0.0);
    panSlider.setMajorTickUnit(0.2);
    panSlider.setShowTickLabels(true);

    final Slider prioritySlider = new Slider(0.0, 10.0, 0.0);
    prioritySlider.setMajorTickUnit(1);
    prioritySlider.setShowTickLabels(true);

    AudioClip audioClip = new AudioClip(
        Paths.get(MEDIAFILE).toUri().toString());

    playButton.setOnAction((e) -> audioClip.play());
    stopButton.setOnAction((e) -> audioClip.stop());

    audioClip.cycleCountProperty().bind(cycleSlider.valueProperty());
    audioClip.volumeProperty().bind(volumeSlider.valueProperty());
    audioClip.rateProperty().bind(rateSlider.valueProperty());
    audioClip.balanceProperty().bind(balanceSlider.valueProperty());
    audioClip.panProperty().bind(panSlider.valueProperty());
    audioClip.priorityProperty().bind(prioritySlider.valueProperty());

    GridPane sliderPane = new GridPane();

    sliderPane.setHgap(5);
    sliderPane.setVgap(10);

    sliderPane.addRow(0, new Label("CycleCount:"), cycleSlider);
    sliderPane.addRow(1, new Label("Volume:"), volumeSlider);
    sliderPane.addRow(2, new Label("Rate:"), rateSlider);
    sliderPane.addRow(3, new Label("Balance:"), balanceSlider);
    sliderPane.addRow(4, new Label("Pan:"), panSlider);
    sliderPane.addRow(5, new Label("Priority:"), prioritySlider);

    HBox buttonBox = new HBox(5, playButton, stopButton);

    VBox root = new VBox(5, sliderPane, buttonBox);

    root.setPrefWidth(300);
    root.setPrefHeight(350);
    root.setStyle("-fx-padding: 10;"
        +"-fx-border-style: solid inside;"
        + "-fx-border-width: 2;"
        + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;"
        + "-fx-border-color: blue;");

    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.setTitle("Audio Media Demo");
    stage.show();
  }

  public static void main(final String[] args)
  {
    Application.launch(args);
  }
}
