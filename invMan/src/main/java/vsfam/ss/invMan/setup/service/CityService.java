package vsfam.ss.invMan.setup.service;

import java.util.Calendar;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.manager.dao.AuditingRepo;
import vsfam.ss.invMan.manager.domain.Auditing;
import vsfam.ss.invMan.setup.dao.CityRepo;
import vsfam.ss.invMan.setup.domain.City;

@Service
public class CityService {
	@Autowired
	private AuditingRepo auditingRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Transactional
	public TransactionResult addCity(City city, String updateBy) {
		
		city = cityRepo.save(city);
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "ADDCity", city.getAuditString());
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(city, true);
	}

	@Transactional
	public TransactionResult deleteCity(Long id, String updateBy) {

		Optional<City> oe = this.cityRepo.findById(id);

		if (oe.isEmpty())
			return new TransactionResult(false, "No such record found.");

		City obj = oe.get();

		String auditString = obj.getAuditString();

		cityRepo.delete(obj);

		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "DELCity", auditString);
		this.auditingRepo.save(auditing);

		return new TransactionResult(true, "Record deleted successfully");
	}
	

	@Transactional
	public TransactionResult updateCity(City city, String updateBy) {
		
		Optional<City> oe = this.cityRepo.findById(city.getId());
		
		if (oe.isEmpty()) return new TransactionResult(false, "No such record found.");
		
		City obj = oe.get();
		
		String auditString = "Old=" + obj.getAuditString();
		
		obj.setName(city.getName());
		obj.setState(city.getState());
		
		obj = cityRepo.save(obj);
		
		auditString = auditString + ", New=" + obj.getAuditString();
		
		Auditing auditing = new Auditing(updateBy, Calendar.getInstance(), "UPDCity", auditString);
		this.auditingRepo.save(auditing);
		
		return new TransactionResult(obj, true);
	}
}
