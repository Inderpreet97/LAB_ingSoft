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
	
	/*  
	 
	 	XXX CODICE EXTRA PER APRIRE UNA FINESTRA POP-UP
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("ing_software/circolosportivo_JavaFX_DB/FXML/AddPersonDialog.fxml"));

		Parent parent = fxmlLoader.load();

		AddPersonDialogController dialogController = fxmlLoader.<AddPersonDialogController>getController();
		dialogController.setAppMainObservableList(iscrizioniList);

		Scene scene = new Scene(parent, 300, 200);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.showAndWait();

	 */

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
