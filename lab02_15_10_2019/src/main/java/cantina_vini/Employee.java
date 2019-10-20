package cantina_vini;

public class Employee extends Person{
	
	// Constructors
	public Employee() {}	
	public Employee(String username, String name, String surname, String password) {
		super(username, name, surname, password);
	}
	
	// Functions
	public void replaceProduct() {
		
		/*TODO: Ask the name, year, quantity for the wine to add*/
		Wine wineToAdd = new Wine();
		
		// Check if a customer is waiting for that wine
		for(int index = 0; index < Main.pendingRequestForEmployee.size(); index++){
			
			Request request = Main.pendingRequestForEmployee.get(index);
			
			if(request.wine.getName().equals(wineToAdd.getName()) && request.wine.getYear() == wineToAdd.getYear()) {
				
				if (request.customer != null) {
					// If customer is not null, it means that a customer made the request and he's waiting for notification
					Notification notification = new Notification(request.customer, request.wine);
					Main.pendingNotificationForCustomer.add(notification);
				}
				
				// Remove the Wine Request from Request List
				Main.pendingRequestForEmployee.remove(index);
				index--;
			}
		}
	}
	
	public void Menu() {
		// Visulizza le pending Request
		for(Request req : Main.pendingRequestForEmployee) {
			if(req.customer != null) {
				System.out.print("REQUEST: A customer has requested " + req.quantity + " bottles ");
				System.out.println("of " + req.wine.getName() + ", " + req.wine.getYear());
			} else {
				System.out.print("System: There are no more bottles of " + req.wine.getName() + ", " + req.wine.getYear());
			}
		}
	
		boolean logout = false;
		int userChoice;
		
		do {
			do {
				try {
					
					System.out.println("\n=========> MAIN MENU <=========");
					System.out.println("1) Aggiungi Utente\n2) Utenti Attivi\n3) Chat con utente");
					System.out.print("4) Modifica Profilo\n5) Logout\nScelta: ");
					
					userChoice = Main.scanner.nextInt();
					Main.scanner.nextLine();
					
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
					System.out.println("Press [enter] to continue...");
					Main.scanner.nextLine();
					userChoice = 0;
				}
				
			} while (userChoice < 1 || userChoice > 5);
			
			System.out.println();
			
			switch (userChoice) {
			case 1:
				// Do something
				
				break;
			case 2:
				// Do something
				
				break;
			case 3:
				// Do something
				
				break;
			case 4:
				// Do something
				
				break;
			case 5:
				logout = true;
				break;

			default:
				System.out.println("Error! User Choice not valid.");
				break;
			}

		} while (!logout);
	}
}
