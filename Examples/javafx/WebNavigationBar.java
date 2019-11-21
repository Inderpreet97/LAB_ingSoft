package it.unipr.sowide.java.javafx;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class WebNavigationBar extends HBox
{
  private FileChooser fileChooser = new FileChooser();

  public WebNavigationBar(final WebView view,
      final String homePage, final boolean goToHome)
  {
    this.setSpacing(4);
    this.setStyle("-fx-padding: 10;"
        + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;"
        + "-fx-border-insets: 5;"
        + "-fx-border-radius: 5;"
        + "-fx-border-color: blue;");

    Label label = new Label("History:");

    fileChooser.setTitle("Open Web Content");
    fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("HTML Files", "*.html", "*.htm"));

    WebEngine webEngine = view.getEngine();
    TextField pageUrl   = new TextField();

    Button refreshButton = new Button("Refresh");
    Button goButton      = new Button("Go");
    Button homeButton    = new Button("Home");
    Button openButton    = new Button("Open");

    HBox.setHgrow(pageUrl, Priority.ALWAYS);

    pageUrl.setOnAction((e) -> webEngine.load(pageUrl.getText()));

    webEngine.locationProperty().addListener((s, o, n) -> pageUrl.setText(n));

    refreshButton.setOnAction((e) -> webEngine.reload());

    goButton.setOnAction((e) -> webEngine.load(pageUrl.getText()));

    homeButton.setOnAction((e) -> webEngine.load(homePage));

    openButton.setOnAction((e) -> {
      File selectedFile = fileChooser.showOpenDialog(
          view.getScene().getWindow());

      if (selectedFile != null)
      {
        try
        {
          webEngine.load(selectedFile.toURI().toURL().toExternalForm());
        }
        catch (MalformedURLException ex)
        {
          ex.printStackTrace();
        }
      }
    });

    this.getChildren().addAll(label, pageUrl, goButton, refreshButton,
        homeButton, openButton);

    if (goToHome)
    {
      webEngine.load(homePage);
    }
  }
}

