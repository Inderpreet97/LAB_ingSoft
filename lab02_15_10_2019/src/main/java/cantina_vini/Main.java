package cantina_vini;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

	// Scanner to get input
	public static Scanner scanner = new Scanner(System.in);

	// Logged user variables
	private static Person loggedUser = new Person();
	private static boolean userIsLogged = false;

	// Global variables
	public static ArrayList<Person> userList = new ArrayList<Person>();
	public static ArrayList<Wine> wineList = new ArrayList<Wine>();
	public static ArrayList<Purchase> purchaseList = new ArrayList<Purchase>();

	// Pending Notification/Request list
	public static ArrayList<Request> pendingRequestForEmployee = new ArrayList<Request>();
	public static ArrayList<Notification> pendingNotificationForCustomer = new ArrayList<Notification>();

	public static void main(String[] args) {

		//TODO registra e purchase listJSON

		readPurchaseListFromFile();
		readUserListFromFile();
		readWineListFromFile();
		readNotificationsFromFile();
		readRequestsFromFile();

		// LOGIN | REGISTER
		int userChoice = 0;
		userIsLogged = false;
		String username;
		String password;
		
		do {
			do {
				try {
					System.out.println("=========> Wine Shop <=========");
					System.out.print("1) Login\n2) Register\nChoice: ");
					userChoice = scanner.nextInt();
					scanner.nextLine();
				} catch (Exception ex) {
					scanner.nextLine();
					userChoice = 0;
				}
			} while (userChoice < 1 || userChoice > 2);

			if (userChoice == 1) {
				try {
					System.out.println("\n=========> LOGIN <=========");

					System.out.print("Username: ");
					username = scanner.nextLine();

					System.out.print("Password: ");
					password = scanner.nextLine();

					if (username.isEmpty() || password.isEmpty()) {
						throw new Exception("Username or Password not inserted.");
					}

					for (Person person : userList) {
						if (person.getUsername().equals(username) && person.getPassword().equals(password)) {
							// loging correct
							loggedUser = person;
							userIsLogged = true;
							System.out.println("Logged in correctly.");
							break;
						}
					}
					
					if(!userIsLogged) {
						System.out.println("\n>> Wrong username or password\n");
					}
	
					System.out.println("Press [enter] to continue...");
					scanner.nextLine();
					
				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("Press [enter] to continue...");
					scanner.nextLine();
				}

			} else {
				try {
					System.out.println("\n=========> REGISTER ACCOUNT <=========");

					System.out.print("Username: ");
					username = scanner.nextLine();

					System.out.print("Password: ");
					password = scanner.nextLine();
					
					System.out.print("Name: ");
					String name = scanner.nextLine();
					
					System.out.print("Surname: ");
					String surname = scanner.nextLine();

					if (username.isEmpty() || password.isEmpty()) {
						
					}
					
					for (Person person : userList) {
						if (person.getUsername().equals(username)) {
							throw new Exception("Username is taken, chose another username.");
						}
					}
					
					userList.add(new Customer(username, name, surname, password));
					
					System.out.println("User registered correctly.");
					System.out.println("Press [enter] to continue...");
					scanner.nextLine();

				} catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("Press [enter] to continue...");
					scanner.nextLine();
				}
			}
		} while (!userIsLogged);


		if (loggedUser instanceof Customer) { // If logged user is a customer
			((Customer) loggedUser).Menu();
		} else if (loggedUser instanceof Employee) { // If logged user is an employee
			((Employee) loggedUser).Menu();
		}

		writePurchaseListFromFile();
		writeUserListOnFile();
		writeWineListOnFile();
		writeNotificationsOnFile();
		writeRequestsOnFile();

		System.out.println("\n\nLogged out, see you soon!");

	}

	// Purchase List JSON
	private static void readPurchaseListFromFile(){
		JSONParser parser = new JSONParser();

		try (Reader reader = new FileReader("purchaseList.json")) {
			JSONArray purchaseListJSON = (JSONArray) parser.parse(reader);

			for (int i=0; i<purchaseListJSON.size(); i++) {
				JSONObject purchase = (JSONObject) purchaseListJSON.get(i);
				
				JSONObject customerJSON = (JSONObject) purchase.get("customer");
				JSONObject wineJSON = (JSONObject) purchase.get("wine");

				// Create the customer object from json object
				Customer customer = new Customer(
						(String) customerJSON.get("username"),
						(String) customerJSON.get("name"),
						(String) customerJSON.get("surname"),
						(String) customerJSON.get("password"));

				// Create the wine object from json object
				Wine wine = new Wine(
						(String) wineJSON.get("name"),
						Integer.valueOf(wineJSON.get("year").toString()),
						(String) wineJSON.get("description"),
						(String) wineJSON.get("vine"),
						Integer.valueOf(wineJSON.get("quantity").toString()),
						(double) wineJSON.get("price"));
				
				double quantity = Double.valueOf(purchase.get("quantity").toString());
				double amount = Double.valueOf(purchase.get("amount").toString());
				LocalDateTime date = LocalDateTime.parse((CharSequence) purchase.get("date"));
				Boolean shipped = Boolean.valueOf(purchase.get("shipped").toString());

				// Create the notification object with customer and wine -> add to the notification global list
				purchaseList.add(new Purchase(customer, wine, quantity, amount, date, shipped));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@SuppressWarnings("unchecked")
	private static void writePurchaseListFromFile(){
		// Create JSON string for notification list
		JSONArray purchaseListJSON = new JSONArray();

		for (Purchase purchase : Main.purchaseList) {

			// Write the customer JSON
			JSONObject purchaseDetails = new JSONObject();

			JSONObject customerDetails = new JSONObject();
			customerDetails.put("name", purchase.getCustomer().getName());
			customerDetails.put("surname", purchase.getCustomer().getSurname());
			customerDetails.put("username", purchase.getCustomer().getUsername());
			customerDetails.put("password", purchase.getCustomer().getPassword());
			customerDetails.put("account", "customer");
			// The account key value can only be "customer", because only a customer is in purchase list

			// Write the wine JSON
			JSONObject wineDetails = new JSONObject();
			wineDetails.put("name", purchase.getWine().getName());
			wineDetails.put("year", purchase.getWine().getYear());
			wineDetails.put("description", purchase.getWine().getDescription());
			wineDetails.put("vine", purchase.getWine().getVine());
			wineDetails.put("quantity", purchase.getWine().getQuantity());
			wineDetails.put("price", purchase.getWine().getPrice());

			purchaseDetails.put("quantity", purchase.getQuantity());
			purchaseDetails.put("amount", purchase.getAmount());
			purchaseDetails.put("date", purchase.getDate().toString());
			purchaseDetails.put("shipped", purchase.isShipped());
			purchaseDetails.put("customer", customerDetails);
			purchaseDetails.put("wine", wineDetails);

			purchaseListJSON.add(purchaseDetails);

		}

		// Write JSON file
		try (FileWriter file = new FileWriter("purchaseList.json")) {
			file.write(purchaseListJSON.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Users JSON
	@SuppressWarnings("unchecked")
	private static void writeUserListOnFile() {
		// Create JSON string for user list
		JSONArray userListJSON = new JSONArray();

		for(Person p : Main.userList) {
			JSONObject userDetails = new JSONObject();
			userDetails.put("name", p.getName());
			userDetails.put("surname", p.getSurname());
			userDetails.put("username", p.getUsername());
			userDetails.put("password", p.getPassword());

			String account = (p instanceof Employee)? "employee" : "customer";
			userDetails.put("account", account);

			userListJSON.add(userDetails);
		}

		// Write JSON file
		try (FileWriter file = new FileWriter("users.json")) {
			file.write(userListJSON.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void readUserListFromFile() {
		//JSON parser object to parse read file
		JSONParser parser = new JSONParser();

		try (Reader reader = new FileReader("users.json")) {

			JSONArray userListJSON = (JSONArray) parser.parse(reader);

			for (int i = 0 ; i < userListJSON.size(); i++) {
				JSONObject user = (JSONObject) userListJSON.get(i);
				String name = (String) user.get("name");
				String surname = (String) user.get("surname");
				String username = (String) user.get("username");
				String password = (String) user.get("password");
				String account = (String) user.get("account");

				if(account.equals("employee")) {
					Main.userList.add(new Employee(username, name, surname, password));
				} else {
					Main.userList.add(new Customer(username, name, surname, password));
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// Wines JSON
	@SuppressWarnings("unchecked")
	private static void writeWineListOnFile() {
		// Create JSON string for wine list
		JSONArray wineListJSON = new JSONArray();

		for (Wine wine: Main.wineList) {
			JSONObject wineDetails = new JSONObject();
			wineDetails.put("name", wine.getName());
			wineDetails.put("year", wine.getYear());
			wineDetails.put("description", wine.getDescription());
			wineDetails.put("vine", wine.getVine());
			wineDetails.put("quantity", wine.getQuantity());
			wineDetails.put("price", wine.getPrice());

			wineListJSON.add(wineDetails);
		}

		// Write JSON file
		try (FileWriter file = new FileWriter("wines.json")) {
			file.write(wineListJSON.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	private static void readWineListFromFile() {
		//JSON parser object to parse read file
		JSONParser parser = new JSONParser();

		try (Reader reader = new FileReader("wines.json")) {

			JSONArray wineListJSON = (JSONArray) parser.parse(reader);

			for (int i = 0 ; i < wineListJSON.size(); i++) {
				JSONObject wine = (JSONObject) wineListJSON.get(i);
				String name = (String) wine.get("name");
				int year = Integer.valueOf(wine.get("year").toString());
				String description = (String) wine.get("description");
				String vine = (String) wine.get("vine");
				int quantity = Integer.valueOf(wine.get("quantity").toString());
				double price = (double) wine.get("price");

				// Add the JSON object to the wine list
				Main.wineList.add(new Wine(name, year, description, vine, quantity, price));

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	// Notifications JSON
	@SuppressWarnings("unchecked")
	private static void writeNotificationsOnFile() {
		// Create JSON string for notification list
		JSONArray notificationListJSON = new JSONArray();

		for (Notification notification : Main.pendingNotificationForCustomer) {

			// Write the customer JSON
			JSONObject notificationDetails = new JSONObject();

			JSONObject customerDetails = new JSONObject();
			customerDetails.put("name", notification.customer.getName());
			customerDetails.put("surname", notification.customer.getSurname());
			customerDetails.put("username", notification.customer.getUsername());
			customerDetails.put("password", notification.customer.getPassword());
			customerDetails.put("account", "customer");
			// The account key value can only be "customer", because only a customer is in notification list

			// Write the wine JSON
			JSONObject wineDetails = new JSONObject();
			wineDetails.put("name", notification.wine.getName());
			wineDetails.put("year", notification.wine.getYear());
			wineDetails.put("description", notification.wine.getDescription());
			wineDetails.put("vine", notification.wine.getVine());
			wineDetails.put("quantity", notification.wine.getQuantity());
			wineDetails.put("price", notification.wine.getPrice());

			notificationDetails.put("customer", customerDetails);
			notificationDetails.put("wine", wineDetails);

			notificationListJSON.add(notificationDetails);

		}

		// Write JSON file
		try (FileWriter file = new FileWriter("notifications.json")) {
			file.write(notificationListJSON.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void readNotificationsFromFile() {

		JSONParser parser = new JSONParser();

		try (Reader reader = new FileReader("notifications.json")) {
			JSONArray notificationListJSON = (JSONArray) parser.parse(reader);

			for (int i=0; i<notificationListJSON.size(); i++) {
				JSONObject notification = (JSONObject) notificationListJSON.get(i);
				JSONObject customerJSON = (JSONObject) notification.get("customer");
				JSONObject wineJSON = (JSONObject) notification.get("wine");

				// Create the customer object from json object
				Customer customer = new Customer(
						(String) customerJSON.get("username"),
						(String) customerJSON.get("name"),
						(String) customerJSON.get("surname"),
						(String) customerJSON.get("password"));

				// Create the wine object from json object
				Wine wine = new Wine(
						(String) wineJSON.get("name"),
						Integer.valueOf(wineJSON.get("year").toString()),
						(String) wineJSON.get("description"),
						(String) wineJSON.get("vine"),
						Integer.valueOf(wineJSON.get("quantity").toString()),
						(double) wineJSON.get("price"));

				// Create the notification object with customer and wine -> add to the notification global list
				Main.pendingNotificationForCustomer.add(new Notification(customer, wine));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// Requests JSON
	@SuppressWarnings("unchecked")
	private static void writeRequestsOnFile() {
		// Create JSON string for request list
		JSONArray requestListJSON = new JSONArray();

		for (Request request : Main.pendingRequestForEmployee) {

			// Write the customer JSON
			JSONObject requestDetails = new JSONObject();

			JSONObject customerDetails = new JSONObject();
			customerDetails.put("name", request.customer.getName());
			customerDetails.put("surname", request.customer.getSurname());
			customerDetails.put("username", request.customer.getUsername());
			customerDetails.put("password", request.customer.getPassword());
			customerDetails.put("account", "customer");
			// The account key value can only be "customer", because only a customer is in request list

			// Write the wine JSON
			JSONObject wineDetails = new JSONObject();
			wineDetails.put("name", request.wine.getName());
			wineDetails.put("year", request.wine.getYear());
			wineDetails.put("description", request.wine.getDescription());
			wineDetails.put("vine", request.wine.getVine());
			wineDetails.put("quantity", request.wine.getQuantity());
			wineDetails.put("price", request.wine.getPrice());

			requestDetails.put("customer", customerDetails);
			requestDetails.put("wine", wineDetails);
			requestDetails.put("quantity", request.quantity);

			requestListJSON.add(requestDetails);

		}

		// Write JSON file
		try (FileWriter file = new FileWriter("requests.json")) {
			file.write(requestListJSON.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void readRequestsFromFile() {

		JSONParser parser = new JSONParser();

		try (Reader reader = new FileReader("requests.json")) {
			JSONArray requestListJSON = (JSONArray) parser.parse(reader);

			for (int i=0; i<requestListJSON.size(); i++) {
				JSONObject request = (JSONObject) requestListJSON.get(i);
				JSONObject customerJSON = (JSONObject) request.get("customer");
				JSONObject wineJSON = (JSONObject) request.get("wine");

				// Create the customer object from json object
				Customer customer = new Customer(
						(String) customerJSON.get("username"),
						(String) customerJSON.get("name"),
						(String) customerJSON.get("surname"),
						(String) customerJSON.get("password"));

				// Create the wine object from json object
				Wine wine = new Wine(
						(String) wineJSON.get("name"),
						Integer.valueOf(wineJSON.get("year").toString()),
						(String) wineJSON.get("description"),
						(String) wineJSON.get("vine"),
						Integer.valueOf(wineJSON.get("quantity").toString()),
						(double) wineJSON.get("price"));

				// Read quantity
				int quantity = Integer.valueOf(request.get("quantity").toString());

				// Create the request object with customer, wine and quantity -> Add to the request global list
				Main.pendingRequestForEmployee.add(new Request(customer, wine, quantity));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


	// Login & Signing up
	public static void login() {
		
		

		while(!userIsLogged) {

			System.out.println("--> LOGIN <--");

			String username = "";
			String password = "";

			do {
				System.out.print("Username: ");
				username = Main.scanner.nextLine();
				System.out.print("Password: ");
				password = Main.scanner.nextLine();

				if(username.isEmpty() && password.isEmpty()) {
					System.out.println("\n>> Username or Password not inserted\n");
				}

			} while (username.isEmpty() && password.isEmpty());


			for (Person person : userList) {
				if (person.getUsername().equals(username) && person.getPassword().equals(password)) {
					// loging correct
					loggedUser = person;
					userIsLogged = true;
					break;
				}
			}

			if(!userIsLogged) {
				System.out.println("\n>> Wrong username or password\n");
			}
		}

		System.out.println("Welcome " + loggedUser.getName() + " " + loggedUser.getSurname());
	}



	// Print functions
	public static void printWineList(ArrayList<Wine> wineList) {

		// Print a wine list given as a parameter

		int index = 1;
		for (Wine wine : wineList) {
			System.out.println(index + "- " + wine.getName() + " - YEAR: " + wine.getYear() + " - "
					+ wine.getDescription() + " - AMOUNT: " + wine.getQuantity() + " - PRICE: " + wine.getPrice());
			index++;
		}
	}
	public static void printPendingRequestForEmployee() {
		System.out.println("Pending requests for employee");
		for (Request request : pendingRequestForEmployee) {
			System.out.println("\nCustomer: " + request.customer.getName() + " " + request.customer.getSurname());
			System.out.println("Wine: " + request.wine.getName() + " " + request.wine.getYear());
			System.out.println("Quantity: " + request.wine.getQuantity());
			System.out.println("##############################################\n");
		}
	}
	public static void printPendingsNotificationForCustomer() {
		System.out.println("Pending notifications for customer");
		for (Notification notification : pendingNotificationForCustomer) {
			System.out.println("\nCustomer: " + notification.customer.getName() + " " + notification.customer.getSurname());
			System.out.println("Wine: " + notification.wine.getName() + " " + notification.wine.getYear());
			System.out.println("##############################################\n");
		}
	}

	// Other functions
	public static int getPositionOfUser() {
		// return the index of logged user in userList, else return -1
		int index = 0;
		for (Person p : userList) {
			if (p.getName() == loggedUser.getUsername() && p.getPassword() == loggedUser.getPassword()) {
				return index;
			}
			index++;
		}
		return -1;
	}
	public static int getIndexOfWineByNameAndYear(String wineName, int wineYear) {
		// Return the index of a wine in the Main.wineList, if no match the function returns -1
		int index = 0;
		for (Wine w : wineList) {
			if (w.getName().equals(wineName) && w.getYear() == wineYear) {
				return index;
			}
			index++;
		}
		return -1;
	}

}
