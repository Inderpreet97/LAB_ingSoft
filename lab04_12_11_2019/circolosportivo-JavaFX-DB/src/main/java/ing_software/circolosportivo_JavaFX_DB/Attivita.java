package ing_software.circolosportivo_JavaFX_DB;

public abstract class Attivita {
	private String Nome;
	private Persona[] persone;

	public Attivita(String nome) {
		Nome = nome;
		this.persone = new Persona[] {};
	}

	public Boolean aggiungPersona(Persona persona) {
		try {
			this.persone = App.aggiungiElementoArray(this.persone, persona);
			return true;
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			return false;
		}
	}

	public Boolean rimuoviPersona(Persona persona) {
		try {
			this.persone = App.rimuoviElementoArray(this.persone, persona);
			return true;
		} catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			return false;
		}

	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public Persona[] getPersone() {
		return persone;
	}

}
