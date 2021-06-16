package vsfam.ss.invMan.setup.propertyEditors.objectToString;

import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.domain.OrganizationUnit;

public class OrganizationUnitToString implements Converter<OrganizationUnit, String>{

	@Override
	public String convert(OrganizationUnit source) {
		if (source == null) return "";
		return source.getId().toString();
	}
}
