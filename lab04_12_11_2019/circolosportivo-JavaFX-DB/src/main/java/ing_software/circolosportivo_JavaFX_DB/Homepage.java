package ing_software.circolosportivo_JavaFX_DB;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Homepage extends Application {
	
	private final String FXMLFILE = "ing_software/circolosportivo_JavaFX_DB/loginFXML.fxml";
	@Override
	public void start(Stage primaryStage) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(FXMLFILE));

		primaryStage.setTitle("FXML Welcome");
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
