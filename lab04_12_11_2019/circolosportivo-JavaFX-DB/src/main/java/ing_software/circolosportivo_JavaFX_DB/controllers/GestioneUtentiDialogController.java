package ing_software.circolosportivo_JavaFX_DB.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import ing_software.circolosportivo_JavaFX_DB.DatabaseMethods;
import ing_software.circolosportivo_JavaFX_DB.classes.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GestioneUtentiDialogController {
	private String loggedUser;
	
	@FXML
	private TableView<Persona> personaTable;
	private ObservableList<Persona> personaList = FXCollections.observableArrayList();

	@FXML
	private TableColumn<String, String> colNome;
	@FXML
	private TableColumn<String, String> colCognome;
	@FXML
	private TableColumn<String, String> colEmail;

	@FXML
	private Label labelError;

	private Persona selectedUser = null;

	public void initialize() {
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		// Single Click on Table Row Event
		personaTable.setRowFactory(tv -> {
			TableRow<Persona> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty()) {
					selectedUser = row.getItem();
				}
			});
			return row;
		});
	}
	
	public void setLoggedUser(String user) {
		loggedUser = user;
		refreshTable(); // Carica i dati nella tabella
	}

	public void refreshTable() {
		List<Persona> persone = DatabaseMethods.getPersonaList();
		
		personaList = FXCollections.observableArrayList();
		

		persone.forEach(persona -> {
			if(!persona.getEmail().equals(loggedUser)) {
				personaList.add(persona);
			}
		});

		personaTable.setItems(personaList);
		
		selectedUser = null;
		labelError.setText("");
	}
	
	/**
	 * Apri finestra di Aggiunta di un nuovo Utente
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void aggiungiUtente(final ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader()
				.getResource("ing_software/circolosportivo_JavaFX_DB/FXML/AddPersonDialog.fxml"));

		Parent parent = fxmlLoader.load();
		
		AddPersonDialogController controller = fxmlLoader.<AddPersonDialogController>getController();
		controller.setController(this);
		
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.showAndWait();
	}
	
	/**
	 * Dialogo di Eliminazione di un Utente
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void eliminaUtente(final ActionEvent event) throws IOException {
		if (selectedUser != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);

			alert.setTitle("Conferma");
			alert.setHeaderText("ELIMINA UTENTE");
			alert.setContentText("Vuoi veramente eliminare " + selectedUser.getEmail() + " ?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				Boolean risultato = DatabaseMethods.rimuoviPersona(selectedUser.getEmail());
				if (risultato) {
					refreshTable();
					labelError.setText("Utente eliminato");
				} else {
					labelError.setText("Errore durante l'eliminazione");
				}
			} else {
				// ... user chose CANCEL or closed the dialog
			}
		} else {
			labelError.setText("No user selected");
		}

	}
	
	/**
	 * Apri finestra di Modifica dell'utente selezionato
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void btnModificaPersonaClicked(final ActionEvent event) throws IOException {
		if (selectedUser != null) {
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader()
					.getResource("ing_software/circolosportivo_JavaFX_DB/FXML/modificaUtenteDialog.fxml"));

			Parent parent = fxmlLoader.load();
			
			ModificaUtenteDialogController controller = fxmlLoader.<ModificaUtenteDialogController>getController();
			controller.setController(this);
			controller.loadData(selectedUser.getNome(), selectedUser.getCognome(), selectedUser.getEmail());

			Scene scene = new Scene(parent);
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(scene);
			stage.showAndWait();
			
		} else {
			labelError.setText("No user selected");
		}
	}

}
