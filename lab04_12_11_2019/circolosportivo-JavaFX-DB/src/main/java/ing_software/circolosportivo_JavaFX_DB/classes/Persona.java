package ing_software.circolosportivo_JavaFX_DB.classes;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // In che modo salvare le sottoclassi -> unica tabella con un
														// discriminatore
@DiscriminatorColumn(name = "persona_type", discriminatorType = DiscriminatorType.INTEGER) // Discriminatore intero ->
																							// "1" è Socio, "2" è
																							// Amministratore
@DiscriminatorValue("null") // Se nel caso in una riga nella colonna "persona_type" ci dovesse essere un
							// null la riga verrebbe mappata di default in Persona
public class Persona {

	private StringProperty email;
	private StringProperty nome;
	private StringProperty cognome;
	private StringProperty password;

	public Persona() {

	}

	public Persona(String nome, String cognome, String email, String password) {
		this.nome = new SimpleStringProperty(nome);
		this.cognome = new SimpleStringProperty(cognome);
		this.email = new SimpleStringProperty(email);
		this.password = new SimpleStringProperty(password);
	}

	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		if (this.nome == null) {
			this.nome = new SimpleStringProperty(nome);
		} else {
			this.nome.set(nome);
		}
	}

	@Column(name = "cognome", nullable = false)
	public String getCognome() {
		return cognome.get();
	}

	public void setCognome(String cognome) {
		if (this.cognome == null) {
			this.cognome = new SimpleStringProperty(cognome);
		} else {
			this.cognome.set(cognome);
		}
	}

	@Id
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		if (this.email == null) {
			this.email = new SimpleStringProperty(email);
		} else {
			this.email.set(email);
		}
	}

	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password.get();
	}

	public void setPassword(String password) {
		if (this.password == null) {
			this.password = new SimpleStringProperty(password);
		} else {
			this.password.set(password);
		}
	}
}
