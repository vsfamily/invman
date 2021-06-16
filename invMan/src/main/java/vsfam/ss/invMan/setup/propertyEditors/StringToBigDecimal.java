package vsfam.ss.invMan.setup.propertyEditors;

import java.math.BigDecimal;

import org.springframework.core.convert.converter.Converter;

public class StringToBigDecimal implements Converter<String, BigDecimal>{
	
	@Override
	public BigDecimal convert(String id) {
		try {
			
			BigDecimal obj = new BigDecimal(id);
			
			return obj;
			
		} catch (Exception e) {
			return new BigDecimal(0);
		}
	}
}
