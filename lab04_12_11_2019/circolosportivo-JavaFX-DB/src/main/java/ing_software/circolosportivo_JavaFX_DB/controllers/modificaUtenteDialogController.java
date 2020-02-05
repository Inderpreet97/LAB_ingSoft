package ing_software.circolosportivo_JavaFX_DB.controllers;

import java.util.List;

import ing_software.circolosportivo_JavaFX_DB.DatabaseMethods;
import ing_software.circolosportivo_JavaFX_DB.classes.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class modificaUtenteDialogController {
	@FXML
	private TextField textFieldNome;
	@FXML
	private TextField textFieldCognome;
	@FXML
	private TextField textFieldEmail;
	@FXML
	private TextField textFieldPassword;

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

	public void initialize() {
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colCognome.setCellValueFactory(new PropertyValueFactory<>("cognome"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		
		refreshTable(); // Carica i dati nella tabella
		
		personaTable.setRowFactory(tv -> {
			TableRow<Persona> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty()) {
					Persona rowData = row.getItem();
					System.out.println(rowData);
					
					textFieldNome.setText(rowData.getNome());
					textFieldCognome.setText(rowData.getCognome());
					textFieldEmail.setText(rowData.getEmail());
				}
			});
			return row;
		});
	}
	
	public void refreshTable() {
		List<Persona> persone = DatabaseMethods.getPersonaList();

		personaList = FXCollections.observableArrayList();

		persone.forEach(persona -> {
			personaList.add(persona);
		});

		personaTable.setItems(personaList);
	}

	@FXML
	void btnModificaPersonaClicked(final ActionEvent event) {
		
		String nome = textFieldNome.getText().trim();
		String cognome = textFieldCognome.getText().trim();
		String email = textFieldEmail.getText().trim();
		String password = textFieldPassword.getText().trim();

		if (!(nome.isEmpty() || cognome.isEmpty() || email.isEmpty() || password.isEmpty())) {

			Boolean risultato = DatabaseMethods.modificaPersona(email, nome, cognome, password);

			if (risultato) {
				closeStage(event);
			} else {
				System.out.println("UTENTE NON AGGIORNATO");
				labelError.setText("Utente non aggiornato");
			}
		} else {
			System.out.println("Dati mancanti, inserire tutti i dati e riprovare");
			labelError.setText("Dati mancanti, inserire tutti i dati e riprovare");
		}
	}

	private void closeStage(final ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
}
