package vsfam.ss.invMan.setup.propertyEditors.objectToString;

import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.domain.OrganizationUnitType;

public class OrganizationUnitTypeToString implements Converter<OrganizationUnitType, String>{

	@Override
	public String convert(OrganizationUnitType source) {
		if (source == null) return "";
		return source.getId().toString();
	}
}