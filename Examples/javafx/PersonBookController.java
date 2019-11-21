package it.unipr.sowide.java.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PersonBookController implements Initializable
{
  @FXML
  private TableView<Person> tvData;
  @FXML
  private TableColumn<String, String> colId;
  @FXML
  private TableColumn<String, String> colName;
  @FXML
  private TableColumn<String, String> colAge;

  private ObservableList<Person> tvObservableList = FXCollections
      .observableArrayList();

  @FXML
  void onOpenDialog(final ActionEvent event) throws IOException
  {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
        "AddPersonDialog.fxml"));

    Parent parent = fxmlLoader.load();

    AddPersonDialogController dialogController =
        fxmlLoader.<AddPersonDialogController> getController();
    dialogController.setAppMainObservableList(tvObservableList);

    Scene scene = new Scene(parent, 300, 200);
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setScene(scene);
    stage.showAndWait();
  }

  @Override
  public void initialize(final URL location, final ResourceBundle resources)
  {
    colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    colName.setCellValueFactory(new PropertyValueFactory<>("name"));
    colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
    tvData.setItems(tvObservableList);
  }
}
