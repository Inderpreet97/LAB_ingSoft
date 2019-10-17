package cantina_vini;

public class Employee extends Person{
	
	// Constructors
	public Employee() {}	
	public Employee(String username, String name, String surname, String password) {
		// TODO Auto-generated constructor stub
		super(username, name, surname, password);
	}
	
	// Functions
	public void manageShipment() {
		// This function allows an employee to manage orders and make the shipment
	}
	public void replaceProduct() {
		
		// Add some wine
		Wine wineToAdd = new Wine();
		
		// Check if a customer is waiting for that wine
		for(Request request : Main.pendinRequestForEmployee) {
			if(request.wine.getName().equals(wineToAdd.getName()) && request.wine.getYear() == wineToAdd.getYear()) {
				if (request.customer != null) {
					// If customer is not null, it means that a customer made the request and he's waiting for notification
					Notification notification = new Notification(request.customer, request.wine);
					Main.pendingNotificationForCustomer.add(notification);
				}
			}
		}
	}
	
	public void Menu() {
		
		// Visulizza le pending Request
		
	}
}
