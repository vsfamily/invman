package vsfam.ss.invMan.setup.service;

import java.util.Calendar;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.manager.dao.AuditingRepo;
import vsfam.ss.invMan.manager.domain.Auditing;
import vsfam.ss.invMan.setup.dao.CountryRepo;
import vsfam.ss.invMan.setup.domain.Country;

@Service
public class CountryService {
	@Autowired
	private AuditingRepo auditingRepo;
	
	@Autowired
	private CountryRepo countryRepo;
	
	@Transactional
	public TransactionResult addCountry(Country country, String updateBy) {
		
		country = countryRepo.save(country);
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "ADDCountry", country.getAuditString());
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(country, true);
	}

	@Transactional
	public TransactionResult deleteCountry(Long id, String updateBy) {

		Optional<Country> oe = this.countryRepo.findById(id);

		if (oe.isEmpty())
			return new TransactionResult(false, "No such record found.");

		Country obj = oe.get();

		String auditString = obj.getAuditString();

		countryRepo.delete(obj);

		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "DELCountry", auditString);
		this.auditingRepo.save(auditing);

		return new TransactionResult(true, "Record deleted successfully");
	}
	

	@Transactional
	public TransactionResult updateCountry(Country country, String updateBy) {
		
		Optional<Country> oe = this.countryRepo.findById(country.getId());
		
		if (oe.isEmpty()) return new TransactionResult(false, "No such record found.");
		
		Country obj = oe.get();
		
		String auditString = "Old=" + obj.getAuditString();
		
		obj.setName(country.getName());
		
		obj = countryRepo.save(obj);
		
		auditString = auditString + ", New=" + obj.getAuditString();
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "UPDCountry", auditString);
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(obj, true);
	}
}
