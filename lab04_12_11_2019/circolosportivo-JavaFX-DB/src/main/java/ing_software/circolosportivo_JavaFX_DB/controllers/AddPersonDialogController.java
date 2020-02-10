package ing_software.circolosportivo_JavaFX_DB.controllers;

import ing_software.circolosportivo_JavaFX_DB.DatabaseMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AddPersonDialogController {
	@FXML
	private TextField textFieldNome;
	@FXML
	private TextField textFieldCognome;
	@FXML
	private TextField textFieldEmail;
	@FXML
	private TextField textFieldPassword;
	
	@FXML
	private Label labelError;
	
	@FXML 
	private ToggleButton toggleBtnSocio;
	@FXML 
	private ToggleButton toggleBtnAdmin;

	private ToggleGroup toggleUserType;
	
	private GestioneUtentiDialogController parentController;
	
	public void initialize() {
		toggleBtnSocio.setUserData(1);
		toggleBtnAdmin.setUserData(2);
		
		toggleUserType = new ToggleGroup();
		
		toggleBtnSocio.setToggleGroup(toggleUserType);
		toggleBtnAdmin.setToggleGroup(toggleUserType);
	}
	
	public void setController(GestioneUtentiDialogController controller) {
		parentController = controller;
	}
	
	@FXML
	void btnAddPersonClicked(final ActionEvent event) {
		
		String nome = textFieldNome.getText().trim();
		String cognome = textFieldCognome.getText().trim();
		String email = textFieldEmail.getText().trim();
		String password = textFieldPassword.getText().trim();
		
		if(!(nome.isEmpty() || cognome.isEmpty() || email.isEmpty() || password.isEmpty() || toggleUserType.getSelectedToggle() != null)) {
			int userType = (int) toggleUserType.getSelectedToggle().getUserData();
			
			Boolean risultato = DatabaseMethods.aggiungiPersona(nome, cognome, email, password, userType);
			
			if(risultato) {
				parentController.refreshTable();
				closeStage(event);
			} else {
				labelError.setText("Utente non aggiunto");
			}
		} else {
			labelError.setText("Dati mancanti, inserire tutti i dati e riprovare");
		}
	}


	private void closeStage(final ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
}
