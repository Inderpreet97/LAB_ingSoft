package ing_software.circolosportivo_JavaFX_DB;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="attivita")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="attivita_type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("null")
public class Attivita {
	
	@Id
	@Column(name = "nome", nullable = false)
	private String nome;
	
	private Persona[] persone;
	
	public Attivita() {	}
	
	public Attivita(String nome) {
		this.nome = nome;
		this.persone = new Persona[] {};
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Persona[] getPersone() {
		return persone;
	}
	
	/** TOOD Forse non servono, ricrodarsi di cancellare questi metodi se non usati
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
	 */

}
