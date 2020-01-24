package ing_software.circolosportivo_JavaFX_DB;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Corso extends Attivita {
	
	public Corso() {
		super();
	}

	public Corso(String nome) {
		super(nome);
	}

}
