package ing_software.gestione_impiegati;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Server {

	// File names
	private static String employeeFileName = "employee.json";
	private static String branchFileName = "branches.json";

	// Global list
	// static ArrayList<Employee> employeeList = new ArrayList<Employee>();
	private static CopyOnWriteArrayList<Employee> employeeList = new CopyOnWriteArrayList<Employee>();
	private static CopyOnWriteArrayList<Branch> branchList = new CopyOnWriteArrayList<Branch>();

	// Server attributes
	private static final int PORT = 4444;
	private static final int COREPOOL = 5;
	private static final int MAXPOOL = 100;
	private static final long IDLETIME = 5000;

	// Generic attributes
	private ServerSocket socket;
	private ThreadPoolExecutor pool;

	// Constructors
	public Server() throws IOException {
		this.socket = new ServerSocket(PORT);
	}

	private void run() {

		/*
		 * THREAD POOL:
		 * 
		 * Java Thread pool represents a group of worker threads that are waiting for
		 * the job and reuse many times.
		 * 
		 * In case of thread pool, a group of fixed size threads are created. A thread
		 * from the thread pool is pulled out and assigned a job by the service
		 * provider. After completion of the job, thread is contained in the thread pool
		 * again.
		 * 
		 */

		this.pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());

		/*
		 * Server stays "online" listening for incoming Clients. It accepts it the
		 * incoming call and assign it a new Thread
		 */
		while (true) {
			try {
				Socket s = this.socket.accept();

				this.pool.execute(new ServerThread(this, s));
			} catch (Exception e) {
				break;
			}
		}
		this.pool.shutdown();
	}

	// Getters & setters
	public ThreadPoolExecutor getPool() {
		return this.pool;
	}

	// This functions simply closes the socket
	public void close() {
		try {
			this.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Server Main
	public static void main(final String[] v) throws IOException {
		readJSONEmployee(); // First, read the JSON File
		readJSONBranch();
		new Server().run(); // Run the server
		writeJSONEmployee(); // When server is closed, write on JSON File
		writeJSONBranch();
	}

	/**************************************************************
	 **
	 ** Server functions
	 **
	 **************************************************************/

	/**
	 * Search an employee by username and password
	 * 
	 * @param employee
	 * @return the employee found or null
	 **/
	public Employee Login(Employee employee) {
		for (Employee temp : employeeList) {
			if (temp.getUsername().equals(employee.getUsername())
					&& temp.getPassword().equals(employee.getPassword())) {
				return temp;
			}
		}
		return null;
	}

	/**
	 * Search an employee in the employeeList using username
	 * 
	 * @param username
	 * @return index of employee or -1 if not found
	 **/
	private int searchEmployeeIndexByUsername(String username) {
		int index = 0;
		for (Employee temp : employeeList) {
			if (temp.getUsername().equals(username)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	/**
	 * Search an employee in the employeeList using fiscal code
	 * 
	 * @param fiscalCode
	 * @return index of employee or -1 if not found
	 **/
	private int searchEmployeeIndexByFiscalCode(String fiscalCode) {
		int index = 0;
		for (Employee employee : employeeList) {
			if (employee.getFiscalCode().toLowerCase().equals(fiscalCode.toLowerCase())) {
				return index;
			}
			index++;
		}
		return -1;
	}

	/**
	 * Creates a new array of Employee that have the given Job
	 * 
	 * @param job
	 * @return ArrayList of employee with given Job, if there aren't any return an
	 *         empty array
	 */
	public ArrayList<Employee> getEmployeeListByJob(Jobs job) {

		ArrayList<Employee> returnList = new ArrayList<Employee>();
		for (Employee employee : employeeList) {
			if (employee.getJob().equals(job)) {
				returnList.add(employee);
			}
		}

		return returnList;
	}

	/**
	 * If the employee doesn't already exist, add it to Employee List
	 * 
	 * @param employee
	 * @return true if employee added, false otherwise
	 */
	public Boolean addEmployee(Employee employee) {
		// Check if fiscal code and username are NOT already registered
		if (searchEmployeeIndexByFiscalCode(employee.getFiscalCode()) < 0
				&& searchEmployeeIndexByUsername(employee.getUsername()) < 0) {
			employeeList.add(employee);
			return true;
		}
		return false;
	}

	/**
	 * Find the index of the employee and if it exists update it with new data
	 * 
	 * @param employee
	 * @param oldUsername
	 * @return true if employee updated, false otherwise
	 */
	public Boolean updateEmployee(Employee employee) {
		int toUpdateEmployeeIndex = searchEmployeeIndexByFiscalCode(employee.getFiscalCode());

		if (toUpdateEmployeeIndex >= 0) {
			employeeList.set(toUpdateEmployeeIndex, employee);
			return true;
		}

		// Employee not found
		return false;
	}
	
	/**
	 * Check if the given branch exists in the branch list of the server
	 * @param employeeBranch
	 * @return true if the branch exists, false otherwise
	 */
	public boolean checkBranch(String employeeBranch) {
		
		for(Branch branch : branchList) {
			if(branch.getName().toLowerCase().equals(employeeBranch.toLowerCase())){
				return true;
			}
		}
		
		return false;
	}

	// Read JSON employee list
	private static void readJSONEmployee() {

		// JSON parser object to parse read file
		JSONParser parser = new JSONParser();

		try (Reader reader = new FileReader(employeeFileName)) {
			JSONArray employeeListJSON = (JSONArray) parser.parse(reader);

			for (int i = 0; i < employeeListJSON.size(); i++) {

				JSONObject employeeJSON = (JSONObject) employeeListJSON.get(i);

				// Read Data
				String fiscalCode = (String) employeeJSON.get("fiscalCode");
				String username = (String) employeeJSON.get("username");
				String password = (String) employeeJSON.get("password");
				String name = (String) employeeJSON.get("name");
				String surname = (String) employeeJSON.get("surname");
				String jobString = (String) employeeJSON.get("job");
				Jobs job = Jobs.valueOf(jobString);
				String branch = (String) employeeJSON.get("branch");
				LocalDate startDate = LocalDate.parse((CharSequence) employeeJSON.get("startDate"));
				
				String endDateString = (String) employeeJSON.get("endDate");
				LocalDate endDate = null;
				
				if(!endDateString.isEmpty()) {
					endDate = LocalDate.parse(endDateString);
				}

				// Create the employee object from JSON object
				Employee employee = new Employee(fiscalCode, username, password, name, surname, job, branch, startDate,
						endDate);

				// Add employee to global list
				employeeList.add(employee);

			}
			
			for (Employee e : employeeList) {
				e.print();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
	}

	// Write JSON employee list
	@SuppressWarnings("unchecked")
	static void writeJSONEmployee() {
		// Create JSON string for Employee List
		JSONArray employeeListJSON = new JSONArray();

		for (Employee emp : employeeList) {
			JSONObject empDetails = new JSONObject();
			empDetails.put("fiscalCode", emp.getFiscalCode());
			empDetails.put("username", emp.getUsername());
			empDetails.put("password", emp.getPassword());
			empDetails.put("name", emp.getName());
			empDetails.put("surname", emp.getSurname());
			empDetails.put("job", emp.getJob().name());
			empDetails.put("branch", emp.getBranch());
			empDetails.put("startDate", emp.getStartDate().toString());
			
			if(emp.getEndDate() == null) {
				empDetails.put("endDate", "");
			} else {
				empDetails.put("endDate", emp.getEndDate().toString());
			}

			employeeListJSON.add(empDetails);
		}

		// Write JSON file
		try (FileWriter file = new FileWriter(employeeFileName)) {
			file.write(employeeListJSON.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Read JSON employee list
	@SuppressWarnings("unused")
	private static void readJSONBranch() {

		// JSON parser object to parse read file
		JSONParser parser = new JSONParser();

		try (Reader reader = new FileReader(branchFileName)) {
			JSONArray branchListJSON = (JSONArray) parser.parse(reader);

			for (int i = 0; i < branchListJSON.size(); i++) {

				JSONObject branchJSON = (JSONObject) branchListJSON.get(i);

				// Read Data
				String name = (String) branchJSON.get("name");
				String address = (String) branchJSON.get("address");

				// Create the branch object from JSON object
				Branch branch = new Branch(name, address);

				// Add branch to global list
				branchList.add(branch);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	static void writeJSONBranch() {
		// Create JSON string for Employee List
		JSONArray branchListJSON = new JSONArray();

		for (Branch branch : branchList) {
			JSONObject branchDetails = new JSONObject();
			
			branchDetails.put("name", branch.getName());
			branchDetails.put("address", branch.getAddress());
			
			branchListJSON.add(branchDetails);
		}

		// Write JSON file
		try (FileWriter file = new FileWriter(branchFileName)) {
			file.write(branchListJSON.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}