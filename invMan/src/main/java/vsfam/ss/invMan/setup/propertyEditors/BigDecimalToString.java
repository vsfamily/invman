package vsfam.ss.invMan.setup.propertyEditors;

import java.math.BigDecimal;

import org.springframework.core.convert.converter.Converter;

public class BigDecimalToString implements Converter<BigDecimal, String>{

	@Override
	public String convert(BigDecimal source) {
		if (source == null) return "0.00";
		return source.toString();
	}
}