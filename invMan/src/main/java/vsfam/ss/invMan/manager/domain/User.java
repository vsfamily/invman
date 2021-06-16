package vsfam.ss.invMan.manager.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tab_user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7013922393006144612L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name = "user_generator", sequenceName = "user_id_seq", allocationSize = 1)
	@Column(name = "f_id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "f_uid", updatable = false, nullable = false, unique = true, length = 20)
	private String uid;
	
	@Column(name = "f_full_name", nullable = false, length = 255)
	private String fullName;
	
	@Column(name = "f_password", nullable = false, length = 255)
	private String password;
	
	@Transient
	private String retypePassword;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="users")
	private Set<Role> roles;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="users")
	private Set<Group> groups;
	
	@Column(name = "f_account_non_expired", nullable = false)
	private boolean accountNonExpired = false;
	
	@Column(name = "f_account_non_locked", nullable = false)
	private boolean accountNonLocked = false;
	
	@Column(name = "f_credentials_non_expired", nullable = false)
	private boolean credentialsNonExpired = false;
	
	@Column(name = "f_enabled", nullable = false)
	private boolean enabled = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public String toString() {
		String str = "user ["; 
		str = str + "id=" + ((this.getId() == null)? "" : this.getId()) + ",";
		str = str + "uid=" + ((this.getUid() == null)? "" : this.getUid()) + ",";
		str = str + "fullName=" + ((this.getFullName() == null)? "" : this.getFullName()) + ",";
		str = str + "accountNonLocked=" + this.isAccountNonLocked() + ",";
		str = str + "accountNonExpired=" + this.isAccountNonExpired() + ",";
		str = str + "credentialsNonExpired=" + this.isCredentialsNonExpired() + ",";
		str = str + "enabled=" + this.isEnabled(); 
		str = str + "]";		
		return str;
	}

	public String getAuditString() {
		return this.toString();
	}
	
}
