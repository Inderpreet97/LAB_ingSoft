package ing_software.circolosportivo_JavaFX_DB.classes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Socio extends Persona {

	public Socio(String nome, String cognome, String email, String password) {
		super(nome, cognome, email, password);
	}

	public Socio() {
		super();
	}
	
	/*
			public void sceltaAttivita() {
				try {
		
					// TODO Scelta indice ATTIVITA
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
					//return true;
		
				} catch (Exception ex) {
					System.out.println("Error: " + ex.getMessage());
					//return false;
				}
			}
	*/
}
