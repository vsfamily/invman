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
@Table(name = "tab_organization")
public class Organization implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7469462159832816687L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_generator")
	@SequenceGenerator(name = "organization_generator", sequenceName = "organization_id_seq", allocationSize = 1)
	@Column(name = "f_id", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "f_short_name", updatable = false, nullable = false, unique = true, length = 20)
	private String shortName;
	
	@Column(name = "f_name", nullable = false, unique = true, length = 500)
	private String name;
	
	@Column(name = "f_location", nullable = true, length = 500)
	private String location;
	
	@ManyToOne
	@JoinColumn(name = "f_city", nullable = false)
	private City city;
	
	@Column(name = "f_pin")
	private Long pin;
	
	@Column(name = "f_phone", length = 20)
	private String phone;
	
	@Column(name = "f_fax", length = 20)
	private String fax;
	
	@Column(name = "f_email", length=255)
	private String email;
	
	@Column(name = "f_website_url", length=255)
	private String websiteUrl;
	
	@OneToMany(mappedBy="organization")
	private Set<OrganizationUnit> organizationUnits;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Long getPin() {
		return pin;
	}

	public void setPin(Long pin) {
		this.pin = pin;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	public Set<OrganizationUnit> getOrganizationUnits() {
		return organizationUnits;
	}

	public void setOrganizationUnits(Set<OrganizationUnit> organizationUnits) {
		this.organizationUnits = organizationUnits;
	}
	
	@Override
	public String toString() {
		String str = "organization ["; 
		str = str + "id=" + ((this.getId() == null)? "" : this.getId()) + ",";
		str = str + "shortName=" + ((this.getShortName() == null)? "" : this.getShortName()) + ",";
		str = str + "name=" + ((this.getName() == null)? "" : this.getName()) + ",";
		str = str + "location=" + ((this.getLocation() == null)? "" : this.getLocation()) + ",";
		str = str + "city=" + ((this.getCity() == null)? "" : this.getCity().toString()) + ",";
		str = str + "pin=" + ((this.getPin() == null)? "" : this.getPin()) + ",";
		str = str + "phone=" + ((this.getPhone() == null)? "" : this.getPhone()) + ",";
		str = str + "fax=" + ((this.getFax() == null)? "" : this.getFax()) + ",";
		str = str + "email=" + ((this.getEmail() == null)? "" : this.getEmail()) + ",";
		str = str + "websiteUrl=" + ((this.getWebsiteUrl() == null)? "" : this.getWebsiteUrl()); 
		str = str + "]";		
		return str;
	}

	public String getAuditString() {
		return this.toString();
	}

}
