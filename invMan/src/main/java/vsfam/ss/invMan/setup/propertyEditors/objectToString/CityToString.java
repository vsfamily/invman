package vsfam.ss.invMan.setup.propertyEditors.objectToString;

import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.domain.City;

public class CityToString implements Converter<City, String>{

	@Override
	public String convert(City source) {
		if (source == null) return "";
		return source.getId().toString();
	}
}
