package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class HtmlEditorDemo extends Application
{
  @Override
  public void start(final Stage stage)
  {
    HTMLEditor htmlEditor = new HTMLEditor();
    htmlEditor.setPrefHeight(300);
    htmlEditor.setPrefWidth(600);

    Scene scene = new Scene(htmlEditor);
    stage.setScene(scene);
    stage.setTitle("HTMLEditor Demo");
    stage.show();
  }

  public static void main(final String[] args)
  {
    Application.launch(args);
  }
}
