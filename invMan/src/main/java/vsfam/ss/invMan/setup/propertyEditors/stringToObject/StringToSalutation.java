package vsfam.ss.invMan.setup.propertyEditors.stringToObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.dao.SalutationRepo;
import vsfam.ss.invMan.setup.domain.Salutation;

public class StringToSalutation implements Converter<String, Salutation>{

	@Autowired
	private SalutationRepo repo;
	
	@Override
	public Salutation convert(String id) {
		try {
			
			return (Salutation) repo.findById(Long.valueOf(id)).orElse(null);
			
		} catch (Exception e) {
			return null;
		}
	}
}