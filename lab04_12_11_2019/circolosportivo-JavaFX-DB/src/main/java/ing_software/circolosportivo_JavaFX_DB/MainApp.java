package ing_software.circolosportivo_JavaFX_DB;

import java.io.IOException;

import ing_software.circolosportivo_JavaFX_DB.controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	private static Scene scene;
	
	@Override
	public void start(Stage stage) throws IOException {
		
		scene = new Scene(new StackPane());
	    
		showLoginScreen();
		
		stage.setTitle("Circolo Sportivo");
	    stage.setScene(scene);
	    stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public static void logout() {
		showLoginScreen();
	}

	private static void showLoginScreen() {
		try {
			FXMLLoader loader = new FXMLLoader(MainApp.class.getClassLoader().getResource("ing_software/circolosportivo_JavaFX_DB/FXML/login.fxml"));
			scene.setRoot((Parent) loader.load());
			LoginController controller = loader.<LoginController>getController();
			controller.initManager(scene);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
}
