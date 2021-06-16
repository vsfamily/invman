package vsfam.ss.invMan.manager.propertyEditors.objectToString;

import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.manager.domain.Group;

public class GroupToString implements Converter<Group, String>{

	@Override
	public String convert(Group source) {
		if (source == null) return "";
		return source.getId().toString();
	}
}
