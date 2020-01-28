package ing_software.circolosportivo_JavaFX_DB.classes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Amministratore extends Socio {

	public Amministratore() {
		super();
	}
	
	public Amministratore(String nome, String cognome, String email, String password) {
		super(nome, cognome, email, password);
	}
}
