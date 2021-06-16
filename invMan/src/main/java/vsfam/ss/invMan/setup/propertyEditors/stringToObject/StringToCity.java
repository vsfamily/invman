package vsfam.ss.invMan.setup.propertyEditors.stringToObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.dao.CityRepo;
import vsfam.ss.invMan.setup.domain.City;

public class StringToCity implements Converter<String, City>{

	@Autowired
	private CityRepo repo;
	
	@Override
	public City convert(String id) {
		try {
			
			return (City) repo.findById(Long.valueOf(id)).orElse(null);
			
		} catch (Exception e) {
			return null;
		}
	}
}
