package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class FruitVegetableDemo extends Application
{
  @Override
  public void start(final Stage primaryStage)
  {
    primaryStage.setTitle("Fruits and Vegetables Manager");

    BorderPane componentLayout = new BorderPane();
    componentLayout.setPadding(new Insets(20, 0, 20, 20));

    final FlowPane choicePane = new FlowPane();
    choicePane.setHgap(100);
    Label choice = new Label("Fruits");

    ChoiceBox<String> fruits = new ChoiceBox<>(
        FXCollections.observableArrayList(
            "Asparagus", "Beans", "Broccoli", "Cabbage", "Carrot",
            "Celery", "Cucumber", "Leek", "Mushroom", "Pepper",
            "Radish", "Shallot", "Spinach", "Swede", "Turnip"));

    choicePane.getChildren().add(choice);
    choicePane.getChildren().add(fruits);

    componentLayout.setTop(choicePane);
    final FlowPane listPane = new FlowPane();
    listPane.setHgap(100);
    Label            listLbl    = new Label("Vegetables");
    ListView<String> vegetables = new ListView<>(
        FXCollections.observableArrayList(
            "Apple", "Apricot", "Banana", "Cherry", "Date",
            "Kiwi", "Orange", "Pear", "Strawberry"));

    listPane.getChildren().add(listLbl);
    listPane.getChildren().add(vegetables);
    listPane.setVisible(false);
    componentLayout.setCenter(listPane);

    Button vegFruitBut = new Button("Fruit or Vegetable");
    vegFruitBut.setOnAction((e) -> {
      choicePane.setVisible(!choicePane.isVisible());
      listPane.setVisible(!listPane.isVisible());
    });

    componentLayout.setBottom(vegFruitBut);

    Scene appScene = new Scene(componentLayout, 500, 500);
    primaryStage.setScene(appScene);
    primaryStage.show();
  }

  public static void main(final String[] args)
  {
    launch(args);
  }
}
