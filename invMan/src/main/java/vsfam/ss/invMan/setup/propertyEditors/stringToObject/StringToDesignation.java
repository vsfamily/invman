package vsfam.ss.invMan.setup.propertyEditors.stringToObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.dao.DesignationRepo;
import vsfam.ss.invMan.setup.domain.Designation;

public class StringToDesignation implements Converter<String, Designation>{

	@Autowired
	private DesignationRepo repo;
	
	@Override
	public Designation convert(String id) {
		try {
			
			return (Designation) repo.findById(Long.valueOf(id)).orElse(null);
			
		} catch (Exception e) {
			return null;
		}
	}
}
