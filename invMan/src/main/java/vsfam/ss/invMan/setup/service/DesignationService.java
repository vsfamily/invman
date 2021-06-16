package vsfam.ss.invMan.setup.service;

import java.util.Calendar;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.manager.dao.AuditingRepo;
import vsfam.ss.invMan.manager.domain.Auditing;
import vsfam.ss.invMan.setup.dao.DesignationRepo;
import vsfam.ss.invMan.setup.domain.Designation;

@Service
public class DesignationService {
	@Autowired
	private AuditingRepo auditingRepo;
	
	@Autowired
	private DesignationRepo designationRepo;
	
	@Transactional
	public TransactionResult addDesignation(Designation designation, String updateBy) {
		
		designation = designationRepo.save(designation);
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "ADDDesignation", designation.getAuditString());
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(designation, true);
	}

	@Transactional
	public TransactionResult deleteDesignation(Long id, String updateBy) {

		Optional<Designation> oe = this.designationRepo.findById(id);

		if (oe.isEmpty())
			return new TransactionResult(false, "No such record found.");

		Designation obj = oe.get();

		String auditString = obj.getAuditString();

		designationRepo.delete(obj);

		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "DELDesignation", auditString);
		this.auditingRepo.save(auditing);

		return new TransactionResult(true, "Record deleted successfully");
	}
	

	@Transactional
	public TransactionResult updateDesignation(Designation designation, String updateBy) {
		
		Optional<Designation> oe = this.designationRepo.findById(designation.getId());
		
		if (oe.isEmpty()) return new TransactionResult(false, "No such record found.");
		
		Designation obj = oe.get();
		
		String auditString = "Old=" + obj.getAuditString();
		
		obj.setDescription(designation.getDescription());
		
		obj = designationRepo.save(obj);
		
		auditString = auditString + ", New=" + obj.getAuditString();
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "UPDDesignation", auditString);
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(obj, true);
	}
}
