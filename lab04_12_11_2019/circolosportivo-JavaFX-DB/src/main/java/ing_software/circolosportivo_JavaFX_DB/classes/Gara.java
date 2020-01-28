package ing_software.circolosportivo_JavaFX_DB.classes;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Gara extends Attivita {
	
	public Gara() {
		super();
	}
	public Gara(String nome) {
		super(nome);
	}

}
