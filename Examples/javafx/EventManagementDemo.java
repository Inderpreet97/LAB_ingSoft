package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class EventManagementDemo extends Application
{
  private String radioValue = "";

  @Override
  public void start(final Stage stage)
  {
    ToggleGroup group = new ToggleGroup();

    RadioButton button1 = new RadioButton("none");
    button1.setUserData("none");
    button1.setToggleGroup(group);
    RadioButton button2 = new RadioButton("handler");
    button2.setUserData("handler");
    button2.setToggleGroup(group);
    RadioButton button3 = new RadioButton("filter");
    button3.setUserData("filter");
    button3.setToggleGroup(group);
    RadioButton button4 = new RadioButton("all");
    button4.setUserData("all");
    button4.setToggleGroup(group);

    group.selectedToggleProperty().addListener((t) -> {
      radioValue = group.getSelectedToggle().getUserData().toString();

      System.out.println(radioValue);
    });

    Circle circle = new Circle(150, 150, 50);
    circle.setFill(Color.CORAL);

    EventHandler<MouseEvent> mouseEventFilter = (e) -> {
      System.out.println("Mouse event filter has been called");

      if (radioValue.equals("all") || radioValue.equals("filter"))
      {
       e.consume();
      }
    };

    EventHandler<MouseEvent> mouseEventHandler = (e) -> {
      System.out.println("Mouse event handler has been called");

      if (radioValue.equals("all") || radioValue.equals("handler"))
      {
       e.consume();
      }
    };

    circle.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventFilter);
    circle.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEventHandler);

    HBox root  = new HBox();
    VBox radio = new VBox();

    radio.getChildren().addAll(button1, button2, button3, button4);
    root.getChildren().addAll(circle, radio);

    radio.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;"
        + "-fx-border-insets: 5;" + "-fx-border-radius: 5;"
        + "-fx-border-color: red;");

    root.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
        + "-fx-border-width: 2;"
        + "-fx-border-insets: 5;" + "-fx-border-radius: 5;"
        + "-fx-border-color: blue;");

    Scene scene = new Scene(root);

    stage.setScene(scene);
    stage.setTitle("Consumers");
    stage.show();
  }

  public static void main(final String[] args)
  {
    Application.launch(args);
  }
}
