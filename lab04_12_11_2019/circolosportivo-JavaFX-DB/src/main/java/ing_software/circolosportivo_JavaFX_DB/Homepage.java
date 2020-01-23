package ing_software.circolosportivo_JavaFX_DB;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Homepage extends Application {
	
	private final String LoginFXML = "ing_software/circolosportivo_JavaFX_DB/FXML/loginFXML.fxml";
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(LoginFXML));

		primaryStage.setTitle("FXML Welcome");
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
