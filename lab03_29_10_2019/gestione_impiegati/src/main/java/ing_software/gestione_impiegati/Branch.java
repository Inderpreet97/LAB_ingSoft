package ing_software.gestione_impiegati;

public class Branch {
	
	private String name;
	private String address;
	
	public Branch(String name, String address) {
		this.setName(name);
		this.setAddress(address);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
