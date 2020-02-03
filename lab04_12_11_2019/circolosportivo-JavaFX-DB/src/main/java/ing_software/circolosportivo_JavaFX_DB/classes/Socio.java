package ing_software.circolosportivo_JavaFX_DB.classes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Socio extends Persona {

	public Socio(String nome, String cognome, String email, String password) {
		super(nome, cognome, email, password);
	}

	public Socio() {
		super();
	}
}
