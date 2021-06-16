package vsfam.ss.invMan.setup.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tab_country")
public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1629576229875376086L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_generator")
	@SequenceGenerator(name = "country_generator", sequenceName = "country_id_seq", allocationSize = 1)
	@Column(name = "f_id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "f_code", unique = true, updatable = false, nullable = false, length = 20)
	private String code;
	
	@Column(name = "f_name", unique = true, nullable = false, length = 100)
	private String name;
	
	@OneToMany(mappedBy="country")
	private Set<State> states;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<State> getStates() {
		return states;
	}

	public void setStates(Set<State> states) {
		this.states = states;
	}
	
	@Override
	public String toString() {
		String str = "country ["; 
		str = str + "id=" + ((this.getId() == null)? "" : this.getId()) + ",";
		str = str + "code=" + ((this.getCode() == null)? "" : this.getCode()) + ",";
		str = str + "name=" + ((this.getName() == null)? "" : this.getName());
		str = str + "]";		
		return str;
	}

	public String getAuditString() {
		return this.toString();
	}
}
