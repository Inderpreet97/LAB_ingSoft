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
		
	}
}
