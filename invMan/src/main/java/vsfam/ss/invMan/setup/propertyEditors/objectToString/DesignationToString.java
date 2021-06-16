package vsfam.ss.invMan.setup.propertyEditors.objectToString;

import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.domain.Designation;

public class DesignationToString implements Converter<Designation, String>{

	@Override
	public String convert(Designation source) {
		if (source == null) return "";
		return source.getId().toString();
	}
}
