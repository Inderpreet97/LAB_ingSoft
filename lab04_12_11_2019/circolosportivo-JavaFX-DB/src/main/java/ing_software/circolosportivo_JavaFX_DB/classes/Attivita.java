package ing_software.circolosportivo_JavaFX_DB.classes;

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
	
	
	public Attivita() {	}
	
	public Attivita(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
