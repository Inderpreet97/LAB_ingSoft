package cantina_vini;

public class Employee extends Person {

	// Constructors
	public Employee() {
	}

	public Employee(String username, String name, String surname, String password) {
		super(username, name, surname, password);
	}

	
	public void addWine() {
		try {
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

			// Get the global index of the wine to add
			int globalIndex = Main.getIndexOfWineByNameAndYear(name, year);

			if (globalIndex != -1) {
				// Wine not found in the global list, It Can Be Added
				Main.wineList.add(new Wine(name, year, description, vine, quantity, price));
			} else {
				// Wine already exits in the Global List
				System.out.println("\n>> Wine already in Storage");
			}

			System.out.println("\n>>" + quantity + " bottles of " + name + " added to the Shop");

		} catch (Exception ex) {
			Main.scanner.nextLine();
			System.out.println("\nERROR >> " + ex.getMessage());
		}
	}

	public void shipOrder() {
		for (Purchase purchase : Main.purchaseList) {
			if (!purchase.isShipped()) {
				System.out.print("Wine: " + purchase.getWine().getName());
				System.out.print("\tYear: " + purchase.getWine().getYear());
				System.out.println("\tQuantity: " + purchase.getQuantity());

				System.out.print("Ship this order?\n1)Yes\n2)No\nChoice: ");
				int choice = 0;
				do {
					try {
						choice = Main.scanner.nextInt();
						Main.scanner.nextLine();
					} catch (Exception e) {
						e.printStackTrace();
						choice = 0;
					}
				} while (choice < 1 || choice > 2);

				if (choice == 1) {
					purchase.setShipped(true);
					System.out.println("\nOrder Shipped!\n");
				}
			}
		}
	}

	public void replaceProduct() {

		try {
			System.out.println("--> Replace Wine <--");

			System.out.print("Name: ");
			String name = Main.scanner.nextLine();

			System.out.print("Year: ");
			int year = Main.scanner.nextInt();
			Main.scanner.nextLine();

			System.out.print("Quantity(N. Bottles): ");
			int quantity = Main.scanner.nextInt();
			Main.scanner.nextLine();

			// Get the global index of the wine to add
			int globalIndex = Main.getIndexOfWineByNameAndYear(name, year);

			if (globalIndex != -1) {
				// Wine to add found in the global list
				Main.wineList.get(globalIndex).change(quantity);
			} else {
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
		} catch (Exception ex) {
			Main.scanner.nextLine();
			System.out.println("\nERROR >> " + ex.getMessage());
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

		// Visulizza le pending shippings
		for (Purchase purchase : Main.purchaseList) {
			if (!purchase.isShipped()) {
				System.out.print("NOTIFICATION: shipping is pending!");
				System.out.print("Wine: " + purchase.getWine().getName());
				System.out.print("\tYear: " + purchase.getWine().getYear());
				System.out.println("\tQuantity: " + purchase.getQuantity());
			}
		}

		boolean logout = false;
		int userChoice;

		do {
			do {
				try {

					System.out.println("\n=========> MAIN MENU <=========");
					System.out.println("1) Replace Product\n2) Add Wine\n3) Show Wine List\n4) Show Customer List");
					System.out.print("5) Ship Order\n6) Logout\nChoice: ");

					userChoice = Main.scanner.nextInt();
					Main.scanner.nextLine();

				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
					System.out.println("Press [enter] to continue...");
					Main.scanner.nextLine();
					userChoice = 0;
				}

			} while (userChoice < 1 || userChoice > 6);

			System.out.println();

			switch (userChoice) {
			case 1:
				replaceProduct();

				break;

			case 2:
				// Add Wine
				addWine();
				break;

			case 3:
				// Show Wine List
				Main.printWineList(Main.wineList);
				break;

			case 4:
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

			case 5:
				// Ship Order
				shipOrder();
				break;

			case 6:
				logout = true;
				break;

			default:
				System.out.println("Error! User Choice not valid.");
				break;
			}

		} while (!logout);
	}
}
