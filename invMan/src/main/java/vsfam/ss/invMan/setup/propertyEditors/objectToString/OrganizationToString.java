package vsfam.ss.invMan.setup.propertyEditors.objectToString;

import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.domain.Organization;

public class OrganizationToString implements Converter<Organization, String>{

	@Override
	public String convert(Organization source) {
		if (source == null) return "";
		return source.getId().toString();
	}
}
