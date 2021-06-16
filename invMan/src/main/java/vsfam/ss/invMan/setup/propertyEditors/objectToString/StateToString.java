package vsfam.ss.invMan.setup.propertyEditors.objectToString;

import org.springframework.core.convert.converter.Converter;

import vsfam.ss.invMan.setup.domain.State;

public class StateToString implements Converter<State, String>{

	@Override
	public String convert(State source) {
		if (source == null) return "";
		return source.getId().toString();
	}
}
