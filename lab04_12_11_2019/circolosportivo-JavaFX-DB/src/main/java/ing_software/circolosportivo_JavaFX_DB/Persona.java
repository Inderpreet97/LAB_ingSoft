package ing_software.circolosportivo_JavaFX_DB;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Table;

@Entity
@Table(name="persona")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // In che modo salvare le sottoclassi -> unica tabella con un discriminatore
@DiscriminatorColumn(name="persona_type", discriminatorType = DiscriminatorType.INTEGER) // Discriminatore intero -> "1" è Socio, "2" è Amministratore
@DiscriminatorValue("null") // Se nel caso in una riga nella colonna "persona_type" ci dovesse essere un null la riga verrebbe mappata di default in Persona
public class Persona {
	@Id
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "cognome", nullable = false)
	private String cognome;
	
	@Column(name = "password", nullable = false)
	private String password;

	public Persona() {

	}
	
	public Persona(String nome, String cognome, String email, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
