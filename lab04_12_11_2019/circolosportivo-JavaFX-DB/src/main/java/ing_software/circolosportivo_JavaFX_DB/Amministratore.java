package ing_software.circolosportivo_JavaFX_DB;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.Session;

@Entity
@DiscriminatorValue("2")
public class Amministratore extends Socio {

	public Amministratore() {
		super();
	}
	
	public Amministratore(String nome, String cognome, String email, String password) {
		super(nome, cognome, email, password);
	}

	// Aggiunge un socio o un Amministrare nella tabella persona
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

	// Rimuove persona dalla tabella persona
	public static Boolean rimuoviPersona(String email) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Persona persona = (Persona) session.get(Persona.class, email);
			
			if(persona == null) {
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

	// Aggiorna i dati di una persona
	public static Boolean modificaPersona(String oldEmail, String email, String nome, String cognome, String password) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Persona persona = (Persona) session.get(Persona.class, oldEmail);
			
			if(persona == null) {
				throw new Exception("Persona non trovata");
			}
			
			if(!email.isEmpty()) {
				persona.setEmail(email);
			}
			
			if(!nome.isEmpty()) {
				persona.setNome(nome);
			}
			
			if(!cognome.isEmpty()) {
				persona.setCognome(cognome);
			}
			
			if(!password.isEmpty()) {
				persona.setPassword(password);
			}
			
			// session.update(persona);//No need to update manually as it will be updated
			// automatically on transaction close.
			session.getTransaction().commit();
			session.close();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// Aggiunge Corso o Gara
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

	// Rimuovi attivita
	public static Boolean rimuoviAttivita(String nome) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Attivita attivita = (Attivita) session.get(Attivita.class, nome);
			
			if(attivita == null) {
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

	public static Boolean modificaAttivita(String oldName, String nome) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();

			Attivita attivita = (Attivita) session.get(Attivita.class, oldName);
			
			if(attivita == null) {
				throw new Exception("attivita non trovata");
			}
			
			if(!nome.isEmpty()) {
				attivita.setNome(nome);
			}
			
			// session.update(Attivita);//No need to update manually as it will be updated
			// automatically on transaction close.
			session.getTransaction().commit();
			session.close();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
