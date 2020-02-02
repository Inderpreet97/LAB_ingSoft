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

	public void initialize() {
	}

	public void initManager(final Scene scene) {

		loginButton.setOnAction(e -> {
			String userType = authorize();

			if (userType != null) {

				String resourceUrl = "";
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
	 * If accepted, return a sessionID for the authorized session otherwise, return
	 * null.
	 */
	private String authorize() {
		if (!DatabaseMethods.checkEmailPassoword(user.getText(), password.getText())) {
			return null;
		}
		return DatabaseMethods.getPersonaType(user.getText());
	}

}
