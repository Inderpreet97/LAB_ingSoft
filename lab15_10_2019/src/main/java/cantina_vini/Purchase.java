package cantina_vini;

import java.time.LocalDateTime;

public class Purchase {
	
	public Purchase() {}
	public Purchase(Customer customer, Wine wine, double quantity, double amount, LocalDateTime date) {
		super();
		this.customer = customer;
		this.wine = wine;
		this.quantity = quantity;
		this.amount = amount;
		this.date = date;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Wine getWine() {
		return wine;
	}
	public void setWine(Wine wine) {
		this.wine = wine;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	private Customer customer = new Customer();
	private Wine wine = new Wine();
	private double quantity;
	private double amount;
	private LocalDateTime date;	
	
	
}
