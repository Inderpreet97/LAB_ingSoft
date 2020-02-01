package ing_software.circolosportivo_JavaFX_DB.controllers;

import ing_software.circolosportivo_JavaFX_DB.classes.Partecipazione;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPersonDialogController {
	@FXML
	private TextField tfId;
	@FXML
	private TextField tfName;
	@FXML
	private TextField tfAge;

	private ObservableList<Partecipazione> iscrizioniList;

	@FXML
	void btnAddPersonClicked(final ActionEvent event) {
		System.out.println("btnAddPersonClicked");

		String id = tfId.getText().trim();
		String name = tfName.getText().trim();

		Partecipazione data = new Partecipazione(id, name);

		iscrizioniList.add(data);

		closeStage(event);
	}

	public void setAppMainObservableList(final ObservableList<Partecipazione> iscrizioniList) {
		this.iscrizioniList = iscrizioniList;
	}

	private void closeStage(final ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
}
