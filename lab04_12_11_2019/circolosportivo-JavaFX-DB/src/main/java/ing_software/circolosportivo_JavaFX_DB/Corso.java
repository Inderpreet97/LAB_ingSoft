package ing_software.circolosportivo_JavaFX_DB;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Corso")
public class Corso extends Attivita {

	public Corso(String nome) {
		super(nome);
	}

}
