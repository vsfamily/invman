package vsfam.ss.invMan.manager.propertyEditors.stringToObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.manager.dao.GroupRepo;
import vsfam.ss.invMan.manager.domain.Group;

public class StringToGroup implements Converter<String, Group>{

	@Autowired
	private GroupRepo repo;
	
	@Override
	public Group convert(String id) {
		try {
			
			return (Group) repo.findById(Long.valueOf(id)).orElse(null);
			
		} catch (Exception e) {
			return null;
		}
	}
}