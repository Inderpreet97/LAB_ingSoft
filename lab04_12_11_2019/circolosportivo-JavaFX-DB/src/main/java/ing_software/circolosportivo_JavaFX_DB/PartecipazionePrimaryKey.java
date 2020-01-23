package ing_software.circolosportivo_JavaFX_DB;

import java.io.Serializable;

public class PartecipazionePrimaryKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nomeAttivita;
	private String emailPersona;
	
	public PartecipazionePrimaryKey() {}
	
	public PartecipazionePrimaryKey(String nomeAttivita, String emailPersona){
		this.setNomeAttivita(nomeAttivita);
		this.setEmailPersona(emailPersona);
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
