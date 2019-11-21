package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ContextMenuDemo extends Application
{
  @Override
  public void start(final Stage stage)
  {
    Label label = new Label();

    Circle circle = new Circle();
    circle.setRadius(80);
    circle.setFill(Color.AQUA);

    VBox root = new VBox();
    root.setPadding(new Insets(5));
    root.setSpacing(5);

    root.getChildren().addAll(label, circle);

    ContextMenu contextMenu = new ContextMenu();

    MenuItem item1 = new MenuItem("Menu Item 1");
    item1.setOnAction((e) -> label.setText("Select Menu Item 1"));

    MenuItem item2 = new MenuItem("Menu Item 2");
    item2.setOnAction((e) -> label.setText("Select Menu Item 2"));

    contextMenu.getItems().addAll(item1, item2);

    // When user right-click on Circle
    circle.setOnContextMenuRequested((e) -> contextMenu.show(
        circle, e.getScreenX(), e.getScreenY()));

    Scene scene = new Scene(root, 400, 200);

    stage.setTitle("Context Menu Demo");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(final String[] args)
  {
    Application.launch(args);
  }
}
