package ing_software.circolosportivo_JavaFX_DB;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import ing_software.circolosportivo_JavaFX_DB.classes.Attivita;
import ing_software.circolosportivo_JavaFX_DB.classes.Persona;

public class App {
	public static Attivita[] attivita;
	public static Persona[] circolo;
	public static Scanner scanner;

	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		
		// Operazioni con il DB
		testDB_MetodiNuovi();
		// testDB();

		System.out.println("Programma terminato.\nPremere un tasto per terminare....");
		scanner.nextLine();

		scanner.close();
	}

	private static void testDB_MetodiNuovi() {
		DatabaseMethods.aggiungiPersona("persona1", "persona1", "persona1", "persona1", 1);
		
		System.out.println("Operazione eseguita.\nPremere un tasto per terminare....");
		scanner.nextLine();
		
		DatabaseMethods.aggiungiPersona("persona2", "persona2","persona2", "persona2", 2);
		
		System.out.println("Operazione eseguita.\nPremere un tasto per terminare....");
		scanner.nextLine();
		
		DatabaseMethods.aggiungiAttivita("attivita1", 1);
		
		System.out.println("Operazione eseguita.\nPremere un tasto per terminare....");
		scanner.nextLine();
		
		DatabaseMethods.aggiungiAttivita("attivita2", 2);
		
		System.out.println("Operazione eseguita.\nPremere un tasto per terminare....");
		scanner.nextLine();
		
		DatabaseMethods.iscrizioneAttivita("attivita1", "persona1");
		
		System.out.println("Operazione eseguita.\nPremere un tasto per terminare....");
		scanner.nextLine();
		
		DatabaseMethods.iscrizioneAttivita("attivita2", "persona1");
		
		System.out.println("Operazione eseguita.\nPremere un tasto per terminare....");
		scanner.nextLine();
		
		DatabaseMethods.iscrizioneAttivita("attivita1", "persona2");
		
		System.out.println("Operazione eseguita.\nPremere un tasto per terminare....");
		scanner.nextLine();
		
		DatabaseMethods.iscrizioneAttivita("attivita2", "persona2");
		
		System.out.println("Operazione eseguita.\nPremere un tasto per terminare....");
		scanner.nextLine();
		
		DatabaseMethods.lasciaAttivita("attivita2", "persona1");
		
		System.out.println("Operazione eseguita.\nPremere un tasto per terminare....");
		scanner.nextLine();
		
		DatabaseMethods.rimuoviPersona("persona2");
		
		System.out.println("Operazione eseguita.\nPremere un tasto per terminare....");
		scanner.nextLine();
		
		DatabaseMethods.rimuoviAttivita("attivita2");
	}

//	private static void testDB() {
//
//		/*
//		 * Retrieve all saved objects
//		 */
//		List<Persona> persone = App.getAllPersone();
//		System.out.println("List of all persisted personas >>>");
//		for (Persona persona : persone) {
//			System.out.println(String.format("Persona> Nome: %s, Cognome: %s, Email: %s, Psw: %s",persona.getNome(),persona.getCognome(), persona.getEmail(),persona.getPassword()));
//		}
//	}

	// METODI DI HIBERNATE


	/**
	 * This method returns list of all persisted Persona objects/tuples from
	 * database
	 */
	public static List<Persona> getAllPersone() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Persona> persone = (List<Persona>) session.createQuery("FROM Persona p ORDER BY p.cognome ASC").list(); // "p" è un alias, usa i nomi delle classi e non le tabelle

		session.getTransaction().commit();
		session.close();
		return persone;
	}
}
