package vsfam.ss.invMan.setup.service;

import java.util.Calendar;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.manager.dao.AuditingRepo;
import vsfam.ss.invMan.manager.domain.Auditing;
import vsfam.ss.invMan.setup.dao.OrganizationUnitTypeRepo;
import vsfam.ss.invMan.setup.domain.OrganizationUnitType;

@Service
public class OrganizationUnitTypeService {
	@Autowired
	private AuditingRepo auditingRepo;
	
	@Autowired
	private OrganizationUnitTypeRepo organizationUnitTypeRepo;
	
	@Transactional
	public TransactionResult addOrganizationUnitType(OrganizationUnitType organizationUnitType, String updateBy) {
		
		organizationUnitType = organizationUnitTypeRepo.save(organizationUnitType);
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "ADDOrganizationUnitType", organizationUnitType.getAuditString());
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(organizationUnitType, true);
	}

	@Transactional
	public TransactionResult deleteOrganizationUnitType(Long id, String updateBy) {

		Optional<OrganizationUnitType> oe = this.organizationUnitTypeRepo.findById(id);

		if (oe.isEmpty())
			return new TransactionResult(false, "No such record found.");

		OrganizationUnitType obj = oe.get();

		String auditString = obj.getAuditString();

		organizationUnitTypeRepo.delete(obj);

		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "DELOrganizationUnitType", auditString);
		this.auditingRepo.save(auditing);

		return new TransactionResult(true, "Record deleted successfully");
	}
	

	@Transactional
	public TransactionResult updateOrganizationUnitType(OrganizationUnitType organizationUnitType, String updateBy) {
		
		Optional<OrganizationUnitType> oe = this.organizationUnitTypeRepo.findById(organizationUnitType.getId());
		
		if (oe.isEmpty()) return new TransactionResult(false, "No such record found.");
		
		OrganizationUnitType obj = oe.get();
		
		String auditString = "Old=" + obj.getAuditString();
		
		obj.setDescription(organizationUnitType.getDescription());
		
		obj = organizationUnitTypeRepo.save(obj);
		
		auditString = auditString + ", New=" + obj.getAuditString();
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "UPDOrganizationUnitType", auditString);
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(obj, true);
	}
}
