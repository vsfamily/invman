package vsfam.ss.invMan.manager.propertyEditors.stringToObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.manager.dao.UserRepo;
import vsfam.ss.invMan.manager.domain.User;

public class StringToUser implements Converter<String, User>{

	@Autowired
	private UserRepo repo;
	
	@Override
	public User convert(String id) {
		try {
			
			return (User) repo.findById(Long.valueOf(id)).orElse(null);
			
		} catch (Exception e) {
			return null;
		}
	}
}
