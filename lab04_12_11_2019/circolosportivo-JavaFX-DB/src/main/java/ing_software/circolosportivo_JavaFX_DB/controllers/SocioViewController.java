package ing_software.circolosportivo_JavaFX_DB.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ing_software.circolosportivo_JavaFX_DB.DatabaseMethods;
import ing_software.circolosportivo_JavaFX_DB.MainApp;
import ing_software.circolosportivo_JavaFX_DB.classes.Attivita;
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
						System.out.println(rowData.getNomeAttivita() + " - " + rowData.getEmailPersona());
						Boolean risultato = DatabaseMethods.lasciaAttivita(rowData.getNomeAttivita(), rowData.getEmailPersona());
						if(risultato) refreshTable();
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

		List<Attivita> attivita = DatabaseMethods.getAttivitaList();
		
		List<String> choices = new ArrayList<>();
		
		attivita.forEach(a -> choices.add(a.getNome()));

		ChoiceDialog<String> dialog = new ChoiceDialog<>("---", choices);
		dialog.setTitle("Choice Dialog");
		dialog.setHeaderText("A quale attivita vuoi iscriverti?");
		dialog.setContentText("Scegli l'attività:");

		// Traditional way to get the response value.
		// iSPresent == false se l'utente fa cancel o non sceglie niente
		Optional<String> result = dialog.showAndWait();
		
		if (result.isPresent()){
			Boolean risultato = DatabaseMethods.iscrizioneAttivita(result.get(), loggedUser);
			if (risultato) refreshTable();
			
		}
		
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