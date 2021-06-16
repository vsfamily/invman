package vsfam.ss.invMan.setup.propertyEditors.stringToObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.dao.CountryRepo;
import vsfam.ss.invMan.setup.domain.Country;

public class StringToCountry implements Converter<String, Country>{

	@Autowired
	private CountryRepo repo;
	
	@Override
	public Country convert(String id) {
		try {
			
			return (Country) repo.findById(Long.valueOf(id)).orElse(null);
			
		} catch (Exception e) {
			return null;
		}
	}
}
