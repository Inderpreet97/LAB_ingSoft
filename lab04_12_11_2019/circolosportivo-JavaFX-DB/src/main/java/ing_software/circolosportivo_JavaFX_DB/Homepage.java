package ing_software.circolosportivo_JavaFX_DB;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Homepage extends Application {
	
	@Override
	public void start(Stage stage) throws IOException {
		
		Scene scene = new Scene(new StackPane());
	    
	    LoginManager loginManager = new LoginManager(scene);
	    loginManager.showLoginScreen();

	    stage.setScene(scene);
	    stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
