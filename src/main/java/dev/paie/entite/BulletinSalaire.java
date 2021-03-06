package dev.paie.entite;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

//import org.springframework.stereotype.Component;

@Entity
@Table(name="BULLETINS")
public class BulletinSalaire {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="EMP_ID")
	private RemunerationEmploye remunerationEmploye;
	
	@ManyToOne
	@JoinColumn(name="PER_ID")
	private Periode periode;
	
	@Column(name="PRIME_EXP")
	private BigDecimal primeExceptionnelle;
	
	@Column(name="DATE_CREATION")
	private LocalDateTime dateCreation;
	
	@Transient
	private ResultatCalculRemuneration resultatCalculRemuneration;

	/**
	 * @return the resultatCalculRemuneration
	 */
	public ResultatCalculRemuneration getResultatCalculRemuneration() {
		return resultatCalculRemuneration;
	}

	/**
	 * @param resultatCalculRemuneration the resultatCalculRemuneration to set
	 */
	public void setResultatCalculRemuneration(ResultatCalculRemuneration resultatCalculRemuneration) {
		this.resultatCalculRemuneration = resultatCalculRemuneration;
	}

	public RemunerationEmploye getRemunerationEmploye() {
		return remunerationEmploye;
	}

	public void setRemunerationEmploye(RemunerationEmploye remunerationEmploye) {
		this.remunerationEmploye = remunerationEmploye;
	}

	public Periode getPeriode() {
		return periode;
	}

	public void setPeriode(Periode periode) {
		this.periode = periode;
	}

	public BigDecimal getPrimeExceptionnelle() {
		return primeExceptionnelle;
	}

	public void setPrimeExceptionnelle(BigDecimal primeExceptionnelle) {
		this.primeExceptionnelle = primeExceptionnelle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the dateCreation
	 */
	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

}
