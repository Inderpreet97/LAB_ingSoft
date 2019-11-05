package ingegneria_software.SystemClass;

import java.util.Scanner;


public class EasyConsole {
	
	public static Scanner scanner;

	static class Console {

		public static void Output(String text) {
			System.out.println(text);
		}
		
		public static int IntInput(String text) {
			scanner = new Scanner(System.in);
			Output(text);
			int input = scanner.nextInt();
			scanner.close();
			return input;
		}
		
		public static String Input(String text) {
			scanner = new Scanner(System.in);
			Output(text);
			String input = scanner.nextLine();
			scanner.close();
			return input;
		}
	}

}
