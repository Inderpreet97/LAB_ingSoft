package cantina_vini;

import java.util.ArrayList;
import java.util.Scanner;

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

		addUsers();
		addWines();

		login();

		if (loggedUser instanceof Customer) { // If logged user is a customer
			((Customer) loggedUser).Menu();
		} else if (loggedUser instanceof Employee) { // If logged user is an employee
			((Employee) loggedUser).Menu();
		}

		System.out.println("\n\nLogged out, see you soon!");

	}
	
	public static void writeWineListOnFile() {
		// TODO write wine list on 
		//First Employee
        JSONObject employeeDetails = new JSONObject();
        employeeDetails.put("firstName", "Lokesh");
        employeeDetails.put("lastName", "Gupta");
        employeeDetails.put("website", "howtodoinjava.com");
         
        JSONObject employeeObject = new JSONObject();
        employeeObject.put("employee", employeeDetails);
         
        //Second Employee
        JSONObject employeeDetails2 = new JSONObject();
        employeeDetails2.put("firstName", "Brian");
        employeeDetails2.put("lastName", "Schultz");
        employeeDetails2.put("website", "example.com");
         
        JSONObject employeeObject2 = new JSONObject();
        employeeObject2.put("employee", employeeDetails2);
         
        //Add employees to list
        JSONArray employeeList = new JSONArray();
        employeeList.add(employeeObject);
        employeeList.add(employeeObject2);
         
        //Write JSON file
        try (FileWriter file = new FileWriter("employees.json")) {
 
            file.write(employeeList.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void readWineListFromFile() {
		// TODO read Wine List From File
		//JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("employees.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);
             
            //Iterate over employee array
            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	// TODO metod for reading JSON from file
	private static void parseEmployeeObject(JSONObject employee)
    {
        //Get employee object within list
        JSONObject employeeObject = (JSONObject) employee.get("employee");
         
        //Get employee first name
        String firstName = (String) employeeObject.get("firstName");   
        System.out.println(firstName);
         
        //Get employee last name
        String lastName = (String) employeeObject.get("lastName"); 
        System.out.println(lastName);
         
        //Get employee website name
        String website = (String) employeeObject.get("website");   
        System.out.println(website);
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
