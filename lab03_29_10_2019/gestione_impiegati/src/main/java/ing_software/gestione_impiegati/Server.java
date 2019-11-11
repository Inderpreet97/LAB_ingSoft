package ing_software.gestione_impiegati;

import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Server {

	// File names
	private String employeeFileName = "employee.json";

	// Global list
	ArrayList<Employee> employeeList = new ArrayList<Employee>();

	// Server
	private static final int PORT = 4444;
	private static final double MIN = 0.1;

	public void reply() {

		try {
			ServerSocket server = new ServerSocket(PORT);

			Functions i;
			i = Functions.login;

			Socket client = server.accept();

			ObjectInputStream inputObject = new ObjectInputStream(client.getInputStream());
			ObjectOutputStream outputObject = new ObjectOutputStream(client.getOutputStream());
			Random r = new Random();

			while (true) {

				// Object receiver by a client
				Object obj = inputObject.readObject();

				// Check if object is not null and if it is instance of Message Class
				if ((obj != null) && (obj instanceof Message)) {

					// Cast object to Message class
					Message msg = (Message) obj;

					System.out.format(" Server received: %s from Client\n", msg.getContent());

					// Check the differente called function from the message received
					switch (msg.getCalledFunction()) {
					case login:
						if (msg.getObj() instanceof Employee) {
							Employee loggedEmployee = Login(((Employee) msg.getObj()));
							if (loggedEmployee != null) {
								// Login corrected
								System.out.print("Login corrected for " + loggedEmployee.getUsername());
								System.out.print(" - " + loggedEmployee.getName() + " " + loggedEmployee.getSurname());
								System.out.println(" - " + loggedEmployee.getFiscalCode());
								
								// Send message "done" to the client
								// TODO in set content, describe the message or simply "Done"?
								msg.setContent("Done");      
								msg.setCalledFunction(Functions.done);
								outputObject.writeObject(msg);
								outputObject.flush();
								
							} else {
								// Login not corrected
								System.out.println("Login not corrected");
								
								// Send message "error" to the client
								msg.setContent("Login not corrected, please enter corrected credentials");
								msg.setCalledFunction(Functions.error);
								outputObject.writeObject(msg);
								outputObject.flush();
								
							}
						} else {

							// content = descrizione del messaggio
							// calledFunction (done = successo) (error = non successo)
							// if calledFunction = error -> content = descrizione errore

							msg.setContent("Object is not instance of Employee");
							msg.setCalledFunction(Functions.error);
							outputObject.writeObject(msg);
							outputObject.flush();
						}
						break;
					case insertEmployee:
						// Check if all data received are corrected
						// Check if the fiscalCode and the username does not exist yet
						
						if (msg.getObj() instanceof Employee) {
							Employee employee = (Employee) msg.getObj();
							if (searchEmployeeByFiscalCode(employee.getFiscalCode())) {
								msg.setContent("Done");
								msg.setCalledFunction(Functions.done);
								outputObject.writeObject(msg);
								outputObject.flush();
							} else {
								msg.setContent("FISCAL CODE ALREADY EXIST");
								msg.setCalledFunction(Functions.error);
								outputObject.writeObject(msg);
								outputObject.flush();
							}
						}
						
						break;
					case updateEmployee:
						// Update
						break;
					case searchEmployee:
						break;
					}

					if (r.nextDouble() > MIN) {
						// outputObject.writeObject(new Message(msg.getUser(), "done"));
						outputObject.flush();
					} else {
						// outputObject.writeObject(new Message(m.getUser(), "end"));
						outputObject.flush();
						break;
					}
				} else {
					break;
				}
			}

			client.close();
			server.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] v) {
		new Server().reply();
	}

	private Employee Login(Employee employee) {
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
	private void readJSONEmployee() {

		// JSON parser object to parse read file
		JSONParser parser = new JSONParser();

		try (Reader reader = new FileReader(employeeFileName)) {
			JSONArray employeeListJSON = (JSONArray) parser.parse(reader);

			for (int i = 0; i < employeeListJSON.size(); i++) {

				JSONObject employeeJSON = (JSONObject) employeeListJSON.get(i);

				// ReadlDate
				LocalDate startDate = LocalDate.parse((CharSequence) employeeJSON.get("startDate"));
				LocalDate endDate = LocalDate.parse((CharSequence) employeeJSON.get("endDate"));

				// Create the employee object from json object
				Employee employee = new Employee((String) employeeJSON.get("fiscalCode"),
						(String) employeeJSON.get("username"), (String) employeeJSON.get("name"),
						(String) employeeJSON.get("surname"), (String) employeeJSON.get("password"),
						(String) employeeJSON.get("job"), (String) employeeJSON.get("branch"),
						LocalDate.parse((CharSequence) employeeJSON.get("startDate")),
						LocalDate.parse((CharSequence) employeeJSON.get("endDate")));

				// Add employee to global list
				employeeList.add(employee);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
	}

	private void writeJSONEmployee() {
	}
}