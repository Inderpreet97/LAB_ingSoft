package ing_software.gestione_impiegati;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class DataServer {

	private static final int PORT = 4444;
	private static final double MIN = 0.1;

	public void reply() {
		
		try {
			
			ServerSocket server = new ServerSocket(PORT);

			Socket client = server.accept();

			ObjectInputStream is = new ObjectInputStream(client.getInputStream());
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			Random r = new Random();

			while (true) {
				
				// Reads until there are messages or
				// until it sends an end message

				Object obj = is.readObject();

				if (obj != null) {

					// Case
					if (obj instanceof Message) {

						System.out.format(" Server received: %s from Client\n", ((Message) obj).getContent());

						if (r.nextDouble() > MIN) {
							os.writeObject(new Message(obj.getBranch(), "done"));
							os.flush();
						} else {
							os.writeObject(new Message(obj.getUser(), "end"));
							os.flush();
							break;
						}

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

	public static void main(String[] args) {
		new DataServer().reply();
	}

}
