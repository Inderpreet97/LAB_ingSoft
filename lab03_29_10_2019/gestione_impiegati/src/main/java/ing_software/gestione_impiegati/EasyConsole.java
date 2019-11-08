package ing_software.gestione_impiegati;

import java.util.Scanner;

public class EasyConsole {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static class Console {
		
		
		public static void Enter(String text) {			// Print the text and waiting until the user types enter
			System.out.print(text);
			scanner.nextLine();
		}
		
		// OUTPUT
		public static void Output (Object text) {
			System.out.print(text);
		}
		
		public static void OutputLN (Object text) {
			System.out.println(text);
		}
		
		// INPUT
		
		public static int IntInput(String text) {
			Output(text);
			return scanner.nextInt();
		}
		
		public static double DoubleInput(String text) {
			Output(text);
			return scanner.nextDouble();
		}
		
		public static String Input(String text) {
			Output(text);
			return scanner.next();
		}
		
		public static long LongInput(String text) {
			Output(text);
			return scanner.nextLong();
		}
		
		public static float FloatInput(String text) {
			Output(text);
			return scanner.nextFloat();
		}
		
		public static short ShortInput(String text) {
			Output(text);
			return scanner.nextShort();
		}
		
	}

}
