package ing_software.circolo_sportivo;

public class Socio extends Persona{
	
	public Socio(String nome, String cognome, String email, String password) {
		super(nome, cognome, email, password);
	}

	public Boolean iscrizioneAttivita() {
		try {
			System.out.println("\n------>> Iscrizione Attivita <<------");
			for (int index = 0; index < App.attivita.length; index++) {
				System.out.println(index + ")\t" + App.attivita[index].getNome());
			}

			int userChoice;
			do {
				try {
					System.out.print("\nIndice attivita a cui iscriversi: ");
					userChoice = App.scanner.nextInt();
					App.scanner.nextLine(); // Perchè nextInt() non legge "\n" e quindi viene letto da questo nextLine()
				} catch (Exception ex) {
					System.out.println("Errore: " + ex.getMessage());
					System.out.println("Premere un tasto per continuare...");
					App.scanner.nextLine();
					userChoice = -1;
				}
			} while (userChoice < 0 || userChoice > App.attivita.length);

			App.attivita[userChoice].aggiungPersona(this);
			return true;

		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			return false;
		}
	}

	public Boolean lasciaAttivita() {
		try {
			System.out.println("\n------>> Disiscrizione Attivita <<------");
			for (int index = 0; index < App.attivita.length; index++) {
				System.out.println(index + ")\t" + App.attivita[index].getNome());
			}

			int userChoice;
			do {
				try {
					System.out.print("\nIndice attivita a cui disiscriversi: ");
					userChoice = App.scanner.nextInt();
					App.scanner.nextLine(); // Perchè nextInt() non legge "\n" e quindi viene letto da questo nextLine()
				} catch (Exception ex) {
					System.out.println("Errore: " + ex.getMessage());
					System.out.println("Premere un tasto per continuare...");
					App.scanner.nextLine();
					userChoice = -1;
				}
			} while (userChoice < 0 || userChoice > App.attivita.length);

			App.attivita[userChoice].rimuoviPersona(this);
			return true;

		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			return false;
		}
	}
}
