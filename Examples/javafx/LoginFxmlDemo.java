package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginFxmlDemo extends Application
{
  private static final String FXMLFILE =
      "/it/unipr/sowide/java/javafx/loginFxml.fxml";

  @Override
  public void start(final Stage stage) throws Exception
  {
    Parent root = FXMLLoader.load(getClass().getResource(FXMLFILE));

    stage.setTitle("FXML Welcome");
    stage.setScene(new Scene(root, 300, 275));
    stage.show();
  }

  public static void main(final String[] args)
  {
    Application.launch(LoginFxmlDemo.class, args);
  }
}
