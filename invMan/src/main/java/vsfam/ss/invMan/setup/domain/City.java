package vsfam.ss.invMan.setup.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tab_city")
public class City implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_generator")
	@SequenceGenerator(name = "city_generator", sequenceName = "city_id_seq", allocationSize = 1)
	@Column(name = "f_id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "f_code", updatable = false, nullable = false, unique = true, length = 20)
	private String code;
	
	@Column(name = "f_name", nullable = false, unique = true, length = 100)
	private String name;
	
	@ManyToOne
	@JoinColumn(name="f_state", nullable=false)
	private State state;

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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	@Override
	public String toString() {
		String str = "city ["; 
		str = str + "id=" + ((this.getId() == null)? "" : this.getId()) + ",";
		str = str + "code=" + ((this.getCode() == null)? "" : this.getCode()) + ",";
		str = str + "name=" + ((this.getName() == null)? "" : this.getName()) + ",";
		str = str + "state=" + ((this.getState() == null)? "" : this.getState().toString()); 
		str = str + "]";		
		return str;
	}

	public String getAuditString() {
		return this.toString();
	}
}
