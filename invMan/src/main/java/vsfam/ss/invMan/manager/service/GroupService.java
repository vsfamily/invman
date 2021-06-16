package vsfam.ss.invMan.manager.service;

import java.util.Calendar;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.manager.dao.AuditingRepo;
import vsfam.ss.invMan.manager.dao.GroupRepo;
import vsfam.ss.invMan.manager.domain.Auditing;
import vsfam.ss.invMan.manager.domain.Group;

@Service
public class GroupService {
	@Autowired
	private AuditingRepo auditingRepo;
	
	@Autowired
	private GroupRepo groupRepo;
	
	@Transactional
	public TransactionResult addGroup(Group group, String updateBy) {
		
		group = groupRepo.save(group);
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "ADDGroup", group.getAuditString());
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(group, true);
	}

	@Transactional
	public TransactionResult deleteGroup(Long id, String updateBy) {

		Optional<Group> oe = this.groupRepo.findById(id);

		if (oe.isEmpty())
			return new TransactionResult(false, "No such record found.");

		Group obj = oe.get();

		String auditString = obj.getAuditString();

		groupRepo.delete(obj);

		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "DELGroup", auditString);
		this.auditingRepo.save(auditing);

		return new TransactionResult(true, "Record deleted successfully");
	}
	

	@Transactional
	public TransactionResult updateGroup(Group group, String updateBy) {
		
		Optional<Group> oe = this.groupRepo.findById(group.getId());
		
		if (oe.isEmpty()) return new TransactionResult(false, "No such record found.");
		
		Group obj = oe.get();
		
		String auditString = "Old=" + obj.getAuditString();
		
		obj.setDescription(group.getDescription());
		
		obj = groupRepo.save(obj);
		
		auditString = auditString + ", New=" + obj.getAuditString();
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "UPDGroup", auditString);
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(obj, true);
	}
}
