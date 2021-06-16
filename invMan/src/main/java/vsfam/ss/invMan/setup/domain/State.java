package vsfam.ss.invMan.setup.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tab_state")
public class State implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7487948295498835061L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "state_generator")
	@SequenceGenerator(name = "state_generator", sequenceName = "state_id_seq", allocationSize = 1)
	@Column(name = "f_id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "f_code", unique = true, updatable = false, nullable = false, length = 20)
	private String code;
	
	@Column(name = "f_name", unique = true, nullable = false, length = 100)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "f_country", nullable = false)
	private Country country;
	
	@OneToMany(mappedBy="state")
	private Set<City> cities;

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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Set<City> getCities() {
		return cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}
	
	@Override
	public String toString() {
		String str = "state ["; 
		str = str + "id=" + ((this.getId() == null)? "" : this.getId()) + ",";
		str = str + "code=" + ((this.getCode() == null)? "" : this.getCode()) + ",";
		str = str + "name=" + ((this.getName() == null)? "" : this.getName()) + ",";
		str = str + "country=" + ((this.getCountry() == null)? "" : this.getCountry().toString()); 
		str = str + "]";		
		return str;
	}

	public String getAuditString() {
		return this.toString();
	}
	
}
