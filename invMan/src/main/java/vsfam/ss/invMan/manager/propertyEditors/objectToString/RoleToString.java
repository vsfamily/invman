package vsfam.ss.invMan.manager.propertyEditors.objectToString;

import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.manager.domain.Role;

public class RoleToString implements Converter<Role, String>{

	@Override
	public String convert(Role source) {
		if (source == null) return "";
		return source.getId().toString();
	}
}
