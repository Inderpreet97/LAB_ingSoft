package cantina_vini;

import java.time.LocalDateTime;

public class Order {
	
	// Attributes
	private Customer customer = new Customer();
	private Employee employee = new Employee();
	private Wine wine = new Wine();
	private double quantity;
	private double amount;
	private LocalDateTime date;
	private boolean pending = false;
	
	// Constructors
	public Order() {}

}
