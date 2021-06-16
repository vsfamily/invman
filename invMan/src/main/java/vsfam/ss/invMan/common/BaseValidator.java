package vsfam.ss.invMan.common;

import java.math.BigDecimal;

public class BaseValidator {
	
	public boolean isAlphaNumericWithUnderscore(String str) {
		if (str.matches("^[A-Za-z0-9_]+$")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isAlphaNumeric(String str) {
		if (str.matches("^[A-Za-z0-9]+$")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isAlphaNumericWithSpace(String str) {
		if (str.matches("^[A-Za-z0-9 ]+$")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNumeric(String str) {
		if (str.matches("^[0-9]+$")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNumericWithSpace(String str) {
		if (str.matches("^[0-9 ]+$")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEmail(String str) {
		if (str.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean lengthRange(String str, int startLength, int endLength) {
		if (str.length() >= startLength && str.length() <= endLength) {
			return true;
		} else {
			return false;
		}
	}

	public boolean valueRange(Long val, Long startValue, Long endValue) {
		if (val.longValue() >= startValue.longValue()
				&& val.longValue() <= endValue.longValue()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean valueRange(BigDecimal val, BigDecimal startValue,
			BigDecimal endValue) {
		if (val.compareTo(startValue) >= 0 && val.compareTo(endValue) <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNegative(BigDecimal val) {
		if (val.compareTo(new BigDecimal(0.00)) < 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isZeroOrNegative(BigDecimal val) {
		if (val.compareTo(new BigDecimal(0.00)) <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNegative(Long val) {
		if (val.longValue() < 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isZeroOrNegative(Long val) {
		if (val.longValue() <= 0) {
			return true;
		} else {
			return false;
		}
	}
}
