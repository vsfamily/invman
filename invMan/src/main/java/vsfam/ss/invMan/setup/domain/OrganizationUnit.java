package vsfam.ss.invMan.setup.domain;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

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
@Table(name = "tab_organization_unit")
public class OrganizationUnit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1890398159433689800L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_unit_generator")
	@SequenceGenerator(name = "organization_unit_generator", sequenceName = "organization_unit_id_seq", allocationSize = 1)
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

	@ManyToOne
	@JoinColumn(name = "f_organization", nullable = false)
	private Organization organization;
	
	@ManyToOne
	@JoinColumn(name = "f_parent", nullable = false)
	private OrganizationUnit parent;
	
	@OneToMany(mappedBy="parent")
	private Set<OrganizationUnit> children = new TreeSet<OrganizationUnit>();
	
	@ManyToOne
	@JoinColumn(name = "f_organization_type", nullable = false)
	private OrganizationUnitType organizationUnitType;

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

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public OrganizationUnit getParent() {
		return parent;
	}

	public void setParent(OrganizationUnit parent) {
		this.parent = parent;
	}

	public Set<OrganizationUnit> getChildren() {
		return children;
	}

	public void setChildren(Set<OrganizationUnit> children) {
		this.children = children;
	}

	public OrganizationUnitType getOrganizationUnitType() {
		return organizationUnitType;
	}

	public void setOrganizationUnitType(OrganizationUnitType organizationUnitType) {
		this.organizationUnitType = organizationUnitType;
	}
	
	@Override
	public String toString() {
		String str = "organizationUnit ["; 
		str = str + "id=" + ((this.getId() == null)? "" : this.getId()) + ",";
		str = str + "shortName=" + ((this.getShortName() == null)? "" : this.getShortName()) + ",";
		str = str + "name=" + ((this.getName() == null)? "" : this.getName()) + ",";
		str = str + "location=" + ((this.getLocation() == null)? "" : this.getLocation()) + ",";
		str = str + "city=" + ((this.getCity() == null)? "" : this.getCity().toString()) + ",";
		str = str + "pin=" + ((this.getPin() == null)? "" : this.getPin()) + ",";
		str = str + "phone=" + ((this.getPhone() == null)? "" : this.getPhone()) + ",";
		str = str + "fax=" + ((this.getFax() == null)? "" : this.getFax()) + ",";
		str = str + "email=" + ((this.getEmail() == null)? "" : this.getEmail()) + ",";
		str = str + "websiteUrl=" + ((this.getWebsiteUrl() == null)? "" : this.getWebsiteUrl()) + ",";
		str = str + "organizationUnitType=" + ((this.getOrganizationUnitType() == null)? "" : this.getOrganizationUnitType().toString()) + ",";
		str = str + "organization=" + ((this.getOrganization() == null)? "" : this.getOrganization().toString()) + ",";
		str = str + "parent=" + ((this.getParent() == null)? "" : this.getParent()); 
		str = str + "]";		
		return str;
	}

	public String getAuditString() {
		return this.toString();
	}
	
}
