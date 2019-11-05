package ingegneria_software.SystemClass;

import ingegneria_software.SystemClass.EasyConsole.Console;

public class Main {

	public static void main(String[] args) {
		
		Console.Output("ciao" + 5);
		
		int numero = Console.IntInput("Inserisci numero: ");
		Console.Output("" + numero);
		
	}

}
