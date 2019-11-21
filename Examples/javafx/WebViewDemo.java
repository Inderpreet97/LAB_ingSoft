package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class WebViewDemo extends Application
{
  @Override
  public void start(final Stage primaryStage)
  {
    primaryStage.setTitle("WebView Demo");

    WebView webView = new WebView();

    webView.getEngine().load("http://google.com");

    VBox vBox = new VBox(webView);

    Scene scene = new Scene(vBox, 960, 600);

    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(final String[] args)
  {
    launch(args);
  }
}
