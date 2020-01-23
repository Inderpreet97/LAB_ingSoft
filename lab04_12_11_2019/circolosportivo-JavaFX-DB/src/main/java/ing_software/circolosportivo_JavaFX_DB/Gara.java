package ing_software.circolosportivo_JavaFX_DB;

import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("1")
public class Gara extends Attivita {

	public Gara(String nome) {
		super(nome);
	}

}
