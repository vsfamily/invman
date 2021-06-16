package vsfam.ss.invMan.setup.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tab_salutation")
public class Salutation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7348075548342924107L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "salutation_generator")
	@SequenceGenerator(name = "salutation_generator", sequenceName = "salutation_id_seq", allocationSize = 1)
	@Column(name = "f_id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "f_code", updatable = false, unique = true, nullable = false, length = 20)
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		String str = "salutation ["; 
		str = str + "id=" + ((this.getId() == null)? "" : this.getId()) + ",";
		str = str + "code=" + ((this.getCode() == null)? "" : this.getCode()); 
		str = str + "]";		
		return str;
	}

	public String getAuditString() {
		return this.toString();
	}
}
