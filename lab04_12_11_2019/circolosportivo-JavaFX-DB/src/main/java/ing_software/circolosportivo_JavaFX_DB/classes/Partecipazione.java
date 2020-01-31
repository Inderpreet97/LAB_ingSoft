package ing_software.circolosportivo_JavaFX_DB.classes;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "partecipazione")
@IdClass(PartecipazionePrimaryKey.class)

public class Partecipazione {

	private StringProperty nomeAttivita;

	private StringProperty emailPersona;

	public Partecipazione() {
	}

	public Partecipazione(String nomeAttivita, String emailPersona) {
		this.nomeAttivita = new SimpleStringProperty(nomeAttivita);
		this.emailPersona = new SimpleStringProperty(emailPersona);
	}

	@Id
	@Column(name = "attivita", nullable = false)
	public String getNomeAttivita() {
		return nomeAttivita.get();
	}

	public void setNomeAttivita(String nomeAttivita) {
		if (this.nomeAttivita == null) {
			this.nomeAttivita = new SimpleStringProperty(nomeAttivita);
		} else {
			this.nomeAttivita.set(nomeAttivita);
		}
	}

	@Id
	@Column(name = "persona", nullable = false)
	public String getEmailPersona() {
		return emailPersona.get();
	}

	public void setEmailPersona(String emailPersona) {
		if (this.emailPersona == null) {
			this.emailPersona = new SimpleStringProperty(emailPersona);
		} else {
			this.emailPersona.set(emailPersona);
		}

	}
}
