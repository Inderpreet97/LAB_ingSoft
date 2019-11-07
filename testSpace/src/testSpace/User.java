package testSpace;

public class User {
	
	private String name;
	private String surname;
	public User(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}
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

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (obj == null) {
			return false;
		} else if (obj instanceof User) {
			User user = (User) obj;
			if (user.getName().equals(this.name) && (user.getSurname().equals(this.surname))){
				return true;
			}
		}
		return false;
	}
	
}
