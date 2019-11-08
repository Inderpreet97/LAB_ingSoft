package ing_software.gestione_impiegati;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import ing_software.gestione_impiegati.EasyConsole.Console;

public class Client {
	static Scanner scanner = new Scanner(System.in);

	// Logged user variables
	private static Employee loggedUser;

	private static final int SPORT = 4444;
	private static final String SHOST = "localhost";

	public Message send(Message message)
	{
		try
		{
			Socket  client = new Socket(SHOST, SPORT);

			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream  is = new ObjectInputStream(client.getInputStream());
			Message returnMessage;

			while (true)
			{
				// Sends messages until it receives an �end� message

				os.writeObject(message);
				os.flush();

				Object o = is.readObject();

				if ((o != null) && (o instanceof Message))
				{
					returnMessage = (Message) o;
					break;
				}
			}

			client.close();
			return returnMessage;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static void main(final String[] v)
	{

		// LOGIN
		Boolean userIsLogged = false;
		String username;
		String password;

		do {
			System.out.println("=========> Employee Manager <=========");
			System.out.println("=========> Login <========= ");

			try {
				System.out.print("Username: ");
				username = scanner.nextLine();

				System.out.print("Password: ");
				password = scanner.nextLine();

				if (username.isEmpty() || password.isEmpty()) {
					throw new Exception("Username or Password not inserted.");
				}

				loggedUser = new Employee(username, password);
				Message loginMessage = new Message(loggedUser,"",Functions.login);

				// Send the login Message to the server
				// The server returns another message
				Message returnMessage = new Client().send(loginMessage);

				// Check the called Function from the server
				// If It is "done" -> then set the loggedUser true
				if(returnMessage.getCalledFunction() == Functions.done) {
					userIsLogged = true;
					loggedUser = (Employee) returnMessage.getObj();
					System.out.println("\n>> Logged in correctly\n");
				} else {
					userIsLogged = false;
					System.out.println("\n>> Wrong username or password\n");
				}

				System.out.println("Press [enter] to continue...");
				scanner.nextLine();

			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Press [enter] to continue...");
				scanner.nextLine();
			}
		} while (!userIsLogged);

		// Different menu interface for each king of job (Functionary, manager, admin)
		switch (loggedUser.getJob().toLowerCase()){
		case "functionary":
			menuFunctionary();
			break;
		case "manager":
			menuManager();
			break;
		case "admin":
			menuAdmin();
			break;
		default:
			System.out.println("\nERROR: Unknown user!!!\n");
			break;
		}

		System.out.println("\n\nLogged out, see you soon!");
	}

	private static void menuFunctionary (){
		// TODO Functionary Menu
		boolean logout = false;
		int userChoice;

		do {
			do {
				try {

					Console.OutputLN("\n=========> MAIN MENU <=========");
					Console.Output(""
							+ "1) New employee\n"
							+ "2) Update employee\n"
							+ "3) Logout\n"
							+ "Choice: ");

					userChoice = Console.IntInput(null);

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
					Console.Enter("Press [enter] to continue...");
					userChoice = 0;
				}

			} while (userChoice < 1 || userChoice > 6);

			System.out.println();

			switch (userChoice) {
			case 1:
				replaceProduct();

				break;

			case 2:
				// Add Wine
				addWine();
				break;

			case 3:
				// Show Wine List
				Main.printWineList(Main.wineList);
				break;

			case 4:
				// Show Customer List
				System.out.println("\n-->Printing Customer List <--");
				int index = 0;
				for (Person person : Main.userList) {
					if (person instanceof Customer) {
						System.out.print(index++ + ") ");
						System.out.print("Name: " + person.getName());
						System.out.println("\tSurname: " + person.getSurname());
					}
				}
				break;

			case 5:
				// Ship Order
				shipOrder();
				break;

			case 6:
				logout = true;
				break;

			default:
				System.out.println("Error! User Choice not valid.");
				break;
			}

		} while (!logout);
	}

	private static void menuManager (){
		// TODO Manager Menu
	}

	private static void menuAdmin (){
		// TODO Admin Menu
	}
	
}
