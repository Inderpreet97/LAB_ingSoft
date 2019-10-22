package cantina_vini;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
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

		readUserListFromFile();
		readWineListFromFile();
		readNotificationsFromFile();
		readRequestsFromFile();
		
		addWines();

		login();

		if (loggedUser instanceof Customer) { // If logged user is a customer
			((Customer) loggedUser).Menu();
		} else if (loggedUser instanceof Employee) { // If logged user is an employee
			((Employee) loggedUser).Menu();
		}
		
		writeUserListOnFile();
		writeWineListOnFile();
		writeNotificationsOnFile();
		writeRequestsOnFile();
		
		System.out.println("\n\nLogged out, see you soon!");

	}
	
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
         
        //Write JSON file
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
	
	private static void writeWineListOnFile() {
		// TODO scrittura vini su file 
	}
	
	private static void readWineListFromFile() {
		// TODO lettura vini su file
	}
	
	private static void writeNotificationsOnFile() {
		// TODO scrittura Notifiche su file 
	}
	
	private static void readNotificationsFromFile() {
		// TODO lettura Notifiche su file
	}
	
	private static void writeRequestsOnFile() {
		// TODO scrittura Richieste su file 
	}
	
	private static void readRequestsFromFile() {
		// TODO lettura Richieste su file
	}
	
	public static void addUsers() {
		// Add default users
		Customer defaultCustomer1 = new Customer("beppe", "Giuseppe", "Urbano", "beppe123");
		Customer defaultCustomer2 = new Customer("Mario", "Mario", "Rossi", "mario123");
		Employee defaultAdmin = new Employee("Gianni", "Gianni", "Morandi", "gianni123");
		registerCustomer(defaultCustomer1);
		registerCustomer(defaultCustomer2);
		registerCustomer(defaultAdmin);
	}

	public static void addWines() {
		// Add default wines
		wineList.add(new Wine("Lambrusco", 1980, "Vino rosso", "Callmewine", 10, 2000));
		wineList.add(new Wine("Prosecco", 1975, "Vino bianco", "Cantina Colli Euganei", 10, 1500));
	}

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
					System.out.println("Logged");
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

	public static void registerCustomer(Person person) {
		// Add a person to a list
		userList.add(person);
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
