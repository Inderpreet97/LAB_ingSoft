package cantina_vini;

public class Notification {
	
	// Constructors
	public Notification() {}
	public Notification(Customer customer, Wine wine) {
		super();
		this.customer = customer;
		this.wine = wine;
	}

	// Attributes
	public Customer customer = new Customer();	// It cannot be null
	public Wine wine = new Wine();
	
}
