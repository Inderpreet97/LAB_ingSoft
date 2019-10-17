package cantina_vini;

public class Wine {

	public Wine() {}
	
	public Wine(String name, int year, String description, String vine, int quantity, double price) {
		super();
		this.name = name;
		this.year = year;
		this.description = description;
		this.vine = vine;
		this.quantity = quantity;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVine() {
		return vine;
	}
	public void setVine(String vine) {
		this.vine = vine;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void change(int value) {
		this.quantity += value;
	}
	
	private String name;
	private int year;
	private String description;
	private String vine;
	private int quantity;
	private double price;
	
	
}
