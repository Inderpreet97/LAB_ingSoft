package cantina_vini;

import java.util.ArrayList;

public class Person {
	
	// Attributes
	private String username;
	private String name;
	private String surname;
	private String password;
	
	// Constructors
	public Person() {}
	public Person(String username, String name, String surname, String password) {
		super();
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.password = password;
	}
	
	// Getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	// Functions
	public void searchWine() {
		// This function searches the wines
		
		System.out.print("Wine name: ");
		String wineName = Main.scanner.nextLine();
		
		ArrayList<Wine> resultList = new ArrayList<Wine>();
		
		for (Wine wine : Main.wineList) {
			 if (wine.getName().equals(wineName)){	resultList.add(wine);} 
		}
		
		if (resultList.size() == 0) { System.out.println("No wine found with this name");}
		else { Main.printWineList(resultList);	}	
		
	}
	public ArrayList<Wine> searchWineAndGetList() {
		
		// The same function of searhWine. It returns the resultList
		
		System.out.print("Wine name: ");
		String wineName = Main.scanner.nextLine();
		
		ArrayList<Wine> resultList = new ArrayList<Wine>();
		
		for (Wine wine : Main.wineList) {
			if (wine.getName().toLowerCase().equals(wineName.toLowerCase()) && wine.getQuantity() > 0){	resultList.add(wine);} 
		}
		
		if (resultList.size() == 0) { System.out.println("No wine found with this name");}
		
		Main.printWineList(resultList);
		return resultList;
		
	}
	
	

}
