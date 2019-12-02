package ing_software.gestione_impiegati;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import ing_software.gestione_impiegati.Client.ClientManager;
import ing_software.gestione_impiegati.EasyConsole.Console;
import ing_software.gestione_impiegati.EasyConsole.ControlMenu;

public class Menu {

	public static class MenuFunctionary {

		protected static ArrayList<String> options = new ArrayList<String>(
				Arrays.asList("Logout", "New Employee", "Update Employee"));

		public static void Run() {

			Boolean running = true;
			while (running) {
				switch (ControlMenu.RunMenu(options).toLowerCase()) {
				case "logout":
					running = false;
					break;
				case "new employee":
					addEmployee();
					break;
				case "update employee":
					updateEmployee();
					break;
				case "":
					Console.Output("Error");
					break;
				default:
					Console.Output("Unknown command");
					break;
				}
			}
		}

		// Methods

		public static void addEmployee() {
			Console.Output("Insert");

			String fiscalCode = Console.Input("Fiscal code: ");
			String username = Console.Input("Username: ");
			String password = Console.Input("Password: ");
			String name = Console.Input("Name: ");
			String surname = Console.Input("Surname: ");

			// TODO WORKING HERE
			// String jobString = Console.Input("Job: ");
			// Jobs job = Jobs.valueOf(jobString);
			Jobs job = Console.EnumInput(Jobs.class, "Select the job of the employee: ",
					new Jobs[] { Jobs.admin, Jobs.manager });

			String branch = Console.Input("Branch: ");

			LocalDate startDate = Console.LocalDateInput("Start date: ");
			LocalDate endDate = Console.LocalDateInput("End date: ");
			

			Employee employee = new Employee(fiscalCode, username, password, name, surname, job, branch, startDate,
					endDate);

			Message message = new Message(employee, "", Functions.insertEmployee);
			Message returnMessage = ClientManager.send(message);

			// Done or Error
			if (ClientManager.checkMessage(returnMessage)) {
				Console.OutputLN("Employee added");
			} else {
				int choice = ControlMenu.ChoiceMenu(new ArrayList<String>(Arrays.asList("Add correct employee", "Return to Menu")));
				if (choice == 1) {
					addEmployee();
				}
				// Else end this function and return to menu
			}

		}

		public static void updateEmployee() {

			Console.Output("Update");

			ClientManager.loggedUser.print();

			Console.OutputLN("Update your profile");
			Console.Output("Type to update, or press [Enter] if you do not want to modify");

			String username = Console.Input("Username: ");
			String name = Console.Input("Name: ");
			String surname = Console.Input("Surname: ");

			// Check if the parameters are all empty or not
			if (username.isEmpty() && name.isEmpty() && surname.isEmpty()) {
				Console.Output("No updates");
			} else {

				// Copy the logged User (backup) and set new parameters
				Employee tempEmployee = ClientManager.loggedUser;
				tempEmployee.setUsername(username);
				tempEmployee.setName(name);
				tempEmployee.setSurname(surname);

				Message message = new Message(tempEmployee, "", Functions.updateEmployee);
				Message returnMessage = ClientManager.send(message);

				if (ClientManager.checkMessage(returnMessage)) {
					Console.Output("Update done");
				} else {
					Console.Output(returnMessage.getContent());
				}

			}

		}

	}

	public static class MenuManager extends MenuFunctionary {

		protected static ArrayList<String> options = new ArrayList<String>(Arrays.asList("Logout", "New employee",
				"Update employee", "Worker list", "Functionary list", "Manager list"));;

		public static void Run() {
			Boolean running = true;
			while (running) {
				switch (ControlMenu.RunMenu(options)) {
				case "Logout":
					running = false;
					break;
				case "New Employee":
					addEmployee();
					break;
				case "Update employee":
					updateEmployee();
					break;
				case "Worker list":
					search("worker");
					break;
				case "Functionary list":
					search("functionary");
					break;
				case "Manager list":
					search("manager");
					break;
				case "":
					Console.Output("Errore");
					break;
				}
			}
		}

		public static void search(String job) {
			
			Message message = new Message(null, job, Functions.searchEmployee);
			Message returnMessage = ClientManager.send(message);

			if (ClientManager.checkMessage(returnMessage)) {
				// Get list of employee from the object of returnMessage
				@SuppressWarnings("unchecked")
				ArrayList<Employee> employeeList = (ArrayList<Employee>) returnMessage.getObj();

				// Print list
				for (Employee employee : employeeList) {
					Console.OutputLN("Fiscal code:" + employee.getFiscalCode());
					Console.OutputLN("Username: " + employee.getUsername());
					Console.OutputLN("Name: " + employee.getName());
					Console.OutputLN("Surname: " + employee.getSurname());
					Console.OutputLN("Branch: " + employee.getBranch());
					Console.OutputLN("Job: " + employee.getJob());
					Console.OutputLN(null);
					Console.OutputLN(null);
				}

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
