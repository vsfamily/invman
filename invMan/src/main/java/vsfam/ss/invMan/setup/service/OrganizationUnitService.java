package vsfam.ss.invMan.setup.service;

import java.util.Calendar;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.manager.dao.AuditingRepo;
import vsfam.ss.invMan.manager.domain.Auditing;
import vsfam.ss.invMan.setup.dao.OrganizationUnitRepo;
import vsfam.ss.invMan.setup.domain.OrganizationUnit;

@Service
public class OrganizationUnitService {
	@Autowired
	private AuditingRepo auditingRepo;
	
	@Autowired
	private OrganizationUnitRepo organizationUnitRepo;
	
	@Transactional
	public TransactionResult addOrganizationUnit(OrganizationUnit organizationUnit, String updateBy) {
		
		organizationUnit = organizationUnitRepo.save(organizationUnit);
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "ADDOrganizationUnit", organizationUnit.getAuditString());
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(organizationUnit, true);
	}

	@Transactional
	public TransactionResult deleteOrganizationUnit(Long id, String updateBy) {

		Optional<OrganizationUnit> oe = this.organizationUnitRepo.findById(id);

		if (oe.isEmpty())
			return new TransactionResult(false, "No such record found.");

		OrganizationUnit obj = oe.get();

		String auditString = obj.getAuditString();

		organizationUnitRepo.delete(obj);

		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "DELOrganizationUnit", auditString);
		this.auditingRepo.save(auditing);

		return new TransactionResult(true, "Record deleted successfully");
	}
	

	@Transactional
	public TransactionResult updateOrganizationUnit(OrganizationUnit organizationUnit, String updateBy) {
		
		Optional<OrganizationUnit> oe = this.organizationUnitRepo.findById(organizationUnit.getId());
		
		if (oe.isEmpty()) return new TransactionResult(false, "No such record found.");
		
		OrganizationUnit obj = oe.get();
		
		String auditString = "Old=" + obj.getAuditString();
		
		obj.setName(organizationUnit.getName());
		obj.setLocation(organizationUnit.getLocation());
		obj.setCity(organizationUnit.getCity());
		obj.setPin(organizationUnit.getPin());
		obj.setPhone(organizationUnit.getPhone());
		obj.setFax(organizationUnit.getFax());
		obj.setEmail(organizationUnit.getEmail());
		obj.setWebsiteUrl(organizationUnit.getWebsiteUrl());
		obj.setParent(organizationUnit.getParent());
		obj.setOrganization(organizationUnit.getOrganization());
		obj.setOrganizationUnitType(organizationUnit.getOrganizationUnitType());
		
		obj = organizationUnitRepo.save(obj);
		
		auditString = auditString + ", New=" + obj.getAuditString();
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "UPDOrganizationUnit", auditString);
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(obj, true);
	}
}
