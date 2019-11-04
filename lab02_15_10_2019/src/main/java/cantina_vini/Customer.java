package cantina_vini;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Customer extends Person {

	// Constructor
	public Customer() {}
	public Customer(String username, String name, String surname, String password) {
		super(username, name, surname, password);
	}

	// Functions
	public void Menu() {

		// Show pending notification for this customer
		for(int index = 0; index < Main.pendingNotificationForCustomer.size(); index++) {
			Notification notif = Main.pendingNotificationForCustomer.get(index);
			Customer c = notif.customer;
			if(c.getName().equals(this.getName()) && c.getSurname().equals(this.getSurname())) {
				// Show notification and remove it from the list
				System.out.print("NOTIFICATION: The Wine you requested has arrived ");
				System.out.println("(" + notif.wine.getName() + "," + notif.wine.getYear() +")");
				Main.pendingNotificationForCustomer.remove(index);
				index--;
			}
		}

		/* TODO: TESTARE menu customer */
		boolean logout = false;
		int userChoice;

		do {
			do {
				try {

					System.out.println("\n=========> MAIN MENU <=========");
					System.out.println("1) Buy Wine\n2) Search Wine");
					System.out.print("3) Logout\nChoice: ");

					userChoice = Main.scanner.nextInt();
					Main.scanner.nextLine();

				} catch (Exception ex) {
					Main.scanner.nextLine();
					System.out.println("Error: " + ex.getMessage());
					userChoice = 0;
				}

			} while (userChoice < 1 || userChoice > 3);

			System.out.println();

			switch (userChoice) {
			case 1:
				buy();
				break;

			case 2:
				searchWine();
				break;

			case 3:
				logout = true;
				break;

			default:
				System.out.println("Error! User Choice not valid.");
				break;
			}

		} while (!logout);
	}

	public void buy() {

		// Check if an order has to be processed (true) or not (false)
		boolean order = false;

		// List of wine
		ArrayList<Wine> wineList = searchWineAndGetList();

		// Type the index
		if(wineList.size() > 0) {
			int chosenWineIndex = -1;
			// TODO Errore con la selzione degli indici dell'array
			do {
				try {
					System.out.print("Type the the index of wine you want: ");
				    chosenWineIndex = Main.scanner.nextInt();
					Main.scanner.nextLine();
				} catch (Exception ex) {
					ex.printStackTrace();
					chosenWineIndex = -1;
				}
			} while (chosenWineIndex < 0 && chosenWineIndex > wineList.size());

			// The real position of the wine list from the research is lower than one
			chosenWineIndex--;

			// Found the real position of that particular wine in the gloabl Main.wineList
			int realGlobalWineIndex = Main.getIndexOfWineByNameAndYear(wineList.get(chosenWineIndex).getName(), wineList.get(chosenWineIndex).getYear());

			// Amount of wine bottles
			System.out.println("Quantity you want to buy: ");
			int quantity = Main.scanner.nextInt();
			Main.scanner.nextLine();

			// Check the amount of bottles
			while (quantity >= Main.wineList.get(realGlobalWineIndex).getQuantity() && !order ) {

				System.out.println("Amount NOT AVAILABLE");
				System.out.println("1- Order\n2-Change amount:");
				int choice = Main.scanner.nextInt();
				Main.scanner.nextLine();

				// Check if choice is correct
				while (choice < 1 || choice > 2) {
					System.out.println("1- Order\n2-Change amount:");
					choice = Main.scanner.nextInt();
					Main.scanner.nextLine();
				}

				if (choice == 1) { // Order -> set order to true -> exit the while loop
					order = true;
				}

				System.out.println("Amount you want to buy: ");
				quantity = Main.scanner.nextInt();
				Main.scanner.nextLine();
			}

			if (order) {
				// Make the order
				System.out.println("Created request for wine");

				Request request = new Request();
				request.customer = this;
				request.wine = Main.wineList.get(realGlobalWineIndex);
				request.quantity = quantity;

				Main.pendingRequestForEmployee.add(request);


			} else {
				// Buying the Wine
				double amount = Main.wineList.get(realGlobalWineIndex).getPrice() * quantity;
				System.out.println("Total amount: " +  amount);

				// this = this customer
				Purchase purchase = new Purchase(this, Main.wineList.get(realGlobalWineIndex), quantity, amount, LocalDateTime.now(), false);
				Main.purchaseList.add(purchase);

				// Changing quantity of wine in the Main.wineList
				Main.wineList.get(realGlobalWineIndex).change(-quantity);

				// Check remaining quantity of Wine in global wineList
				if(Main.wineList.get(realGlobalWineIndex).getQuantity() == 0) {
					Request request = new Request();
					request.customer = null;
					request.wine = Main.wineList.get(realGlobalWineIndex);
					request.quantity = quantity;

					Main.pendingRequestForEmployee.add(request);
				}
			}
		}
	}

}
