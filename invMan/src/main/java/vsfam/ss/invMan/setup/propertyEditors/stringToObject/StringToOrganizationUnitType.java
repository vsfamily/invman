package vsfam.ss.invMan.setup.propertyEditors.stringToObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.dao.OrganizationUnitTypeRepo;
import vsfam.ss.invMan.setup.domain.OrganizationUnitType;

public class StringToOrganizationUnitType implements Converter<String, OrganizationUnitType>{

	@Autowired
	private OrganizationUnitTypeRepo repo;
	
	@Override
	public OrganizationUnitType convert(String id) {
		try {
			
			return (OrganizationUnitType) repo.findById(Long.valueOf(id)).orElse(null);
			
		} catch (Exception e) {
			return null;
		}
	}
}