package testSpace;

import java.util.ArrayList;

public class TestApp {
	
	public static void main(String[] args) {
		System.out.println("NomeApp:" + App.nomeApp);
		
		App app = new App();
		
		app.nomeApp = "ciao";
		
		System.out.println("NomeApp:" + App.nomeApp);
		
		App app2 = new App();
		
		System.out.println("NomeApp (app2):" + app2.nomeApp);
		System.out.println("NomeApp (App):" + App.nomeApp);
	}

	static class App {
		
		public static String nomeApp;
		
	}
}
