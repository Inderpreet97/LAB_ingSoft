package ing_software.gestione_impiegati;

import java.time.LocalDate;

public class Employee {
	
	// Attributes
	private String fiscalCode;
	private String username;
	private String password;
	private String name;
	private String surname;
	
	private String job;
	private String branch;
	
	private LocalDate startDate;
	private LocalDate endDate;
	
	// Constructors
	public Employee(String fiscalCode, String username, String password, String name, String surname, String job,
			String branch, LocalDate startDate, LocalDate endDate) {
		this.fiscalCode = fiscalCode;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.job = job;
		this.branch = branch;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Employee(String fiscalCode, String username, String password, String name, String surname, String job,
			String branch, LocalDate startDate) {
		this(fiscalCode, username, password, name, surname, job, branch, startDate, null);
		
	}
	
	// Getters & Setters
	public String getFiscalCode() {
		return fiscalCode;
	}
	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	
	

}