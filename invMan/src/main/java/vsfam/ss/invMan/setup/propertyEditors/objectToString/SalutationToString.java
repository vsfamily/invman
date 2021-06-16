package vsfam.ss.invMan.setup.propertyEditors.objectToString;

import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.domain.Salutation;

public class SalutationToString implements Converter<Salutation, String>{

	@Override
	public String convert(Salutation source) {
		if (source == null) return "";
		return source.getId().toString();
	}
}
