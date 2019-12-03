package ing_software.gestione_impiegati;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

				case "logout":				running = false;						break;
				case "new employee":		addEmployee();							break;
				case "update employee":		updateEmployee();						break;	
				case "":					Console.Output("Error");				break;
				default:					Console.Output("Unknown command");		break;

				}
			}
		}

		// Methods

		public static void addEmployee() {
			Console.Output("\n>> Add Employee <<\n");

			String fiscalCode 	= Console.Input("Fiscal code: ");
			String username 	= Console.Input("Username: ");
			String password 	= Console.Input("Password: ");
			String name 		= Console.Input("Name: ");
			String surname 		= Console.Input("Surname: ");

			Jobs job = Console.EnumInput(Jobs.class, "Select the job of the employee: ",
					new Jobs[] { Jobs.admin, Jobs.manager });

			String branch = Console.Input("Branch: ");

			LocalDate startDate = Console.LocalDateInput("Start date [d/M/yyyy] : ");

			String date = Console.Input("End date [d/M/yyyy] (press enter to skip): ");
			LocalDate endDate = null;

			if (!date.isEmpty()) {
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
				endDate = LocalDate.parse(date, dateFormat);
			}

			if(!(fiscalCode.isEmpty() || username.isEmpty() || password.isEmpty() 
					|| name.isEmpty() || surname.isEmpty() || branch.isEmpty() || startDate.equals(null))) {
				Employee employee = new Employee(fiscalCode, username, password, name, surname, job, branch, startDate,
						endDate);

				Message message = new Message(employee, "", Functions.insertEmployee);
				Message returnMessage = ClientManager.send(message);

				// Done or Error
				if (ClientManager.checkMessage(returnMessage)) {
					Console.OutputLN("\n>> Employee added \n\n");
				} else {
					int choice = ControlMenu.ChoiceMenu(new ArrayList<String>(Arrays.asList("Add correct employee", "Return to Menu")));
					if (choice == 1) {
						addEmployee();
					}
					// Else end this function and return to menu
				}
				
			} else {
				Console.OutputLN("\n>> Some data is missing");
				int choice = ControlMenu.ChoiceMenu(new ArrayList<String>(Arrays.asList("Add correct employee", "Return to Menu")));
				if (choice == 1) {
					addEmployee();
				}
				// Else end this function and return to menu
			}

		}

		@SuppressWarnings("unchecked")
		public static void updateEmployee() {
			
			ArrayList<String> optionsArray = new ArrayList<String>();
			optionsArray.add("Update your profile");
			
			Message message = new Message(ClientManager.loggedUser.getJob(), ClientManager.loggedUser.getJob().toString(), Functions.searchEmployee);
			Message returnMessage = ClientManager.send(message);
			
			ArrayList<Employee> employeeList = new ArrayList<Employee>();
			
			if (ClientManager.checkMessage(returnMessage)) {
				// Get list of employee from the object of returnMessage
				
				employeeList = (ArrayList<Employee>) returnMessage.getObj();

				if (employeeList.size() > 0) {
					for (Employee employee : employeeList) {
						optionsArray.add(employee.getFiscalCode());
					}
				}
			} else {
				Console.Output("Error occurred");
			}
			
			int choice = ControlMenu.ChoiceMenu(optionsArray);
			
			Employee toUpdateEmployee = null;
			
			if(choice == 1) {
				Console.OutputLN("\n>> Update your profile <<\n");
				toUpdateEmployee = ClientManager.loggedUser;
			} else {
				Console.OutputLN("");
				toUpdateEmployee = employeeList.get(choice - 2);
			}
			
			toUpdateEmployee.print();

			Console.OutputLN("Type to update, or press [Enter] if you do not want to modify\n");

			String username	= 	Console.Input("Username: ");
			String name		=	Console.Input("Name: ");
			String surname	= 	Console.Input("Surname: ");

			// Check if the parameters are all empty or not
			if (username.isEmpty() && name.isEmpty() && surname.isEmpty()) {
				Console.OutputLN("\n>> No updates\n");
			} else {

				if (!username.isEmpty())		toUpdateEmployee.setUsername(username);
				if (!name.isEmpty())			toUpdateEmployee.setName(name);
				if (!surname.isEmpty())			toUpdateEmployee.setSurname(surname);

				message = new Message(toUpdateEmployee, "", Functions.updateEmployee);
				returnMessage = ClientManager.send(message);

				if (ClientManager.checkMessage(returnMessage)) {
					Console.OutputLN("\n>> Update done\n");
				} else {
					Console.OutputLN(returnMessage.getContent());
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

						case "Logout":				running = false;				break;
						case "New Employee":		addEmployee();					break;
						case "Update employee":		updateEmployee();				break;
						case "Worker list":			search(Jobs.worker);			break;
						case "Functionary list":	search(Jobs.functionary);		break;
						case "Manager list":		search(Jobs.manager);			break;
						case "":					Console.Output("Errore");		break;
						}
					}
				}

				public static void search(Jobs job) {

					Message message = new Message(job, job.toString(), Functions.searchEmployee);
					Message returnMessage = ClientManager.send(message);

					if (ClientManager.checkMessage(returnMessage)) {
						// Get list of employee from the object of returnMessage

						@SuppressWarnings("unchecked")
						ArrayList<Employee> employeeList = (ArrayList<Employee>) returnMessage.getObj();

						// Print list
						if (employeeList.size() > 0) {
							int index = 1;
							for (Employee employee : employeeList) {
								Console.OutputLN("\nEmployee " + index + " :");
								Console.OutputLN("Fiscal code: " + employee.getFiscalCode());
								Console.OutputLN("Username: " + employee.getUsername());
								Console.OutputLN("Name: " + employee.getName());
								Console.OutputLN("Surname: " + employee.getSurname());
								Console.OutputLN("Branch: " + employee.getBranch());
								Console.OutputLN("Job: " + employee.getJob());
								index ++;
							}
						} else {
							Console.OutputLN("\n>> " + job + " list is empty\n");
						}

					} else {
						Console.Output("Error occurred");
					}

				}
	}

	public static class MenuAdmin extends MenuManager {

		protected static ArrayList<String> options = new ArrayList<String>(Arrays.asList(
				"Logout", "New employee", "Update employee", "Worker list", "Functionary list", "Manager list", "Admin list"));;


				public static void Run() {
					Boolean running = true;
					while (running) {
						switch (ControlMenu.RunMenu(options)) {

						case "Logout":				running = false;				break;
						case "New Employee":		addEmployee();					break;
						case "Update employee":		updateEmployee();				break;
						case "Worker list":			search(Jobs.worker);			break;
						case "Functionary list":	search(Jobs.functionary);		break;
						case "Manager list":		search(Jobs.manager);			break;
						case "Admin list":			search(Jobs.admin);				break;
						case "":					Console.Output("Errore");		break;
						}
					}
				}
	}

}
