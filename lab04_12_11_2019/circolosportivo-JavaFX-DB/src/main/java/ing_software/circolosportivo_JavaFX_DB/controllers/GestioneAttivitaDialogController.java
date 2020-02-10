package ing_software.circolosportivo_JavaFX_DB.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import ing_software.circolosportivo_JavaFX_DB.DatabaseMethods;
import ing_software.circolosportivo_JavaFX_DB.classes.Attivita;
import ing_software.circolosportivo_JavaFX_DB.classes.Corso;
import ing_software.circolosportivo_JavaFX_DB.classes.Gara;
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

public class GestioneAttivitaDialogController {
	@FXML
	private TableView<Attivita> attivitaTable;
	private ObservableList<Attivita> attivitaList = FXCollections.observableArrayList();

	@FXML
	private TableColumn<String, String> colTipo;
	@FXML
	private TableColumn<String, String> colNome;
	
	@FXML
	private Label labelError;
	
	private Attivita selectedAttivita = null;
	
	public void initialize() {
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

		refreshTable();
		
		attivitaTable.setRowFactory(tv -> {
			TableRow<Attivita> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty()) {
					selectedAttivita = row.getItem();
				}
			});
			return row;
		});
	}
	
	public void refreshTable() {
		List<Attivita> attivitaListDB = DatabaseMethods.getAttivitaList();
		
		attivitaList = FXCollections.observableArrayList();
		
		attivitaListDB.forEach(attivita -> {
			
			if(attivita instanceof Corso) {
				attivita.setTipo("corso");
				attivitaList.add(attivita);
				
			} else if(attivita instanceof Gara) {
				attivita.setTipo("gara");
				attivitaList.add(attivita);
			}
		});
		
		attivitaTable.setItems(attivitaList);
		
		selectedAttivita = null;
		labelError.setText("");
	}
	
	@FXML
	void btnAggiungiAttivitaClicked(final ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader()
				.getResource("ing_software/circolosportivo_JavaFX_DB/FXML/AddActivityDialog.fxml"));

		Parent parent = fxmlLoader.load();
		
		AddActivityDialogController controller = fxmlLoader.<AddActivityDialogController>getController();
		controller.setController(this);
		
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
		stage.showAndWait();
	}
	
	@FXML
	void btnEliminaAttivitaClicked(final ActionEvent event) {
		if (selectedAttivita != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);

			alert.setTitle("Conferma");
			alert.setHeaderText("ELIMINA ATTIVITA");
			alert.setContentText("Vuoi veramente eliminare " + selectedAttivita.getNome() + " ?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				Boolean risultato = DatabaseMethods.rimuoviAttivita(selectedAttivita.getNome());
				if (risultato) {
					refreshTable();
					labelError.setText("Attivita eliminata");
				} else {
					labelError.setText("Errore durante l'eliminazione");
				}
			} else {
				// ... user chose CANCEL or closed the dialog
			}
		} else {
			labelError.setText("No activity selected");
		}
	}
}
