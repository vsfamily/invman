package vsfam.ss.invMan.setup.service;

import java.util.Calendar;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.manager.dao.AuditingRepo;
import vsfam.ss.invMan.manager.domain.Auditing;
import vsfam.ss.invMan.setup.dao.StateRepo;
import vsfam.ss.invMan.setup.domain.State;

@Service
public class StateService {
	@Autowired
	private AuditingRepo auditingRepo;
	
	@Autowired
	private StateRepo stateRepo;
	
	@Transactional
	public TransactionResult addState(State state, String updateBy) {
		
		state = stateRepo.save(state);
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "ADDState", state.getAuditString());
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(state, true);
	}

	@Transactional
	public TransactionResult deleteState(Long id, String updateBy) {

		Optional<State> oe = this.stateRepo.findById(id);

		if (oe.isEmpty())
			return new TransactionResult(false, "No such record found.");

		State obj = oe.get();

		String auditString = obj.getAuditString();

		stateRepo.delete(obj);

		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "DELState", auditString);
		this.auditingRepo.save(auditing);

		return new TransactionResult(true, "Record deleted successfully");
	}
	

	@Transactional
	public TransactionResult updateState(State state, String updateBy) {
		
		Optional<State> oe = this.stateRepo.findById(state.getId());
		
		if (oe.isEmpty()) return new TransactionResult(false, "No such record found.");
		
		State obj = oe.get();
		
		String auditString = "Old=" + obj.getAuditString();
		
		obj.setName(state.getName());
		obj.setCountry(state.getCountry());
		
		obj = stateRepo.save(obj);
		
		auditString = auditString + ", New=" + obj.getAuditString();
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "UPDState", auditString);
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(obj, true);
	}
}
