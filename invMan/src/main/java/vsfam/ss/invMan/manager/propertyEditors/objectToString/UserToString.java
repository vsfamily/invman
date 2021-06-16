package vsfam.ss.invMan.manager.propertyEditors.objectToString;

import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.manager.domain.User;

public class UserToString implements Converter<User, String>{

	@Override
	public String convert(User source) {
		if (source == null) return "";
		return source.getId().toString();
	}
}