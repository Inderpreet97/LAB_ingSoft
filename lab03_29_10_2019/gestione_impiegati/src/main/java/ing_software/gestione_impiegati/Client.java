package ing_software.gestione_impiegati;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

import ing_software.gestione_impiegati.EasyConsole.Console;
import ing_software.gestione_impiegati.Menu.MenuAdmin;
import ing_software.gestione_impiegati.Menu.MenuFunctionary;
import ing_software.gestione_impiegati.Menu.MenuManager;

public class Client {
	
	// Changing client as a static class
	
	public static class ClientManager {
		static Scanner scanner = new Scanner(System.in);

		// Logged user variables
		public static Employee loggedUser;

		private static final int SPORT = 4444;
		private static final String SHOST = "localhost";

		public static Message send(Message message) {

			// This functions send a message to the server and returns the message received from the server

			try {
				Socket client = new Socket(SHOST, SPORT);

				ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
				ObjectInputStream is = new ObjectInputStream(client.getInputStream());
				Message returnMessage;

				while (true) {
					// Sends messages until it receives an �end� message

					os.writeObject(message);
					os.flush();

					Object o = is.readObject();

					if ((o != null) && (o instanceof Message)) {
						returnMessage = (Message) o;
						break;
					}
				}

				client.close();
				return returnMessage;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		public static boolean checkMessage(Message message) {

			// This functions checks if a message is an error message (return false) or not

			if (message.getCalledFunction() == Functions.done) {
				return true;
			}

			// Whatever the error, print the error message and retunr false
			Console.Output(message.getContent());
			return false;

		}

		public static void main(final String[] v) {

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
					Message loginMessage = new Message(loggedUser, "", Functions.login);

					// Send the login Message to the server
					// The server returns another message
					Message returnMessage = send(loginMessage);

					// Check the called Function from the server
					// If It is "done" -> then set the loggedUser true
					if (returnMessage.getCalledFunction() == Functions.done) {
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

			switch (loggedUser.getJob().toLowerCase()) {
			case "functionary":		MenuFunctionary.Run();									break;
			case "manager":			MenuManager.Run();										break;
			case "admin":			MenuAdmin.Run();										break;
			default:				System.out.println("\nERROR: Unknown user!!!\n");		break;
			}

			System.out.println("\n\nLogged out, see you soon!");
		}
	}
	
}
