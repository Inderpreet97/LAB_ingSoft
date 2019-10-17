package cantina_vini;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static Scanner scanner = new Scanner(System.in);

	private static Person loggedUser = new Person();
	private static boolean userIsLogged = false;

	// Global variables
	public static ArrayList<Person> userList = new ArrayList<Person>();
	public static ArrayList<Wine> wineList = new ArrayList<Wine>();

	public static ArrayList<Purchase> purchaseList = new ArrayList<Purchase>();
	public static ArrayList<Request> pendinRequestForEmployee = new ArrayList<Request>();
	public static ArrayList<Notification> pendingNotificationForCustomer = new ArrayList<Notification>();

	public static void main(String[] args) {

		addUsers();
		addWines();

		login();

		if (loggedUser instanceof Customer) { // If logged user is a customer
			Customer temp = (Customer) loggedUser;
			loggedUser = temp;
			((Customer) loggedUser).buy();
		} else if (loggedUser instanceof Employee) { // If logged user is an employee
			Employee temp = (Employee) loggedUser;
			loggedUser = temp;
		}

		printWineList(wineList);

	}

	public static void addUsers() {
		// Add users
		Customer defaultCustomer1 = new Customer("beppe", "Giuseppe", "Urbano", "beppe123");
		Customer defaultCustomer2 = new Customer("Mario", "Mario", "Rossi", "mario123");
		Employee defaultAdmin = new Employee("Gianni", "Gianni", "Morandi", "gianni123");
		registerCustomer(defaultCustomer1);
		registerCustomer(defaultCustomer2);
		registerCustomer(defaultAdmin);
	}

	public static void addWines() {
		// Add wines
		wineList.add(new Wine("Lambrusco", 1980, "Vino rosso", "Callmewine", 10, 2000));
		wineList.add(new Wine("Prosecco", 1975, "Vino bianco", "Cantina Colli Euganei", 10, 1500));
	}

	public static void login() {

		while(!userIsLogged) {
			String username = "Gianni";
			String password = "gianni123";
			
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
				System.out.println("Wrong username or password");
			}
		}

		System.out.println("Welcome " + loggedUser.getName() + " " + loggedUser.getSecondName());
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

	public static int getIndexOfWineByName(Wine wine) {
		// Return the index of a wine in the Main.wineList, if no match the function
		// returns -1
		int index = 0;
		for (Wine w : wineList) {
			if (w.getName().equals(wine.getName()) && w.getYear() == wine.getYear()) {
				return index;
			}
			index++;
		}
		return -1;
	}

}
