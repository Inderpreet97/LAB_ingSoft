package it.unipr.sowide.java.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class LoginFxmlController
{
  @FXML
  private Text actiontarget;

  @FXML
  protected void handleSubmitButtonAction(final ActionEvent event)
  {
    actiontarget.setText("Sign in button pressed");
  }
}
