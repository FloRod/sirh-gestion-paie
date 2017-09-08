package dev.paie.entite;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.springframework.stereotype.Component;

@Entity
@Table(name="GRADES")
public class Grade {
	
	/** id : Integer */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	/** code : String */
	@Column(name="CODE")
	private String code;
	
	/** nbHeuresBase : BigDecimal */
	@Column(name="NB_HEURES_BASE")
	private BigDecimal nbHeuresBase;
	
	/** tauxBase : BigDecimal */
	@Column(name="TAUX_BASE")
	private BigDecimal tauxBase;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getNbHeuresBase() {
		return nbHeuresBase;
	}
	public void setNbHeuresBase(BigDecimal nbHeuresBase) {
		this.nbHeuresBase = nbHeuresBase;
	}
	public BigDecimal getTauxBase() {
		return tauxBase;
	}
	public void setTauxBase(BigDecimal tauxBase) {
		this.tauxBase = tauxBase;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grade other = (Grade) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
	
	

}
