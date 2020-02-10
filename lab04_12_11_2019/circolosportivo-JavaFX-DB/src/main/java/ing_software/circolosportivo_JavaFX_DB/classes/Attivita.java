package ing_software.circolosportivo_JavaFX_DB.classes;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Access(AccessType.PROPERTY)
@Table(name="attivita")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="attivita_type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("null")

public class Attivita {
	
	private StringProperty nome;
	
	// Property usata nella tabella in gestione attività
	private StringProperty tipo;
	
	public Attivita() {	}
	
	public Attivita(String nome) {
		this.nome = new SimpleStringProperty(nome);
	}
	
	@Id
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

	@Transient
	public String getTipo() {
		return tipo.get();
	}

	public void setTipo(String tipo) {
		if (this.tipo == null) {
			this.tipo = new SimpleStringProperty(tipo);
		} else {
			this.tipo.set(tipo);
		}
	}
}
