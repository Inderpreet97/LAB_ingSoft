package testSpace;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import testSpace.Menu.MenuFunctionary;
import testSpace.Menu.MenuManager;

public class ObjectServer {
	private static final int SPORT = 4444;
	private static final double MIN = 0.1;

	public void reply() {
		System.out.println("Server is online on port " + SPORT);
		
		try {
			ServerSocket server = new ServerSocket(SPORT);

			Socket client = server.accept();
			
			ObjectInputStream is = new ObjectInputStream(client.getInputStream());
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			//Random r = new Random();

			while (true) {
				// Reads until there are messages or
				// until it sends an �end� message

				Object o = is.readObject();

				// If nobject is not null
				if (o != null) {

					if (o instanceof Message) {
						Message message = (Message) o;
						System.out.format(" Server received: %s from Client\n", message.getContent());

						// If it is a User
						if (message.getObject() instanceof User) {
							User user = (User) message.getObject();
							System.out.println("A user has been readt");
							os.writeObject("Welcome " + user.getName() + " " + user.getSurname());
							os.flush();
						}
					}

				} else {
					// If object receiver is null, break the while loop
					System.out.println("Object null has been received from a client");
					break;
				}
			}

			client.close();
			server.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public static void main(final String[] v) {
		//new ObjectServer().reply();
		// MenuFunctionary.Run();
		MenuManager.Run();
	}
}