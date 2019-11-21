package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PersonBookDemo extends Application
{
  @Override
  public void start(final Stage primaryStage) throws Exception
  {
    Parent root  = FXMLLoader.load(getClass().getResource("personBook.fxml"));
    Scene  scene = new Scene(root, 500, 500);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(final String[] args)
  {
    launch(args);
  }
}
