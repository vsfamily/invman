package vsfam.ss.invMan.setup.propertyEditors.stringToObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.dao.StateRepo;
import vsfam.ss.invMan.setup.domain.State;

public class StringToState implements Converter<String, State>{

	@Autowired
	private StateRepo repo;
	
	@Override
	public State convert(String id) {
		try {
			
			return (State) repo.findById(Long.valueOf(id)).orElse(null);
			
		} catch (Exception e) {
			return null;
		}
	}
}
