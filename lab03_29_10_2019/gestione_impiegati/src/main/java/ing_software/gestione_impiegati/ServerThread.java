package ing_software.gestione_impiegati;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {

	private Server server;
	private Socket socket;
	private Employee loggedUser = null;

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
								System.out.format("Thread %s login corrected for %s \n", id, loggedEmployee.getUsername());

								// Send message "done" to the client
								msg.setCalledFunction(Functions.done);
								msg.setObj(loggedEmployee);
								os.writeObject(msg);
								os.flush();
								loggedUser = loggedEmployee;

							} else {
								// Login not corrected
								System.out.format("Thread %s login failed \n", id);

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
							if (this.server.searchEmployeeIndexByFiscalCode(employee.getFiscalCode()) >= 0) {
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
						if(msg.getObj() instanceof Jobs) {
							Jobs searchedJob = (Jobs) msg.getObj();
							if(searchedJob.compareTo(loggedUser.getJob()) > 0) {
								msg.setContent("The logged user have Insufficient Privileges");
								msg.setCalledFunction(Functions.error);
								os.writeObject(msg);
								os.flush();
							}
						}

						/* JOBS
						 *   -  worker
						 *   -  functionary
						 *   -  manager
						 *   -  admin
						 */
						/* TODO controlla i privilegi dell'utente che chiama il metodo
						 *
						 * I privilegi devono consentire o vietare la ricerca di determinanti Job
						 * presente in msg.getConent();
						 *
						 */
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
			e.printStackTrace();
		}
	}
}
