package ing_software.circolosportivo_JavaFX_DB;

import java.util.List;

import org.hibernate.Session;

import ing_software.circolosportivo_JavaFX_DB.classes.Amministratore;
import ing_software.circolosportivo_JavaFX_DB.classes.Attivita;
import ing_software.circolosportivo_JavaFX_DB.classes.Corso;
import ing_software.circolosportivo_JavaFX_DB.classes.Gara;
import ing_software.circolosportivo_JavaFX_DB.classes.Partecipazione;
import ing_software.circolosportivo_JavaFX_DB.classes.PartecipazionePrimaryKey;
import ing_software.circolosportivo_JavaFX_DB.classes.Persona;
import ing_software.circolosportivo_JavaFX_DB.classes.Socio;

/**
 * Static Class with public methods used to query the DB
 */
public class DatabaseMethods {
	/**
	 * Cerca un Persona tramite email e password
	 * @param email
	 * @param password
	 * @return true if user found with the given email and password, false otherwise
	 */
	public static Boolean checkEmailPassoword(String email, String password) {
		try {
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Persona persona = (Persona) session.get(Persona.class, email);

			if (persona == null) {
				return false;
			}

			if (password.equals(persona.getPassword())) {
				return true;
			}

			session.getTransaction().commit();
			session.close();

			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Restituisce il tipo di account di una Persona
	 * @param email
	 * @return string representing the user type, null if user not found or in case of error
	 */
	public static String getPersonaType(String email) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Persona> personaList = (List<Persona>) session.createQuery("FROM Persona p WHERE p.email = :email")
					.setParameter("email", email).list();

			if (personaList == null) {
				return null;
			}

			if (personaList.get(0) instanceof Amministratore) {
				return "admin";
			}
			
			if (personaList.get(0) instanceof Socio) {
				return "socio";
			}

			session.getTransaction().commit();
			session.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Restituisce la lista delle attività alle quali l'utente è iscritto
	 * @param userEmail
	 * @return
	 */
	public static List<Partecipazione> getPartecipazioni(String userEmail) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Partecipazione> persone = (List<Partecipazione>) session
					.createQuery("FROM Partecipazione p WHERE p.emailPersona = :email").setParameter("email", userEmail)
					.list();

			session.getTransaction().commit();
			session.close();

			return persone;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @return Restituisce la lista di tutte le attività
	 */
	public static List<Attivita> getAttivitaList() {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Attivita> attivita = (List<Attivita>) session.createQuery("FROM Attivita").list();

			session.getTransaction().commit();
			session.close();

			return attivita;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Aggiunge una Persona nella tabella persona
	 * @param nome
	 * @param cognome
	 * @param email
	 * @param password
	 * @param personaType, 1 per Socio, 2 per Amministratore
	 * @return True se persona aggiunta correttamente, False altrimenti
	 */
	public static Boolean aggiungiPersona(String nome, String cognome, String email, String password, int personaType) {
		try {
			if (personaType == 1) { // aggiungi socio
				Socio persona = new Socio(nome, cognome, email, password);

				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();

				String returnEmail = (String) session.save(persona);

				if (returnEmail.equals(email)) {
					session.getTransaction().commit();
					session.close();
					return true;
				} else {
					session.getTransaction().rollback();
					session.close();
					return false;
				}

			} else if (personaType == 2) { // aggiungi amministratore
				Amministratore persona = new Amministratore(nome, cognome, email, password);

				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();

				String returnEmail = (String) session.save(persona);

				if (returnEmail.equals(email)) {
					session.getTransaction().commit();
					session.close();
					return true;
				} else {
					session.getTransaction().rollback();
					session.close();
					return false;
				}
			}

			// personaType non corretto
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Rimuove persona dalla tabella persona
	 * @param email
	 * @return True persona rimossa, False altrimenti
	 */
	public static Boolean rimuoviPersona(String email) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Persona persona = (Persona) session.get(Persona.class, email);

			if (persona == null) {
				throw new Exception("Persona non trovata");
			}

			session.delete(persona);
			session.getTransaction().commit();
			session.close();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Aggiorna i dati di una persona, non è possibile modificare l'email
	 * @param email
	 * @param nome
	 * @param cognome
	 * @param password
	 * @return True se i dati vengono aggiornati, False altrimenti
	 */
	public static Boolean modificaPersona(String email, String nome, String cognome, String password) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Persona persona = (Persona) session.get(Persona.class, email);

			if (persona == null) {
				throw new Exception("Persona non trovata");
			}

			if (!nome.isEmpty()) {
				persona.setNome(nome);
			}

			if (!cognome.isEmpty()) {
				persona.setCognome(cognome);
			}

			if (!password.isEmpty()) {
				persona.setPassword(password);
			}
			
			// session.update(persona);
			// No need to update manually as it will be updated automatically on transaction close.
			session.getTransaction().commit();
			session.close();

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Aggiunge un'attività
	 * @param nome
	 * @param attivitaType, 1 = Gara, 2 = Corso
	 * @return True se aggiunta correttamente, False altrimenti
	 */
	public static Boolean aggiungiAttivita(String nome, int attivitaType) {
		try {
			if (attivitaType == 1) { // aggiungi Gara
				Gara attivita = new Gara(nome);

				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();

				String returnNome = (String) session.save(attivita);

				if (returnNome.equals(nome)) {
					session.getTransaction().commit();
					session.close();
					return true;
				} else {
					session.getTransaction().rollback();
					session.close();
					return false;
				}

			} else if (attivitaType == 2) { // aggiungi Corso
				Corso attivita = new Corso(nome);

				Session session = HibernateUtil.getSessionFactory().openSession();
				session.beginTransaction();

				String returnNome = (String) session.save(attivita);

				if (returnNome.equals(nome)) {
					session.getTransaction().commit();
					session.close();
					return true;
				} else {
					session.getTransaction().rollback();
					session.close();
					return false;
				}
			}

			// attivitaType non corretto
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Rimuovi attivita
	 * @param nome
	 * @return True se rimossa correttamente, False altrimenti 
	 */
	public static Boolean rimuoviAttivita(String nome) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Attivita attivita = (Attivita) session.get(Attivita.class, nome);

			if (attivita == null) {
				throw new Exception("Attività non trovata");
			}

			session.delete(attivita);
			session.getTransaction().commit();
			session.close();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Iscrive una persona ad un'attività
	 * @param nomeAttivita
	 * @param emailPersona
	 * @return True se iscrizione avvenuta correttamente, False altrimenti
	 */
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
	
	/**
	 * Disiscrive una persona da un'attività
	 * @param nomeAttivita
	 * @param emailPersona
	 * @return True se disiscrizione avvenuta correttamente, False altrimenti
	 */
	public static Boolean lasciaAttivita(String nomeAttivita, String emailPersona) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Partecipazione partecipazione = (Partecipazione) session.get(Partecipazione.class,
					new PartecipazionePrimaryKey(nomeAttivita, emailPersona));

			if (partecipazione == null) {
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
	
	/**
	 * @return lista persone presenti nel DB
	 */
	public static List<Persona> getPersonaList() {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			@SuppressWarnings("unchecked")
			List<Persona> persone = (List<Persona>) session.createQuery("FROM Persona").list();

			session.getTransaction().commit();
			session.close();

			return persone;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
