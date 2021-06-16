package vsfam.ss.invMan.setup.propertyEditors.stringToObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.dao.ParametersRepo;
import vsfam.ss.invMan.setup.domain.Parameters;

public class StringToParameters implements Converter<String, Parameters>{

	@Autowired
	private ParametersRepo repo;
	
	@Override
	public Parameters convert(String id) {
		try {
			
			return (Parameters) repo.findById(Long.valueOf(id)).orElse(null);
			
		} catch (Exception e) {
			return null;
		}
	}
}