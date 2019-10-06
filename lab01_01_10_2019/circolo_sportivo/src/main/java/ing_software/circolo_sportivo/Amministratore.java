package ing_software.circolo_sportivo;

public class Amministratore extends Socio {

	public Amministratore(String nome, String cognome, String email, String password) {
		super(nome, cognome, email, password);
	}

	public Boolean aggiungiPersona() {
		try {
			int userChoice;

			System.out.println("\n------>> Aggiungi Persona <<------");

			do {
				try {
					System.out.println("\nCosa vuou aggiungere?");
					System.out.print("1) Socio\n2) Amministratore\nScelta: ");
					userChoice = App.scanner.nextInt();
					App.scanner.nextLine(); // Perchè nextInt() non legge "\n" e quindi viene letto da questo nextLine()
				} catch (Exception ex) {
					System.out.println("Errore: " + ex.getMessage());
					System.out.println("Premere un tasto per continuare...");
					App.scanner.nextLine();
					userChoice = 0;
				}
			} while (userChoice < 1 || userChoice > 2);

			System.out.print("Nome: ");
			String nome = App.scanner.nextLine();
			System.out.print("Cognome: ");
			String cognome = App.scanner.nextLine();
			System.out.print("Email: ");
			String email = App.scanner.nextLine();
			System.out.print("Password: ");
			String password = App.scanner.nextLine();

			if (userChoice == 1) {
				Socio socio = new Socio(nome, cognome, email, password);
				App.circolo = App.aggiungiElementoArray(App.circolo, socio);

			} else if (userChoice == 2) {
				Amministratore amministratore = new Amministratore(nome, cognome, email, password);
				App.circolo = App.aggiungiElementoArray(App.circolo, amministratore);
			}

			return true;

		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			return false;
		}
	}

	public Boolean rimuoviPersona() {
		try {

			System.out.println("\n------>> Rimuovi Persona <<------");
			for (int index = 0; index < App.circolo.length; index++) {
				System.out.print(index + ")\t" + App.circolo[index].getNome() + "\t" + App.circolo[index].getCognome());
				System.out.println("\t" + App.circolo[index].getEmail());
			}

			int userChoice;
			do {
				try {
					System.out.print("\nIndice persona da rimuovere: ");
					userChoice = App.scanner.nextInt();
					App.scanner.nextLine(); // Perchè nextInt() non legge "\n" e quindi viene letto da questo nextLine()
				} catch (Exception ex) {
					System.out.println("Errore: " + ex.getMessage());
					System.out.println("Premere un tasto per continuare...");
					App.scanner.nextLine();
					userChoice = -1;
				}
			} while (userChoice < 0 || userChoice > App.circolo.length);

			for (Attivita attivita : App.attivita) {
				attivita.rimuoviPersona(App.circolo[userChoice]);
			}

			App.circolo = App.rimuoviElementoArray(App.circolo, App.circolo[userChoice]);

			return true;

		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			return false;
		}
	}

	public Boolean modificaPersona() {
		try {
			System.out.println("\n------>> Modifica Persona <<------");
			for (int index = 0; index < App.circolo.length; index++) {
				System.out.print(index + ")\t" + App.circolo[index].getNome() + "\t" + App.circolo[index].getCognome());
				System.out.println("\t" + App.circolo[index].getEmail());
			}

			int userChoice;
			do {
				try {
					System.out.print("\nIndice persona da modificare: ");
					userChoice = App.scanner.nextInt();
					App.scanner.nextLine(); // Perchè nextInt() non legge "\n" e quindi viene letto da questo nextLine()
				} catch (Exception ex) {
					System.out.println("Errore: " + ex.getMessage());
					System.out.println("Premere un tasto per continuare...");
					App.scanner.nextLine();
					userChoice = -1;
				}
			} while (userChoice < 0 || userChoice > App.circolo.length);

			System.out.println("\nInserire i nuovi dati");
			System.out.print("Nome: ");
			String nome = App.scanner.nextLine();
			System.out.print("Cognome: ");
			String cognome = App.scanner.nextLine();
			System.out.print("Email: ");
			String email = App.scanner.nextLine();
			System.out.print("Password: ");
			String password = App.scanner.nextLine();

			App.circolo[userChoice].setNome(nome);
			App.circolo[userChoice].setCognome(cognome);
			App.circolo[userChoice].setEmail(email);
			App.circolo[userChoice].setPassword(password);

			return true;

		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			return false;
		}
	}

	public Boolean aggiungiAttivita() {
		try {
			int userChoice;

			System.out.println("\n------>> Aggiungi Attivita <<------");

			do {
				try {
					System.out.println("\nCosa vuoi aggiungere?");
					System.out.print("1) Gara\n2) Corso\nScelta: ");
					userChoice = App.scanner.nextInt();
					App.scanner.nextLine(); // Perchè nextInt() non legge "\n" e quindi viene letto da questo nextLine()
				} catch (Exception ex) {
					System.out.println("Errore: " + ex.getMessage());
					System.out.println("Premere un tasto per continuare...");
					App.scanner.nextLine();
					userChoice = 0;
				}
			} while (userChoice < 1 || userChoice > 2);

			System.out.print("Nome: ");
			String nome = App.scanner.nextLine();

			if (userChoice == 1) {
				Gara gara = new Gara(nome);
				App.attivita = App.aggiungiElementoArray(App.attivita, gara);

			} else if (userChoice == 2) {
				Corso corso = new Corso(nome);
				App.attivita = App.aggiungiElementoArray(App.attivita, corso);
			}

			return true;

		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			return false;
		}
	}

	public Boolean rimuoviAttivita() {
		try {
			System.out.println("\n------>> Rimuovi Attivita <<------");
			for (int index = 0; index < App.attivita.length; index++) {
				System.out.println(index + ")\t" + App.attivita[index].getNome());
			}

			int userChoice;
			do {
				try {
					System.out.print("\nIndice attivita da rimuovere: ");
					userChoice = App.scanner.nextInt();
					App.scanner.nextLine(); // Perchè nextInt() non legge "\n" e quindi viene letto da questo nextLine()
				} catch (Exception ex) {
					System.out.println("Errore: " + ex.getMessage());
					System.out.println("Premere un tasto per continuare...");
					App.scanner.nextLine();
					userChoice = -1;
				}
			} while (userChoice < 0 || userChoice > App.attivita.length);

			App.attivita = App.rimuoviElementoArray(App.attivita, App.attivita[userChoice]);

			return true;

		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			return false;
		}
	}

	public Boolean modificaAttivita() {
		try {
			System.out.println("\n------>> Modifica Attivita <<------");
			for (int index = 0; index < App.attivita.length; index++) {
				System.out.println(index + ")\t" + App.attivita[index].getNome());
			}

			int userChoice;
			do {
				try {
					System.out.print("\nIndice attivita da modificare: ");
					userChoice = App.scanner.nextInt();
					App.scanner.nextLine(); // Perchè nextInt() non legge "\n" e quindi viene letto da questo nextLine()
				} catch (Exception ex) {
					System.out.println("Errore: " + ex.getMessage());
					System.out.println("Premere un tasto per continuare...");
					App.scanner.nextLine();
					userChoice = -1;
				}
			} while (userChoice < 0 || userChoice > App.attivita.length);
			
			System.out.println("\nInserire i nuovi dati");
			System.out.print("Nome: ");
			String nome = App.scanner.nextLine();
			
			App.attivita[userChoice].setNome(nome);

			return true;

		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			return false;
		}
	}
}
