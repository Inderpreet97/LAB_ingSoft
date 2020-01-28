package ing_software.circolosportivo_JavaFX_DB.controllers;

import ing_software.circolosportivo_JavaFX_DB.MainApp;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;

/** Controls the main application screen */
public class SocioViewController {
  @FXML private Button logoutButton;
  @FXML private Label  sessionLabel;
  
  public void initialize() {}
  
  public void initSessionID(final Scene scene, String userType) {
    sessionLabel.setText(userType);
    logoutButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        MainApp.logout();
      }
    });
  }
}