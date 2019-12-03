package ing_software.gestione_impiegati;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import ing_software.gestione_impiegati.EasyConsole.Console;
import ing_software.gestione_impiegati.Menu.MenuAdmin;
import ing_software.gestione_impiegati.Menu.MenuFunctionary;
import ing_software.gestione_impiegati.Menu.MenuManager;

public class Client {

	// Changing client as a static class

	public static class ClientManager {

		// Logged user variables
		public static Employee loggedUser;

		private static final int SPORT = 4444;
		private static final String SHOST = "localhost";

		private static Socket client = null;
		private static ObjectOutputStream outputStream = null;
		private static ObjectInputStream inputStream = null;

		// Functions on Message
		public static Message send(Message message) {

			// This functions send a message to the server and returns the message received from the server

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

		/**
		 * 
		 * Connect to the server, set all parameters to null in the catch block if exception occurred
		 *
		 */
		public static void connect() throws UnknownHostException, IOException {
			client = new Socket(SHOST, SPORT);
			outputStream = new ObjectOutputStream(client.getOutputStream());
			inputStream = null;

		}

		/**
		 * Disconnect the client from the server
		 */
		public static void disconnect() {
			try {
				client.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		/**
		 * Login
		 */
		public static void login() {
			
			Boolean userIsLogged = false;
			String username;
			String password;

			do {
				Console.OutputLN("EMPLOYEE MANAGER");
				Console.OutputLN("LOGIN");

				try {
					
					username = Console.Input("Username: ");
					password = Console.Input("Password: ");

					if (username.isEmpty() || password.isEmpty()) { throw new Exception("Username or Password not inserted.");}
					
					loggedUser = new Employee(username, password);
					Message loginMessage = new Message(loggedUser, "", Functions.login);

					connect();
					
					/**
					 * Send the login Message to the server, and receive a message from it
					 */
					Message returnMessage = send(loginMessage);

					// Check the called Function from the server
					// If It is "done" -> then set the loggedUser true
					if (returnMessage.getCalledFunction() == Functions.done) {
						userIsLogged = true;
						loggedUser = (Employee) returnMessage.getObj();
						Console.Output("\n>> Logged in correctly\n");
						
					} else {
						userIsLogged = false;
						logout();
						Console.Output("\n>> Wrong username or password\n");
					}

					Console.EnterDefault();

				} catch (IOException ex) {
					disconnect();
					ex.printStackTrace();
					
				} catch (Exception ex) {
					Console.OutputLN("");
					Console.OutputLN(ex.getMessage());
					Console.EnterDefault();
				}
				
			} while (!userIsLogged);

			switch (loggedUser.getJob()) {
			case functionary :
				MenuFunctionary.Run();
				break;
			case manager:
				MenuManager.Run();
				break;
			case admin:
				MenuAdmin.Run();
				break;
			default:
				System.out.println("\nERROR: This use does not have a menu !!!\n");
				break;
			}

		}

		/**
		 * Logout --> send a message to the server calling the logout function
		 * Set the loggedUser in the Client as null and disconnect from the Server
		 */
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
