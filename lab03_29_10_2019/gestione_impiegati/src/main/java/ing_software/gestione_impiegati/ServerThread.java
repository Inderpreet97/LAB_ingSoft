package ing_software.gestione_impiegati;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {

	//private static final int MAX = 100;
	//private static final long SLEEPTIME = 200;

	private Server server;
	private Socket socket;

	public ServerThread(final Server s, final Socket c) {
		this.server = s;
		this.socket = c;
	}

	@Override
	public void run() {
		ObjectInputStream is = null;
		ObjectOutputStream os = null;

		try {
			is = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();

			return;
		}

		String id = String.valueOf(this.hashCode());
		
		boolean threadRunning = true;

		while (threadRunning) {

			try {
				Object obj = is.readObject();

				// Check if object is not null and if it is instance of Message Class
				if ((obj != null) && (obj instanceof Message)) {

					// Cast object to Message class
					Message msg = (Message) obj;

					System.out.format("Thread %s received: %s from Client\n", id, msg.getCalledFunction().name());

					if (os == null) {
						os = new ObjectOutputStream(new BufferedOutputStream(
								this.socket.getOutputStream()));
					}

					// Check the differente called function from the message received
					switch (msg.getCalledFunction()) {
					case login:
						if (msg.getObj() instanceof Employee) {
							Employee loggedEmployee = this.server.Login(((Employee) msg.getObj()));
							if (loggedEmployee != null) {
								// Login corrected
								System.out.print("Login corrected for " + loggedEmployee.getUsername());
								System.out.print(" - " + loggedEmployee.getName() + " " + loggedEmployee.getSurname());
								System.out.println(" - " + loggedEmployee.getFiscalCode());

								// Send message "done" to the client
								// TODO in set content, describe the message or simply "Done"?
								msg.setContent("Done");      
								msg.setCalledFunction(Functions.done);
								msg.setObj(loggedEmployee);
								os.writeObject(msg);
								os.flush();

							} else {
								// Login not corrected
								System.out.println("Login not corrected");

								// Send message "error" to the client
								msg.setContent("Login not corrected, please enter corrected credentials");
								msg.setCalledFunction(Functions.error);
								os.writeObject(msg);
								os.flush();

							}
						} else {

							// content = descrizione del messaggio
							// calledFunction (done = successo) (error = non successo)
							// if calledFunction = error -> content = descrizione errore

							msg.setContent("Object is not instance of Employee");
							msg.setCalledFunction(Functions.error);
							os.writeObject(msg);
							os.flush();
						}
						break;
					case logout:
						threadRunning = false;
						break;
					case insertEmployee:
						// Check if all data received are corrected
						// Check if the fiscalCode and the username does not exist yet

						if (msg.getObj() instanceof Employee) {
							Employee employee = (Employee) msg.getObj();
							if (this.server.searchEmployeeByFiscalCode(employee.getFiscalCode())) {
								msg.setContent("Done");
								msg.setCalledFunction(Functions.done);
								os.writeObject(msg);
								os.flush();
							} else {
								msg.setContent("FISCAL CODE ALREADY EXIST");
								msg.setCalledFunction(Functions.error);
								os.writeObject(msg);
								os.flush();
							}
						}

						break;
					case updateEmployee:
						// Update
						break;
					case searchEmployee:
						break;
					default:
						break;
					}
				} 
			} catch (Exception e) {
				e.printStackTrace();
				threadRunning = false;
			}
		}
		
		// Close the connection with a single client
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}