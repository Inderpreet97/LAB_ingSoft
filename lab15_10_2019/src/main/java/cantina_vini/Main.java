package cantina_vini;

import java.awt.List;
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		addUsers();
		addWines();
		
		login();
		System.out.println(loggedUser.getName());
		
		// loggedUser.searchWine();

		if (loggedUser instanceof Customer) {
			Customer temp = (Customer) loggedUser;
			loggedUser = temp;
			((Customer) loggedUser).buy();
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
			String username = "beppe";
			String password = "beppe123";
			
			for (Person person : userList) {
				if (person.getUsername() == username && person.getPassword() == password) {
					// loging correct
					loggedUser = person;
					System.out.println("Logged");
					userIsLogged = true;
					break;
				} else {
					System.out.println("Wrong username or password");
				}
			}
		}
	}
	public static void registerCustomer(Person person) {
		userList.add(person);
	}
	
	public static int getPositionOfUser() {
		// return the index of logged user in userList, else return -1
		int index = 0;
		for (Person p : userList) {
			if (p.getName() == loggedUser.getName() && p.getPassword() == loggedUser.getPassword()) {
				return index;
			}
			index++;
		}
		return -1;
	}
	
	public static void printWineList(ArrayList<Wine> wineList) {
		
		int index = 1;
		for (Wine wine : wineList) {
			System.out.println(index + "- "
								+ wine.getName() 
								+ " - YEAR: " + wine.getYear() + " - " 
								+ wine.getDescription()
								+ " - AMOUNT: " + wine.getQuantity()
								+ " - PRICE: " + wine.getPrice());
			index++;
		}
	}
	
	public static int getIndexOfWineByName(Wine wine) {
		// Return the index of a wine in the Main.wineList, if no match the function returns -1
		int index = 0;
		for (Wine w : wineList) {
			if (w.getName().equals(wine.getName())) { return index;	}
			index++;
		}
		return -1;
	}

}
