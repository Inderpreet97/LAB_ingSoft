package testSpace;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class ObjectClient {
	private static final int SPORT = 4444;
	private static final String SHOST = "localhost";
	private String[] greetings = { "hello", "hi", "ciao", "hey", "aloha", "shalom" };

	private static ArrayList<User> listUser = new ArrayList<User>();
	
	public void send() {
		try {
			// Client
			Socket client = new Socket(SHOST, SPORT);

			Random r = new Random();

			Message message = new Message(new User("Giuseppe", "Urbano"), "");

			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			ObjectInputStream is = new ObjectInputStream(client.getInputStream());

			while (true) {
				// Sends messages until it receives an �end� message

				message.setContent(greetings[r.nextInt(greetings.length)]);

				System.out.format(" Client sends: %s to Server", message.getContent());

				os.writeObject(message);
				os.flush();

				Object o = is.readObject();

				if (o != null) {

					if (o instanceof String) {
						System.out.println("Simple string has been sent: " + o);
					} else if (o instanceof Message) {
						Message n = (Message) o;
						System.out.print("Message received");
						System.out.format(" and received: %s from Server\n", n.getContent());
					}

				} else {
					System.out.println("No object recerived");
					break;
				}
			}

			client.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(final String[] v) {
		// new ObjectClient().send();
		
		// Test contains
		
		listUser.add(new User("giuseppe", "urbano"));
		listUser.add(new User("marco", "rossi"));
		
		User temp = new User("giusepp", "urbano");
		check(temp);
	}
	
	public static void check(User user) {
		for (User u : listUser) {
			if (u.equals(user)) {
				System.out.println("Found");
				break;
			} 
			System.out.println("Not found");
		}
		
	}
}