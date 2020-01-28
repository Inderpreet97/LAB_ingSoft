package ing_software.circolosportivo_JavaFX_DB.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="partecipazione")
@IdClass(PartecipazionePrimaryKey.class)
public class Partecipazione {
	@Id
	@Column(name = "attivita", nullable = false)
	private String nomeAttivita;
	@Id
	@Column(name = "persona", nullable = false)
	private String emailPersona;
	
	public Partecipazione() {}
	
	public Partecipazione(String nomeAttivita, String emailPersona) {
		this.nomeAttivita = nomeAttivita;
		this.emailPersona = emailPersona;
	}

	public String getNomeAttivita() {
		return nomeAttivita;
	}

	public void setNomeAttivita(String nomeAttivita) {
		this.nomeAttivita = nomeAttivita;
	}

	public String getEmailPersona() {
		return emailPersona;
	}

	public void setEmailPersona(String emailPersona) {
		this.emailPersona = emailPersona;
	}
}
