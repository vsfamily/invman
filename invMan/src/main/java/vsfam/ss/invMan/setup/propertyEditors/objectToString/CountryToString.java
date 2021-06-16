package vsfam.ss.invMan.setup.propertyEditors.objectToString;

import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.domain.Country;

public class CountryToString implements Converter<Country, String>{

	@Override
	public String convert(Country source) {
		if (source == null) return "";
		return source.getId().toString();
	}
}
