package ing_software.gestione_impiegati;

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
				Console.OutputLN((index + 1) + ") " + option);
				index++;
			}

			try {
				while (!loop) {

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
		
		public static int ChoiceMenu(ArrayList<String> options) {
			int choice = 0;
			
			do {
				int index = 0;
				for (String option : options) {
					Console.OutputLN((index+1) + ")" + option);
					index++;
				}
				choice = Console.IntInput("Option: ");
			} while (choice < 1 || choice > options.size());
			
			return choice;
		}
		
	}

	public enum InputType {
		// TODO
	}

	public static class Console {

		public static void Enter(String text) { // Print the text and waiting until the user types enter
			System.out.print(text);
			scanner.nextLine();
		}

		public static void EnterDefault() {
			System.out.println("Press [enter] to continue...");
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
			int returnInt = scanner.nextInt();
			scanner.nextLine();
			return returnInt;
			
		}

		public static double DoubleInput(String text) {
			Output(text);
			return scanner.nextDouble();
		}

		public static String Input(String text) {
			Output(text);
			return scanner.nextLine();
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
