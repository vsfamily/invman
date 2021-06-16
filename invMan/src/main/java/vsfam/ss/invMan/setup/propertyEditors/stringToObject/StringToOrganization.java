package vsfam.ss.invMan.setup.propertyEditors.stringToObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.dao.OrganizationRepo;
import vsfam.ss.invMan.setup.domain.Organization;

public class StringToOrganization implements Converter<String, Organization>{

	@Autowired
	private OrganizationRepo repo;
	
	@Override
	public Organization convert(String id) {
		try {
			
			return (Organization) repo.findById(Long.valueOf(id)).orElse(null);
			
		} catch (Exception e) {
			return null;
		}
	}
}
