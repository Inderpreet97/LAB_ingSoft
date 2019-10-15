package cantina_vini;

import java.util.ArrayList;

public class Person {
	
	private String username;
	private String name;
	private String secondName;
	private String password;
	
	public Person() {}
	
	public Person(String username, String name, String secondName, String password) {
		super();
		this.username = username;
		this.name = name;
		this.secondName = secondName;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
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
	
	public void searchWine() {
		
		// This function searches the wines
		
		System.out.println("Wine name: ");
		String wineName = Main.scanner.nextLine();
		System.out.println("Year: ");
		int wineYear = Main.scanner.nextInt();
		
		// System.out.println(wineName + wineYear);
		
		ArrayList<Wine> resultList = new ArrayList<Wine>();
		
		for (Wine wine : Main.wineList) {
			 if (wine.getName().equals(wineName)){	resultList.add(wine);} 
		}
		
		if (resultList.size() == 0) { System.out.println("No wine found with this name");}
		else { Main.printWineList(resultList);	}	
		
	}
	
	public ArrayList<Wine> searchWineAndGetList() {
		
		// The same function of searhWine. It returns the resultList
		
		System.out.println("Wine name: ");
		String wineName = Main.scanner.nextLine();
		System.out.println("Year: ");
		int wineYear = Main.scanner.nextInt();
		
		ArrayList<Wine> resultList = new ArrayList<Wine>();
		
		for (Wine wine : Main.wineList) {
			if (wine.getName().equals(wineName) && wine.getQuantity() > 0){	resultList.add(wine);} 
		}
		
		if (resultList.size() == 0) { System.out.println("No wine found with this name");}
		
		Main.printWineList(resultList);
		return resultList;
		
	}
	
	

}
