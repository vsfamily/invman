package vsfam.ss.invMan.setup.propertyEditors;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class StringToCalendar implements Converter<String, Calendar> {

	@Override
	public Calendar convert(String source){
		try {
			DateFormat formatter;
			Date date;
			formatter = new SimpleDateFormat("dd-MM-yyyy");
			date = (Date) formatter.parse(source);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal;
		} catch (Exception e) {
			return null;
		}
	}
}
