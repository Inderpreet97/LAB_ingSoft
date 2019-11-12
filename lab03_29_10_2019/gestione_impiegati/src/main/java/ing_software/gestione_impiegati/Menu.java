package ing_software.gestione_impiegati;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import ing_software.gestione_impiegati.Client.ClientManager;
import ing_software.gestione_impiegati.EasyConsole.Console;
import ing_software.gestione_impiegati.EasyConsole.ControlMenu;

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
	
		// Methods
		
		public static void logout() {
			Console.Output("Logout");
		}

		public static void addEmployee() {
			Console.Output("Insert");
			
			String fiscalCode = Console.Input("Fiscal code: ");
			String username = Console.Input("Username: ");
			String password = Console.Input("Password: ");
			String name = Console.Input("Name: ");
			String surname = Console.Input("Surname: ");

			String job = Console.Input("Job: ");
			String branch = Console.Input("Branch: ");

			LocalDate startDate = Console.LocalDateInput("Start date: ");
			LocalDate endDate = Console.LocalDateInput("End date: ");
			
			Employee employee = new Employee(fiscalCode, username, password, name, surname, job, branch, startDate, endDate);
			
			Message message = new Message(employee, "", Functions.insertEmployee);
			Message returnMessage = ClientManager.send(message);
			
			// Done or Error
			if (ClientManager.checkMessage(returnMessage)) {
				Console.OutputLN("Employee added");
			} else {
				// Retype
			}
			
			
		}
		
		public static void updateEmployee() {
			Console.Output("Update");
		}

		
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
		}
	}

	public static class MenuAdmin extends MenuManager {
		
		public static void Run() {
			MenuManager.Run();
		}
		
	}

}
