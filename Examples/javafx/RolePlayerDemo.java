package it.unipr.sowide.java.javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class RolePlayerDemo extends Application
{
  private static TextField txtCharacter;
  private static TextField txtActor;

  @Override
  public void start(final Stage primaryStage)
  {
    Label lblCharacter = new Label("Character's Name:");
    lblCharacter.setMinWidth(100);
    lblCharacter.setAlignment(Pos.BOTTOM_RIGHT);
    txtCharacter = new TextField();
    txtCharacter.setMinWidth(200);
    txtCharacter.setMaxWidth(200);
    txtCharacter.setPromptText("Enter the name of the character here.");

    Label lblActor = new Label("Actor's Name:");
    lblActor.setMinWidth(100);
    lblActor.setAlignment(Pos.BOTTOM_RIGHT);

    txtActor = new TextField();
    txtActor.setMinWidth(200);
    txtActor.setMaxWidth(200);
    txtActor.setPromptText("Enter the name of the actor here.");

    Label lblRole1 = new Label("The role of ");
    Label lblRole2 = new Label();
    Label lblRole3 = new Label(" will be played by ");
    Label lblRole4 = new Label();

    HBox paneCharacter = new HBox(20, lblCharacter, txtCharacter);
    paneCharacter.setPadding(new Insets(10));

    HBox paneActor = new HBox(20, lblActor, txtActor);
    paneActor.setPadding(new Insets(10));

    HBox paneRole = new HBox(lblRole1, lblRole2, lblRole3, lblRole4);
    paneRole.setPadding(new Insets(10));

    VBox pane = new VBox(10, paneCharacter, paneActor, paneRole);

    lblRole2.textProperty().bind(txtCharacter.textProperty());
    lblRole4.textProperty().bind(txtActor.textProperty());

    Scene scene = new Scene(pane);
    primaryStage.setScene(scene);
    primaryStage.setTitle("Role Player");
    primaryStage.show();
  }

  public static void main(final String[] args)
  {
    launch(args);
  }
}
