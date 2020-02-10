package ing_software.circolosportivo_JavaFX_DB.controllers;

import java.io.IOException;

import ing_software.circolosportivo_JavaFX_DB.DatabaseMethods;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

/** Controls the login screen */
public class LoginController {
	@FXML
	private TextField user;
	@FXML
	private TextField password;
	@FXML
	private Button loginButton;
	@FXML
	private Label labelError;

	public void initialize() {
	}

	public void initManager(final Scene scene) {
		
		loginButton.setOnAction(e -> {
			
			// userType = null if login failed
			String userType = authorize();

			if (userType != null) {
				String resourceUrl = "";
				
				// Load the correct FXML based on user type
				
				try {
					if (userType == "socio") {
						resourceUrl = "ing_software/circolosportivo_JavaFX_DB/FXML/socioview.fxml";
						FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(resourceUrl));
						scene.setRoot((Parent) loader.load());
						SocioViewController controller = loader.<SocioViewController>getController();
						controller.initSession(scene, user.getText());
					} else if (userType == "admin") {
						resourceUrl = "ing_software/circolosportivo_JavaFX_DB/FXML/adminview.fxml";
						FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(resourceUrl));
						scene.setRoot((Parent) loader.load());
						AdminViewController controller = loader.<AdminViewController>getController();
						controller.initSession(scene, user.getText());
					}

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	/**
	 * Check authorization credentials.
	 * 
	 * @return If accepted, return the user type for the authorized user otherwise, return null.
	 */
	private String authorize() {
		if(user.getText().isEmpty() || password.getText().isEmpty()) {
			labelError.setText("Email or Password is missing!");
			return null;
		}
		
		if (!DatabaseMethods.checkEmailPassoword(user.getText(), password.getText())) {
			labelError.setText("Email or Password is wrong!");
			return null;
		}
		return DatabaseMethods.getPersonaType(user.getText());
	}

}
