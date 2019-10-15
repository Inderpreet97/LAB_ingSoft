package cantina_vini;

public class Wine {

	public Wine(String name, int year, String description, String vine) {
		super();
		this.name = name;
		this.year = year;
		this.description = description;
		this.vine = vine;
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
	public Wine() {
		// TODO Auto-generated constructor stub
	}
	
	private String name;
	private int year;
	private String description;
	private String vine;

}
