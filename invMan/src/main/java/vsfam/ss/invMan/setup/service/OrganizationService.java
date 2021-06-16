package vsfam.ss.invMan.setup.service;

import java.util.Calendar;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.manager.dao.AuditingRepo;
import vsfam.ss.invMan.manager.domain.Auditing;
import vsfam.ss.invMan.setup.dao.OrganizationRepo;
import vsfam.ss.invMan.setup.domain.Organization;

@Service
public class OrganizationService {
	@Autowired
	private AuditingRepo auditingRepo;
	
	@Autowired
	private OrganizationRepo organizationRepo;
	
	@Transactional
	public TransactionResult addOrganization(Organization organization, String updateBy) {
		
		organization = organizationRepo.save(organization);
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "ADDOrganization", organization.getAuditString());
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(organization, true);
	}

	@Transactional
	public TransactionResult deleteOrganization(Long id, String updateBy) {

		Optional<Organization> oe = this.organizationRepo.findById(id);

		if (oe.isEmpty())
			return new TransactionResult(false, "No such record found.");

		Organization obj = oe.get();

		String auditString = obj.getAuditString();

		organizationRepo.delete(obj);

		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "DELOrganization", auditString);
		this.auditingRepo.save(auditing);

		return new TransactionResult(true, "Record deleted successfully");
	}
	

	@Transactional
	public TransactionResult updateOrganization(Organization organization, String updateBy) {
		
		Optional<Organization> oe = this.organizationRepo.findById(organization.getId());
		
		if (oe.isEmpty()) return new TransactionResult(false, "No such record found.");
		
		Organization obj = oe.get();
		
		String auditString = "Old=" + obj.getAuditString();
		
		obj.setName(organization.getName());
		obj.setLocation(organization.getLocation());
		obj.setCity(organization.getCity());
		obj.setPin(organization.getPin());
		obj.setPhone(organization.getPhone());
		obj.setFax(organization.getFax());
		obj.setEmail(organization.getEmail());
		obj.setWebsiteUrl(organization.getWebsiteUrl());
		
		obj = organizationRepo.save(obj);
		
		auditString = auditString + ", New=" + obj.getAuditString();
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "UPDOrganization", auditString);
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(obj, true);
	}
}

