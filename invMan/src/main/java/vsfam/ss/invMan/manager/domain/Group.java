package vsfam.ss.invMan.manager.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

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
@Table(name="tab_group")
public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1462056282390832867L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_generator")
	@SequenceGenerator(name = "group_generator", sequenceName = "group_id_seq", allocationSize = 1)
	@Column(name = "f_id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "f_code", updatable = false, nullable = false, unique = true, length = 20)
	private String code;
	
	@Column(name = "f_description", nullable = false, length = 100)
	private String description;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="groups")
	private Set<Role> roles = new TreeSet<Role>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name="tab_group_user", 
			joinColumns=@JoinColumn(name="f_group_id"),
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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	@Override
	public String toString() {
		String str = "group ["; 
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
