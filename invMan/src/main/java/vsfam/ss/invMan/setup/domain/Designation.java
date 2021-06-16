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
@Table(name = "tab_designation")
public class Designation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9211413714964329658L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "designation_generator")
	@SequenceGenerator(name = "designation_generator", sequenceName = "designation_id_seq", allocationSize = 1)
	@Column(name = "f_id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "f_code", unique = true, updatable = false, nullable = false, length = 20)
	private String code;
	
	@Column(name = "f_description", unique = true, nullable = false, length = 100)
	private String description;

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
		this.code = code.toUpperCase();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		String str = "designation ["; 
		str = str + "id=" + ((this.getId() == null)? "" : this.getId()) + ",";
		str = str + "code=" + ((this.getCode() == null)? "" : this.getCode()) + ",";
		str = str + "description=" + ((this.getDescription() == null)? "" : this.getDescription()); 
		str = str + "]";		
		return str;
	}

	public String getAuditString() {
		return this.toString();
	}
	
}
