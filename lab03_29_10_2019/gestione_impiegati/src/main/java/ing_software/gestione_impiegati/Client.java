package ing_software.gestione_impiegati;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import ing_software.gestione_impiegati.EasyConsole.Console;
import ing_software.gestione_impiegati.Menu.MenuAdmin;
import ing_software.gestione_impiegati.Menu.MenuFunctionary;
import ing_software.gestione_impiegati.Menu.MenuManager;

public class Client {

	// Changing client as a static class

	public static class ClientManager {
		static Scanner scanner = new Scanner(System.in);

		/**
		 * TODO Dobbiamo dividere la creazione e la chiusura della Socket, Ora è dentro
		 * il metodo Send, ma così apriamo e chiudiamo una connessione tutte le volte
		 * che dobbiamo inivare un messaggio. Invece dobbiamo Aprire la connessione al
		 * Login, e chiuderla al Logout oppure in caso di errore.
		 * 
		 * Metodo Send (nudes) deve solo inviare messaggi, assicurandosi che la socket
		 * non sia Null.
		 */

		// Logged user variables
		public static Employee loggedUser;

		private static final int SPORT = 4444;
		private static final String SHOST = "localhost";

		private static Socket client = null;
		private static ObjectOutputStream outputStream = null;
		private static ObjectInputStream inputStream = null;

		// Functions on Message
		public static Message send(Message message) {

			// This functions send a message to the server and returns the message received
			// from the server

			try {
				if (!client.equals(null)) {
					Message returnMessage;
					while (true) {
						// Sends messages until it receives an �end� message

						outputStream.writeObject(message);
						outputStream.flush();

						if (inputStream == null) {
							inputStream = new ObjectInputStream(new BufferedInputStream(client.getInputStream()));
						}

						Object o = inputStream.readObject();

						if ((o != null) && (o instanceof Message)) {
							returnMessage = (Message) o;
							break;
						}
					}

					return returnMessage;

				} else {
					Console.Output("Client not connected to the server");
					return null;
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			}

		}

		public static boolean checkMessage(Message message) {

			// This functions checks if a message is an error message (return false) or not

			if (message.getCalledFunction() == Functions.done) {
				return true;
			}
			// Whatever the error, print the error message and return false
			Console.Output(message.getContent());
			return false;

		}

		public static void connect() throws UnknownHostException, IOException {

			// Connect to the server, set all parameters to null in the catch block if
			// exception occurred

			client = new Socket(SHOST, SPORT);
			outputStream = new ObjectOutputStream(client.getOutputStream());
			inputStream = null;

		}

		public static void disconnect() {

			// Disconnect the client from the server

			try {
				client.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public static void login() {
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

					connect();
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
						logout();
						System.out.println("\n>> Wrong username or password\n");
					}

					System.out.println("Press [enter] to continue...");
					scanner.nextLine();

				} catch (IOException ex) {
					disconnect();
					ex.printStackTrace();
				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("Press [enter] to continue...");
					scanner.nextLine();
				}
				
			} while (!userIsLogged);

			switch (loggedUser.getJob().toLowerCase()) {
			case "functionary":
				MenuFunctionary.Run();
				break;
			case "manager":
				MenuManager.Run();
				break;
			case "admin":
				MenuAdmin.Run();
				break;
			default:
				System.out.println("\nERROR: Unknown user!!!\n");
				break;
			}

		}

		public static void logout() {
			try {
				loggedUser = null;
				Message message = new Message(null, "", Functions.logout);
				send(message);			// Send a message to server and close the thread on server thread
				disconnect();			// Close socket 
				System.out.println("\n\nLogged out, see you soon!");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		public static void main(final String[] v) throws IOException {

			login();

			logout();

		}
	}

}
