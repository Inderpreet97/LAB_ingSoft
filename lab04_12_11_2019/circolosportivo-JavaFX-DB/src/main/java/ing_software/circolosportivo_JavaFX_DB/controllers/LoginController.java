package ing_software.circolosportivo_JavaFX_DB.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {
	@FXML
	private Label actiontarget;

	@FXML
	protected void handleSubmitButtonAction(final ActionEvent event) {
		actiontarget.setText("Sign in button pressed");
	}
}
