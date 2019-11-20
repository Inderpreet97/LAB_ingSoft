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
			
			// TODO nel content ci metto l'hold username e nel messaggio riempio solo i campi da modificare
			Console.Output("Update");
			
			ClientManager.loggedUser.print();
			
			String username = Console.Input("Username: ");
			String name = Console.Input("Name: ");
			String surname = Console.Input("Surname: ");
			
			Employee tempEmployee = new Employee(username, name, surname);
			Message message = new Message(tempEmployee, ClientManager.loggedUser.getUsername(), Functions.updateEmployee);
			Message returnMessage = ClientManager.send(message);
			
			if (ClientManager.checkMessage(message)) {
				// TODO update the clientManager.loggedUser!!!
				Console.Output("Done");
			} else {
				Console.Output("Error");
			}
			
		}

		
	}

	public static class MenuManager extends MenuFunctionary{
		
		protected static ArrayList<String> options = new ArrayList<String>(Arrays.asList("Logout", 
				"New employee", "Update employee", "Worker list", "Functionary list", "Manager list"));;

		public static void Run() {
			
			switch(ControlMenu.RunMenu(options)) {
			case "Logout": 				logout();						break;
			case "New Employee":		addEmployee();					break;
			case "Update employee":		updateEmployee();				break;
			case "Worker list":			search("worker");				break;
			case "Functionary list":	search("functionary");			break;
			case "Manager list":		search("manager");				break;
			case "":					Console.Output("Errore");		break;
			}
		}
		
		public static void search(String job) {
			//TODO Cerca solo gli impiegati
			Message message = new Message(null, job, Functions.searchEmployee);
			Message returnMessage = ClientManager.send(message);
			
			if (ClientManager.checkMessage(returnMessage)) {
				// Get list of employee
			} else {
				Console.Output("Error occurred");
			}
			
		}
	}

	public static class MenuAdmin extends MenuManager {
		
		public static void Run() {
			MenuManager.Run();
		}
		
	}

}
