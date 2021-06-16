package vsfam.ss.invMan.setup.propertyEditors.stringToObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.dao.OrganizationUnitRepo;
import vsfam.ss.invMan.setup.domain.OrganizationUnit;

public class StringToOrganizationUnit implements Converter<String, OrganizationUnit>{

	@Autowired
	private OrganizationUnitRepo repo;
	
	@Override
	public OrganizationUnit convert(String id) {
		try {
			
			return (OrganizationUnit) repo.findById(Long.valueOf(id)).orElse(null);
			
		} catch (Exception e) {
			return null;
		}
	}
}