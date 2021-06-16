package vsfam.ss.invMan.manager.service;

import java.util.Calendar;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.manager.dao.AuditingRepo;
import vsfam.ss.invMan.manager.dao.RoleRepo;
import vsfam.ss.invMan.manager.domain.Auditing;
import vsfam.ss.invMan.manager.domain.Role;

@Service
public class RoleService {

	@Autowired
	private AuditingRepo auditingRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Transactional
	public TransactionResult addRole(Role role, String updateBy) {
		
		role = roleRepo.save(role);
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "ADDRole", role.getAuditString());
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(role, true);
	}

	@Transactional
	public TransactionResult deleteRole(Long id, String updateBy) {

		Optional<Role> oe = this.roleRepo.findById(id);

		if (oe.isEmpty())
			return new TransactionResult(false, "No such record found.");

		Role obj = oe.get();

		String auditString = obj.getAuditString();

		roleRepo.delete(obj);

		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "DELRole", auditString);
		this.auditingRepo.save(auditing);

		return new TransactionResult(true, "Record deleted successfully");
	}
	

	@Transactional
	public TransactionResult updateRole(Role role, String updateBy) {
		
		Optional<Role> oe = this.roleRepo.findById(role.getId());
		
		if (oe.isEmpty()) return new TransactionResult(false, "No such record found.");
		
		Role obj = oe.get();
		
		String auditString = "Old=" + obj.getAuditString();
		
		obj.setDescription(role.getDescription());
		
		obj = roleRepo.save(obj);
		
		auditString = auditString + ", New=" + obj.getAuditString();
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "UPDRole", auditString);
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(obj, true);
	}
}
