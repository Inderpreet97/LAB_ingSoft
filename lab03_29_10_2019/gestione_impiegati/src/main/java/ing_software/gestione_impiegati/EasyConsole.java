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
				Console.OutputLN("");
				for (String option : options) {
					Console.OutputLN((index + 1) + ")" + option);
					index++;
				}
				choice = Console.IntInput("Option: ");
			} while (choice < 1 || choice > options.size());

			return choice;
		}

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

		public static void OutputTAB(Object text) {
			Console.Output(text + "\t");
			Console.OutputLN(null);
		}

		// INPUT

		public static int IntInput(String text) {
			do {
				try {
					Output(text);
					int returnInt = scanner.nextInt();
					scanner.nextLine();
					return returnInt;
				} catch (Exception ex) {
					scanner.nextLine();
					OutputLN(">> Input error");
				}
			} while (true);
		}

		public static double DoubleInput(String text) {
			do {
				try {
					Output(text);
					double returnDouble = scanner.nextDouble();
					scanner.nextLine();
					return returnDouble;
				} catch (Exception ex) {
					scanner.nextLine();
					OutputLN(">> Input error");
				}
			} while (true);
		}

		public static String Input(String text) {
			Output(text);
			return scanner.nextLine();
		}

		public static long LongInput(String text) {
			do {
				try {
					Output(text);
					long reutrnLong = scanner.nextLong();
					scanner.nextLine();
					return reutrnLong;
				} catch (Exception ex) {
					scanner.nextLine();
					OutputLN(">> Input error");
				}
			} while (true);
		}

		public static float FloatInput(String text) {
			do {
				try {
					Output(text);
					float returnFloat = scanner.nextFloat();
					scanner.nextLine();
					return returnFloat;
				} catch (Exception ex) {
					scanner.nextLine();
					OutputLN(">> Input error");
				}
			} while (true);
		}

		public static short ShortInput(String text) {
			do {
				try {
					Output(text);
					short returnShort = scanner.nextShort();
					scanner.nextLine();
					return returnShort;
				} catch (Exception ex) {
					scanner.nextLine();
					OutputLN(">> Input error");
				}
			} while (true);
		}

		public static LocalDate LocalDateInput(String text) {
			do {
				try {
					String date = Input(text);
					if (!date.isEmpty()) {
						DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
						return LocalDate.parse(date, dateFormat);
					}
					return null;
				} catch (Exception ex) {
					OutputLN(">> Input error");
				}
			} while (true);
		}

		public static <E extends Enum<E>> E EnumInput(Class<E> enumClass, String text) {
			OutputLN(text);

			E[] enumValues = enumClass.getEnumConstants();

			int index = 1;

			for (E enumVal : enumValues) {
				OutputLN(index + ") " + enumVal.toString());
				index++;
			}

			do {
				index = IntInput("Choice: ") - 1;
			} while (index <= 0 && index > enumValues.length);

			E job = E.valueOf(enumClass, enumValues[index].toString());

			return job;
		}

		public static <E extends Enum<E>> E EnumInput(Class<E> enumClass, String text, E[] blackListValues) {
			OutputLN(text);

			E[] enumValues = enumClass.getEnumConstants();

			int realIndex = 0;
			int relativeIndex = 1;
			ArrayList<Integer> relativeIndexes = new ArrayList<Integer>();

			for (E enumVal : enumValues) {
				Boolean toPrint = true;

				for (E blackListValue : blackListValues) {
					if (blackListValue.equals(enumVal)) {
						toPrint = false;
						break;
					}
				}

				if (toPrint) {
					OutputLN(relativeIndex + ") " + enumVal.toString());
					relativeIndex++;
					relativeIndexes.add(realIndex);
				}

				realIndex++;
			}

			do {
				relativeIndex = IntInput("Choice: ") - 1;
			} while (relativeIndex < 0 || relativeIndex >= relativeIndexes.size());

			E job = E.valueOf(enumClass, enumValues[relativeIndexes.get(relativeIndex)].toString());

			return job;
		}

	}

}
