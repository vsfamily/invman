package vsfam.ss.invMan.setup.service;

import java.util.Calendar;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.manager.dao.AuditingRepo;
import vsfam.ss.invMan.manager.domain.Auditing;
import vsfam.ss.invMan.setup.dao.SalutationRepo;
import vsfam.ss.invMan.setup.domain.Salutation;

@Service
public class SalutationService {

	@Autowired
	private AuditingRepo auditingRepo;
	
	@Autowired
	private SalutationRepo salutationRepo;
	
	@Transactional
	public TransactionResult addSalutation(Salutation salutation, String updateBy) {
		
		salutation = salutationRepo.save(salutation);
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "ADDSalutation", salutation.getAuditString());
		
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(salutation, true);
	}

	@Transactional
	public TransactionResult deleteSalutation(Long id, String updateBy) {

		Optional<Salutation> oe = this.salutationRepo.findById(id);

		if (oe.isEmpty())
			return new TransactionResult(false, "No such record found.");

		Salutation obj = oe.get();

		String auditString = obj.getAuditString();

		salutationRepo.delete(obj);

		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "DELSalutation", auditString);
		this.auditingRepo.save(auditing);

		return new TransactionResult(true, "Record deleted successfully");
	}
}
