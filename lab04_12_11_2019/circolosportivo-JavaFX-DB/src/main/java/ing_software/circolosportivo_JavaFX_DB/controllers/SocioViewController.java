package ing_software.circolosportivo_JavaFX_DB.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ing_software.circolosportivo_JavaFX_DB.DatabaseMethods;
import ing_software.circolosportivo_JavaFX_DB.MainApp;
import ing_software.circolosportivo_JavaFX_DB.classes.Partecipazione;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

/** Controls the main application screen */
public class SocioViewController {
	private String loggedUser;
	@FXML
	private TableView<Partecipazione> partecipazioniTable;
	private ObservableList<Partecipazione> iscrizioniList = FXCollections.observableArrayList();

	@FXML
	private TableColumn<String, String> colAttivita;

	@FXML
	private Button logoutButton;

	public void initialize() {
		colAttivita.setCellValueFactory(new PropertyValueFactory<>("nomeAttivita"));
	}

	public void initSession(final Scene scene, String loggedUser) {
		this.loggedUser = loggedUser;
		refreshTable();
		logoutButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				MainApp.logout();
			}
		});
		
		partecipazioniTable.setRowFactory( tv -> {
		    TableRow<Partecipazione> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		        	Partecipazione rowData = row.getItem();
		            System.out.println(rowData);
		            
		            Alert alert = new Alert(AlertType.CONFIRMATION);
					
					alert.setTitle("Conferma");
					alert.setHeaderText("DISISCRIZIONE ATTIVITÀ");
					alert.setContentText("Vuoi veramente disiscriverti?");

					Optional<ButtonType> result = alert.showAndWait();
					if (result.get() == ButtonType.OK) {
						DatabaseMethods.lasciaAttivita(rowData.getNomeAttivita(), rowData.getNomeAttivita());
					} else {
						// ... user chose CANCEL or closed the dialog
					}
		        }
		    });
			
		    return row;
		});
	}
	
	

	@FXML
	void onOpenDialog(final ActionEvent event) throws IOException {
		
		// https://code.makery.ch/blog/javafx-dialogs-official/
		
		List<String> choices = new ArrayList<>();
		choices.add("a");
		choices.add("b");
		choices.add("c");

		ChoiceDialog<String> dialog = new ChoiceDialog<>("b", choices);
		dialog.setTitle("Choice Dialog");
		dialog.setHeaderText("Look, a Choice Dialog");
		dialog.setContentText("Choose your letter:");

		// Traditional way to get the response value.
		// iSPresent == false se l'utente fa cancel o non sceglie niente
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    System.out.println("Your choice: " + result.get());
		}

		// The Java 8 way to get the response value (with lambda expression).
		result.ifPresent(letter -> System.out.println("Your choice: " + letter));
		
	}

	public void refreshTable() {
		List<Partecipazione> partecipazioni = DatabaseMethods.getPartecipazioni(loggedUser);

		iscrizioniList = FXCollections.observableArrayList();

		partecipazioni.forEach(partecipazione -> {
			iscrizioniList.add(partecipazione);
		});

		partecipazioniTable.setItems(iscrizioniList);
	}
}