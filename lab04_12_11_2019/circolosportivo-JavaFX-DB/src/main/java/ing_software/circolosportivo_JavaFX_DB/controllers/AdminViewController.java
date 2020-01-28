package ing_software.circolosportivo_JavaFX_DB.controllers;

import ing_software.circolosportivo_JavaFX_DB.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

class AdminViewController {
	@FXML
	private Button logoutButton;
	@FXML
	private Label sessionLabel;

	public void initialize() {
	}

	public void initSessionID(final Scene scene, String sessionID) {
		sessionLabel.setText(sessionID);
		logoutButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MainApp.logout();
			}
		});
	}
}
