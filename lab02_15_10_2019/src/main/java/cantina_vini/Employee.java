package cantina_vini;

public class Employee extends Person {

	// Constructors
	public Employee() {
	}

	public Employee(String username, String name, String surname, String password) {
		super(username, name, surname, password);
	}

	// Functions
	public void replaceProduct() {

		// TODO add Try Catch in case of letters input when numbers are needed
		System.out.println("--> Add Wine <--");

		System.out.print("Name: ");
		String name = Main.scanner.nextLine();

		System.out.print("Year: ");
		int year = Main.scanner.nextInt();
		Main.scanner.nextLine();

		System.out.print("Description: ");
		String description = Main.scanner.nextLine();

		System.out.print("Vine: ");
		String vine = Main.scanner.nextLine();

		System.out.print("Quantity(N. Bottles): ");
		int quantity = Main.scanner.nextInt();
		Main.scanner.nextLine();

		System.out.print("Price per bottle: ");
		double price = Main.scanner.nextDouble();
		Main.scanner.nextLine();

		// Wine wineToAdd = new Wine(name, year, description, vine, quantity, price);

		/*
		 * TODO invece di inserire un nuovo vino, aggiorna la quantit� del vino
		 * esistente OPPURE se il vino non esiste devi crearlo (magari due metodi,
		 * addNewWine() e replaceProduct())
		 */
		
		// Get the global index of the wine to add
		int globalIndex = Main.getIndexOfWineByNameAndYear(name, year);
		
		if (globalIndex != -1) {
			// Wine to add found in the global list
			Main.wineList.get(globalIndex).change(quantity);
		} else{
			// Wine not found in the global list
			System.out.println("Wine not found");
		}

		System.out.println("\n>>" + quantity + " bottles of " + name + " added to the Shop");

		// Check if a customer is waiting for that wine
		for (int index = 0; index < Main.pendingRequestForEmployee.size(); index++) {

			Request request = Main.pendingRequestForEmployee.get(index);

			if (request.wine.getName().equals(name) && request.wine.getYear() == year) {

				if (request.customer != null) {
					// If customer is not null, it means that a customer made the request and he's
					// waiting for notification
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
		for (Request req : Main.pendingRequestForEmployee) {
			if (req.customer != null) {
				System.out.print("REQUEST: A customer has requested " + req.quantity + " bottles ");
				System.out.println("of " + req.wine.getName() + ", " + req.wine.getYear());
			} else {
				System.out.print(
						"System: There are no more bottles of " + req.wine.getName() + ", " + req.wine.getYear());
			}
		}

		boolean logout = false;
		int userChoice;

		do {
			do {
				try {

					System.out.println("\n=========> MAIN MENU <=========");
					System.out.println("1) Replace Product\n2) Show Wine List\n3) Show Customer List");
					System.out.print("4) Show Notification List\n5) Logout\nChoice: ");

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
				replaceProduct();

				break;
			case 2:
				// Show Wine List
				Main.printWineList(Main.wineList);
				break;

			case 3:
				// Show Customer List
				System.out.println("\n-->Printing Customer List <--");
				int index = 0;
				for (Person person : Main.userList) {
					if (person instanceof Customer) {
						System.out.print(index++ + ") ");
						System.out.print("Name: " + person.getName());
						System.out.println("\tSurname: " + person.getSurname());
					}
				}
				break;

			case 4:
				// Show Customer Notification List
				System.out.println("\n-->Printing Customer Notification List <--");
				index = 0;
				for (Notification notif : Main.pendingNotificationForCustomer) {
					System.out.print(index + ") ");
					System.out.print("Customer Name: " + notif.customer.getName());
					System.out.print("\tCustomer Surname: " + notif.customer.getSurname());
					System.out.print("\tWine Name: " + notif.wine.getName());
					System.out.println("\tWine Year: " + notif.wine.getYear());
				}
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
