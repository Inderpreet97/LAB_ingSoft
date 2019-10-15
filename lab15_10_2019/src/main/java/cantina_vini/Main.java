package cantina_vini;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public static Scanner scanner;
	
	private static Person loggedUser = new Person();
	private static boolean userIsLogged = false;
	
	private static ArrayList<Person> userList = new ArrayList<Person>();
	private static ArrayList<Wine> wineList = new ArrayList<Wine>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Customer defaultCustomer1 = new Customer("beppe", "Giuseppe", "Urbano", "beppe123");
		Customer defaultCustomer2 = new Customer("Mario", "Mario", "Rossi", "mario123");
		registerCustomer(defaultCustomer1);
		registerCustomer(defaultCustomer2);
		
		wineList.add(new Wine("Lambrusco", 1980, "Vino rosso", "Callmewine"));
		wineList.add(new Wine("Prosecco", 1975, "Vino biacno", "Cantina Colli Euganei"));
		
		
		
		login();
		
		

	}
	
	public static void login() {
		while(!userIsLogged) {
			String username = scanner.nextLine();
			String password = scanner.nextLine();
			
			for (Person person : userList) {
				if (person.getUsername() == username && person.getPassword() == password) {
					// loging correct
					loggedUser = person;
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
	

}
