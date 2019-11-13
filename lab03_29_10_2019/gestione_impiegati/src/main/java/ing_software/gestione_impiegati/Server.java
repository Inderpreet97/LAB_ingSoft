package ing_software.gestione_impiegati;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Server {

	// File names
	private static String employeeFileName = "employee.json";

	// Global list
	static ArrayList<Employee> employeeList = new ArrayList<Employee>();

	// Server
	private static final int PORT = 4444;

	private static final int COREPOOL = 5;
	private static final int MAXPOOL = 100;
	private static final long IDLETIME = 5000;

	private ServerSocket socket;
	private ThreadPoolExecutor pool;

	public Server() throws IOException {
		this.socket = new ServerSocket(PORT);
	}

	private void run() {
		this.pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>());

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

	public ThreadPoolExecutor getPool() {
		return this.pool;
	}

	public void close() {
		try {
			this.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] v) throws IOException {
		readJSONEmployee();
		new Server().run();
		writeJSONEmployee();
	}

	// Functions
	Employee Login(Employee employee) {
		// Return the employee found or null
		for (Employee temp : employeeList) {
			if (temp.getUsername().equals(employee.getUsername())
					&& temp.getPassword().equals(employee.getPassword())) {
				return temp;
			}
		}
		return null;
	}

	// Search functions
	public int searchEmployeeByUsername(String username) {
		int index = 0;
		for (Employee temp : employeeList) {
			if (temp.getUsername().equals(username)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	public boolean searchEmployeeByFiscalCode(String fiscalCode) {
		for (Employee employee : employeeList) {
			if (employee.getFiscalCode().equals(fiscalCode)) {
				return true;
			}
		}
		return false;
	}

	// Read JSON employee
	private static void readJSONEmployee() {

		// JSON parser object to parse read file
		JSONParser parser = new JSONParser();

		try (Reader reader = new FileReader(employeeFileName)) {
			JSONArray employeeListJSON = (JSONArray) parser.parse(reader);

			for (int i = 0; i < employeeListJSON.size(); i++) {

				JSONObject employeeJSON = (JSONObject) employeeListJSON.get(i);

				// ReadlDate
				String fiscalCode = (String) employeeJSON.get("fiscalCode");
				String username = (String) employeeJSON.get("username");
				String password = (String) employeeJSON.get("password");
				String name = (String) employeeJSON.get("name");
				String surname = (String) employeeJSON.get("surname");
				String job = (String) employeeJSON.get("job");
				String branch = (String) employeeJSON.get("branch");
				LocalDate startDate = LocalDate.parse((CharSequence) employeeJSON.get("startDate"));
				LocalDate endDate = LocalDate.parse((CharSequence) employeeJSON.get("endDate"));

				// Create the employee object from json object
				Employee employee = new Employee(fiscalCode, username, password, name, surname, job, branch, startDate,
						endDate);

				// Add employee to global list
				employeeList.add(employee);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
	}
	private static void writeJSONEmployee() {
	}
}