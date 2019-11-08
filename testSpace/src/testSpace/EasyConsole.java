package testSpace;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class EasyConsole {

	private static Scanner scanner = new Scanner(System.in);

	public static class ControlMenu {

		public static String RunMenu(ArrayList<String> options) {
			
			// Print menu and return choice input from options, else return an empty string
			
			int choice = 0;
			boolean loop = false;
			
			int index = 0;
			for (String option : options) {
				Console.OutputLN((index+1) + ") " + option);
				index++;
			}
			
			try {
				while(!loop) {
						
					choice = Console.IntInput("Choice: ");
					if (choice < 1 || choice > options.size()) {
						Console.Output("Type the correct index");
					} else {
						loop = true;
					}
				}
			} catch (Exception ex) {
				Console.OutputLN(ex.getMessage());
				return "";
			}
			
			return options.get(choice - 1);
		}
	}

	public static class Console {

		public static void Enter(String text) { // Print the text and waiting until the user types enter
			System.out.print(text);
			scanner.nextLine();
		}

		// OUTPUT
		public static void Output(Object text) {
			System.out.print(text);
		}

		public static void OutputLN(Object text) {
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

		public static LocalDate LocalDateInput(String text) {
			String date = Input(text);
			if (!date.equals(null)) {
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
				return LocalDate.parse(date, dateFormat);
			}
			return null;
		}

	}

}
