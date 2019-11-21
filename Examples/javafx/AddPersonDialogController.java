package it.unipr.sowide.java.javafx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPersonDialogController
{
  @FXML
  private TextField tfId;
  @FXML
  private TextField tfName;
  @FXML
  private TextField tfAge;

  private ObservableList<Person> appMainObservableList;

  @FXML
  void btnAddPersonClicked(final ActionEvent event)
  {
    System.out.println("btnAddPersonClicked");

    int    id   = Integer.valueOf(tfId.getText().trim());
    String name = tfName.getText().trim();
    int    age  = Integer.valueOf(tfAge.getText().trim());

    Person data = new Person(id, name, age);

    appMainObservableList.add(data);

    closeStage(event);
  }

  public void setAppMainObservableList(final ObservableList<Person> ol)
  {
    this.appMainObservableList = ol;
  }

  private void closeStage(final ActionEvent event)
  {
    Node  source = (Node) event.getSource();
    Stage stage  = (Stage) source.getScene().getWindow();
    stage.close();
  }
}
