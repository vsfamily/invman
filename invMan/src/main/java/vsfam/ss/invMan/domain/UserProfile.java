package vsfam.ss.invMan.domain;

import java.io.Serializable;

import vsfam.ss.invMan.setup.domain.City;
import vsfam.ss.invMan.setup.domain.Designation;
import vsfam.ss.invMan.setup.domain.OrganizationUnit;

public class UserProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uid;
	
	private String firstName;
	
	private String middleName;
	
	private String lastName;
	
	private String resLocation;
	
	private City resCity;
	
	private Long resPin;
	
	private String resPhone;
	
	private String mobile;
	
	private String email;
	
	private OrganizationUnit organizationUnit;
	
	private String offPhone;
	
	private String offEmail;
	
	private Designation designation;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getResLocation() {
		return resLocation;
	}

	public void setResLocation(String resLocation) {
		this.resLocation = resLocation;
	}

	public City getResCity() {
		return resCity;
	}

	public void setResCity(City resCity) {
		this.resCity = resCity;
	}

	public Long getResPin() {
		return resPin;
	}

	public void setResPin(Long resPin) {
		this.resPin = resPin;
	}

	public String getResPhone() {
		return resPhone;
	}

	public void setResPhone(String resPhone) {
		this.resPhone = resPhone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public String getOffPhone() {
		return offPhone;
	}

	public void setOffPhone(String offPhone) {
		this.offPhone = offPhone;
	}

	public String getOffEmail() {
		return offEmail;
	}

	public void setOffEmail(String offEmail) {
		this.offEmail = offEmail;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}
	
}
