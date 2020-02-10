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

public class AddActivityDialogController {
	@FXML
	private TextField textFieldNome;
	
	@FXML
	private Label labelError;
	
	@FXML 
	private ToggleButton toggleBtnCorso;
	@FXML 
	private ToggleButton toggleBtnGara;
	
	private GestioneAttivitaDialogController parentController;

	private ToggleGroup toggleActivityType;
	
	public void initialize() {
		toggleBtnCorso.setUserData(2);
		toggleBtnGara.setUserData(1);
		
		toggleActivityType = new ToggleGroup();
		
		toggleBtnCorso.setToggleGroup(toggleActivityType);
		toggleBtnGara.setToggleGroup(toggleActivityType);
	}
	
	@FXML
	void btnAddActivityClicked(final ActionEvent event) {
		
		String nome = textFieldNome.getText().trim();
		
		if(!nome.isEmpty() && toggleActivityType.getSelectedToggle() != null) {
			
			int activityType = (int) toggleActivityType.getSelectedToggle().getUserData();
			
			Boolean risultato = DatabaseMethods.aggiungiAttivita(nome, activityType);
			
			if(risultato) {
				parentController.refreshTable();
				closeStage(event);
			} else {
				labelError.setText("Attività non aggiunta");
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

	public void setController(GestioneAttivitaDialogController controller) {
		parentController = controller;
	}
}
