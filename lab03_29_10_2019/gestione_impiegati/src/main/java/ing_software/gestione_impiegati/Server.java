package ing_software.gestione_impiegati;

import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class Server {
	
	// File names
	private String fileNameEmployee = "employee.json";
	
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
				// Reads until there are messages or
				// until it sends an �end� message

				Object obj = inputObject.readObject();

				if ((obj != null) && (obj instanceof Message)) {
					
					Message msg = (Message) obj;

					System.out.format(" Server received: %s from Client\n", msg.getContent());
					
					switch(msg.getCalledFunction()) {
					case login:
						if (msg.getObj() instanceof Employee) {
							Login(((Employee) msg.getObj()));
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
						break;
					case updateEmployee:
						break;
					case searchEmployee:
						break;
					}

					if (r.nextDouble() > MIN) {
						//outputObject.writeObject(new Message(msg.getUser(), "done"));
						outputObject.flush();
					} else {
						//outputObject.writeObject(new Message(m.getUser(), "end"));
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
	
	private Boolean Login(Employee employee) {
		return true;
	}
	
	// Read JSON employee
	private void readJSONEmployee() {
		JSONParser parser = new JSONParser();
		
	}
	
}