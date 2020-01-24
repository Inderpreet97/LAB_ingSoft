package ing_software.circolosportivo_JavaFX_DB;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.Session;

@Entity
@DiscriminatorValue("1")
public class Socio extends Persona {

	public Socio(String nome, String cognome, String email, String password) {
		super(nome, cognome, email, password);
	}

	public Socio() {
		super();
	}

	public static Boolean iscrizioneAttivita(String nomeAttivita, String emailPersona) {
		try {
			Partecipazione partecipazione = new Partecipazione(nomeAttivita, emailPersona);

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			session.save(partecipazione);

			session.getTransaction().commit();
			session.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Boolean lasciaAttivita(String nomeAttivita, String emailPersona) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Partecipazione partecipazione = (Partecipazione) session.get(Partecipazione.class, new PartecipazionePrimaryKey(nomeAttivita, emailPersona));
			
			if(partecipazione == null) {
				throw new Exception("Partecipazione non trovata");
			}
			
			session.delete(partecipazione);
			session.getTransaction().commit();
			session.close();
			
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
