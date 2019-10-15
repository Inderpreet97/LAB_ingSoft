package cantina_vini;

public class Person {
	
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
	
	private String username;
	private String name;
	private String secondName;
	private String password;
	
	

}
