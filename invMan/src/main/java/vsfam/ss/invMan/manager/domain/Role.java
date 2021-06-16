package vsfam.ss.invMan.manager.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tab_role")
public class Role implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 855066159267854057L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
	@SequenceGenerator(name = "role_generator", sequenceName = "role_id_seq", allocationSize = 1)
	@Column(name = "f_id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "f_code", updatable = false, nullable = false, unique = true, length = 20)
	private String code;
	
	@Column(name = "f_description", nullable = false, length = 100)
	private String description;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name="tab_group_role", 
			joinColumns=@JoinColumn(name="f_role_id"),
			inverseJoinColumns=@JoinColumn(name="f_group_id"))
	private Set<Group> groups;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name="tab_user_role", 
			joinColumns=@JoinColumn(name="f_role_id"),
			inverseJoinColumns=@JoinColumn(name="f_user_id"))
	private Set<User> users;

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

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	@Override
	public String toString() {
		String str = "role ["; 
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
