package ing_software.circolosportivo_JavaFX_DB;

import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("2")
public class Corso extends Attivita {

	public Corso(String nome) {
		super(nome);
	}

}
