package cantina_vini;

public class Request {
	
	// Attributes
	public Customer customer = new Customer(); // It can be null
	public Wine wine = new Wine();
	public int quantity;
	
	// Constructors
	public Request() {}
	public Request(Customer customer, Wine wine, int quantity) {
		this.customer = customer;
		this.wine = wine;
		this.quantity = quantity;
	}
}
