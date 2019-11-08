package testSpace;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import testSpace.EasyConsole.Console;
import testSpace.EasyConsole.ControlMenu;

public class Menu {
	
	public static class MenuFunctionary {

		protected static ArrayList<String> options = new ArrayList<String>(Arrays.asList("Logout", "New employee", "Update employee"));
		
		public static void Run() {
			switch(ControlMenu.RunMenu(options)) {
			case "Logout": 				logout();						break;
			case "New Employee":		addEmployee();					break;
			case "Update employee":		updateEmployee();				break;
			case "":					Console.Output("Errore");		break;
			}
		}
		
		public static void logout() {
			Console.Output("Logout");
		}

		public static void addEmployee() {
			Console.Output("Insert");
		}
		
		public static void updateEmployee() {
			Console.Output("Update");
		}

		// Methods

	}

	public static class MenuManager extends MenuFunctionary{
		
		protected static ArrayList<String> options = new ArrayList<String>(Arrays.asList("Logout", "New employee", "Update employee", "Search"));;

		public static void Run() {
			
			switch(ControlMenu.RunMenu(options)) {
			case "Logout": 				logout();						break;
			case "New Employee":		addEmployee();					break;
			case "Update employee":		updateEmployee();				break;
			case "Search":				search();						break;
			case "":					Console.Output("Errore");		break;
			}
		}
		
		public static void search() {
			Console.Output("Search");
		}
	}

	public static class MenuAdmin extends MenuManager {
		
		public static void Run() {
			MenuManager.Run();
		}
		
	}

}
