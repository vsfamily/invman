package vsfam.ss.invMan.setup.propertyEditors.objectToString;

import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.domain.Parameters;

public class ParametersToString implements Converter<Parameters, String>{

	@Override
	public String convert(Parameters source) {
		if (source == null) return "";
		return source.getId().toString();
	}
}