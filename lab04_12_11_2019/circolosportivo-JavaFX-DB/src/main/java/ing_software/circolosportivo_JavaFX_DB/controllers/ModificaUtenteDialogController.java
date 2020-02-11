package ing_software.circolosportivo_JavaFX_DB.controllers;

import ing_software.circolosportivo_JavaFX_DB.DatabaseMethods;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModificaUtenteDialogController {
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
	
	private GestioneUtentiDialogController parentController;

	public void initialize() {

	}
	
	/**
	 * Metodo usato dalla classe chiamante per presettare i dati dell'utente da modificare
	 * 
	 * @param nome
	 * @param cognome
	 * @param email
	 */
	public void loadData(String nome, String cognome, String email) {
		textFieldNome.setText(nome);
		textFieldCognome.setText(cognome);
		textFieldEmail.setText(email);
	}
	
	public void setController(GestioneUtentiDialogController controller) {
		parentController = controller;
	}

	@FXML
	void btnModificaPersonaClicked(final ActionEvent event) {
		String nome = textFieldNome.getText().trim();
		String cognome = textFieldCognome.getText().trim();
		String email = textFieldEmail.getText().trim();
		String password = textFieldPassword.getText().trim();

		if (!(nome.isEmpty() || cognome.isEmpty() || email.isEmpty())) {

			Boolean risultato = DatabaseMethods.modificaPersona(email, nome, cognome, password);

			if (risultato) {
				parentController.refreshTable();
				closeStage(event);
			} else {
				labelError.setText("Utente non aggiornato");
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
