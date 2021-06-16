package vsfam.ss.invMan.manager.propertyEditors.stringToObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.manager.dao.RoleRepo;
import vsfam.ss.invMan.manager.domain.Role;

public class StringToRole implements Converter<String, Role>{

	@Autowired
	private RoleRepo repo;
	
	@Override
	public Role convert(String id) {
		try {
			
			return (Role) repo.findById(Long.valueOf(id)).orElse(null);
			
		} catch (Exception e) {
			return null;
		}
	}
}
