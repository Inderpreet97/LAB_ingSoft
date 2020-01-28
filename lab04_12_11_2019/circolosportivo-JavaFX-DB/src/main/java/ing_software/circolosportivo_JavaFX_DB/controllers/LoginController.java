package ing_software.circolosportivo_JavaFX_DB.controllers;

import java.io.IOException;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

/** Controls the login screen */
public class LoginController {
  @FXML private TextField user;
  @FXML private TextField password;
  @FXML private Button loginButton;
  
  public void initialize() {}
  
  public void initManager(final Scene scene) {
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent event) {
        String sessionID = authorize();
        if (sessionID != null) {
        	try {
    			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader()
    					.getResource("ing_software/circolosportivo_JavaFX_DB/FXML/mainview.fxml"));
    			scene.setRoot((Parent) loader.load());
    			MainViewController controller = loader.<MainViewController>getController();
    			controller.initSessionID(scene, sessionID);
    		} catch (IOException ex) {
    			ex.printStackTrace();
    		}
        }
      }
    });
  }

  /**
   * Check authorization credentials.
   * 
   * If accepted, return a sessionID for the authorized session
   * otherwise, return null.
   */   
  private String authorize() {
    return 
      "open".equals(user.getText()) && "sesame".equals(password.getText()) 
            ? generateSessionID() 
            : null;
  }
  
  private static int sessionID = 0;

  private String generateSessionID() {
    sessionID++;
    return "xyzzy - session " + sessionID;
  }
}
