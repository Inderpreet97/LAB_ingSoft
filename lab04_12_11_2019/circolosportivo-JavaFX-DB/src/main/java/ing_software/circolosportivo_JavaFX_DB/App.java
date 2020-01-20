package ing_software.circolosportivo_JavaFX_DB;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

public class App {
	public static Attivita[] attivita;
	public static Persona[] circolo;
	public static Scanner scanner;

	public static void main(String[] args) {

		// Operazioni con il DB
		testDB();

		scanner = new Scanner(System.in);

		attivita = new Attivita[] { new Corso("calcio"), new Corso("tennis"), new Gara("Torneo di calcio"),
				new Gara("Torneo di tennis") };

		Amministratore pers1 = new Amministratore("Inderpreet", "Singh", "inderpreet@circolo.it", "indi123");
		Amministratore pers2 = new Amministratore("Mario", "Rossi", "mario@circolo.it", "mario123");
		Socio pers3 = new Socio("Pippo", "Baudo", "pippo@circolo.it", "pippo123");
		Socio pers4 = new Socio("Pluto", "Rossi", "pluto@circolo.it", "pluto123");

		circolo = new Persona[] { pers1, pers2, pers3, pers4 };

		// Situazione circolo all'avvio
		System.out.println("\n-------> LISTA PERSONE <-------");
		for (int index = 0; index < circolo.length; index++) {
			System.out.print(index + ")\t" + circolo[index].getNome() + "\t" + circolo[index].getCognome());
			System.out.println("\t" + circolo[index].getEmail());
		}

		System.out.println("\n-------> LISTA ATTIVITA <-------");
		for (int index = 0; index < attivita.length; index++) {
			System.out.print(index + ")\t" + attivita[index].getNome() + "\tPartecipatni { ");
			for (Persona p : attivita[index].getPersone()) {
				System.out.print(p.getNome() + ", ");
			}
			System.out.println(" }");
		}

		pers1.aggiungiPersona();
		pers1.rimuoviPersona();
		pers1.modificaPersona();

		pers3.iscrizioneAttivita();
		pers3.iscrizioneAttivita();
		pers3.lasciaAttivita();

		System.out.println("\n-------> LISTA PERSONE <-------");
		for (int index = 0; index < circolo.length; index++) {
			System.out.print(index + ")\t" + circolo[index].getNome() + "\t" + circolo[index].getCognome());
			System.out.println("\t" + circolo[index].getEmail());
		}

		System.out.println("\n-------> LISTA ATTIVITA <-------");
		for (int index = 0; index < attivita.length; index++) {
			System.out.print(index + ")\t" + attivita[index].getNome() + "\tPartecipatni { ");
			for (Persona p : attivita[index].getPersone()) {
				System.out.print(p.getNome() + ", ");
			}
			System.out.println(" }");
		}

		System.out.println("Premere un tasto per terminare....");
		scanner.nextLine();

		scanner.close();
	}

	private static void testDB() {
		/*
		 * Save few objects with hibernate
		 */
		String personaId1 = App.savePersona("abc","Sam", "Disilva", "2017-05-10");
		String personaId2 = App.savePersona("asd","Joshua", "Brill", "2017-05-15");
		String personaId3 = App.savePersona("qwe","Peter", "Pan", "2017-05-20");
		String personaId4 = App.savePersona("zxc","Bill", "Laurent", "2017-05-12");
		
		// Una riga di codice per togliere dei warning
		personaId1.compareTo(personaId3);
		/*
		 * Retrieve all saved objects
		 */
		List<Persona> persone = App.getAllPersone();
		System.out.println("List of all persisted personas >>>");
		for (Persona persona : persone) {
			System.out.println("Persisted persona :" + persona);
		}

		/*
		 * Update an object
		 */
		App.updatePersona(personaId4, "2017-05-18");

		/*
		 * Deletes an object
		 */
		App.deletePersona(personaId2);

		/*
		 * Retrieve all saved objects
		 */
		List<Persona> remaingPersone = App.getAllPersone();
		System.out.println("List of all remained persisted persone >>>");
		for (Persona persona : remaingPersone) {
			System.out.println("Persisted persona :" + persona);
		}
	}

	// METODI DI HIBERNATE

	/**
	 * This method saves a persona object in database
	 */
	public static String savePersona(String email, String firstName, String lastName, String password) {
		Persona persona = new Persona(firstName, lastName, email, password);

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		String returnEmail = (String) session.save(persona);
		session.getTransaction().commit();
		session.close();
		return returnEmail;
	}

	/**
	 * This method returns list of all persisted Persona objects/tuples from
	 * database
	 */
	public static List<Persona> getAllPersone() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		@SuppressWarnings("unchecked")
		List<Persona> persone = (List<Persona>) session.createQuery("FROM Persona s ORDER BY s.cognome ASC").list();

		session.getTransaction().commit();
		session.close();
		return persone;
	}

	/**
	 * This method updates a specific Persona object
	 */
	public static void updatePersona(String email, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Persona persona = (Persona) session.get(Persona.class, email);
		persona.setPassword(password);
		// session.update(employee);//No need to update manually as it will be updated
		// automatically on transaction close.
		session.getTransaction().commit();
		session.close();
	}

	/**
	 * This method deletes a specific Persona object
	 */
	public static void deletePersona(String personaId2) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Persona employee = (Persona) session.get(Persona.class, personaId2);
		session.delete(employee);
		session.getTransaction().commit();
		session.close();
	}

	// ------- NON TOCCARE QUESTI METODI -------

	public static Persona[] aggiungiElementoArray(Persona[] array, Persona oggetto) throws Exception {

		// Controlla se è già presente una persona con gli stessi attributi
		for (Persona p : array) {
			if (p.getNome().equals(oggetto.getNome()) && p.getCognome().equals(oggetto.getCognome())
					&& p.getEmail().equals(oggetto.getEmail())) {
				throw new Exception("Persona già presente.");
			}
		}

		// Aggiunge una nuova persona in coda
		Persona[] temp = new Persona[array.length + 1];
		System.arraycopy(array, 0, temp, 0, array.length);
		temp[array.length] = oggetto;
		return temp;

	}

	public static Attivita[] aggiungiElementoArray(Attivita[] array, Attivita oggetto) throws Exception {

		// Controlla se è già presente un'attività con lo stesso nome
		for (Attivita a : array) {
			if (a.getNome() == oggetto.getNome()) {
				throw new Exception("Attività già presente.");
			}
		}

		// Aggiunge una nuova attività in coda
		Attivita[] temp = new Attivita[array.length + 1];
		System.arraycopy(array, 0, temp, 0, array.length);
		temp[array.length] = oggetto;
		return temp;
	}

	public static Attivita[] rimuoviElementoArray(Attivita[] array, Attivita oggetto) throws Exception {

		int index = -1;

		// Cerca l'indice dell'oggetto da rimuovere
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(oggetto)) {
				index = i;
				break;
			}
		}

		if (index < 0) {
			throw new Exception("Attivita non trovata");
		}

		Attivita[] tempFirstToIndex = new Attivita[index];
		Attivita[] tempIndexToLast = new Attivita[array.length - index - 1];
		Attivita[] tempFinal = new Attivita[array.length - 1];

		// Divido in due l'array, isolando l'elemento nella posizione = index
		System.arraycopy(array, 0, tempFirstToIndex, 0, index);
		System.arraycopy(array, index + 1, tempIndexToLast, 0, array.length - index - 1);

		// Riunisco gli array senza l'elemento in posizione index
		System.arraycopy(tempFirstToIndex, 0, tempFinal, 0, tempFirstToIndex.length);
		System.arraycopy(tempIndexToLast, 0, tempFinal, index, tempIndexToLast.length);

		return tempFinal;
	}

	public static Persona[] rimuoviElementoArray(Persona[] array, Persona oggetto) throws Exception {

		int index = -1;

		// Cerca l'indice dell'oggetto da rimuovere
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(oggetto)) {
				index = i;
				break;
			}
		}

		if (index < 0) {
			throw new Exception("Persona non trovata");
		}

		Persona[] tempFirstToIndex = new Persona[index];
		Persona[] tempIndexToLast = new Persona[array.length - index - 1];
		Persona[] tempFinal = new Persona[array.length - 1];

		// Divido in due l'array, isolando l'elemento nella posizione = index
		System.arraycopy(array, 0, tempFirstToIndex, 0, index);
		System.arraycopy(array, index + 1, tempIndexToLast, 0, array.length - index - 1);

		// Riunisco gli array senza l'elemento in posizione index
		System.arraycopy(tempFirstToIndex, 0, tempFinal, 0, tempFirstToIndex.length);
		System.arraycopy(tempIndexToLast, 0, tempFinal, index, tempIndexToLast.length);

		return tempFinal;
	}

}
