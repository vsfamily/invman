package vsfam.ss.invMan.setup.validators.add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.setup.dao.CountryRepo;
import vsfam.ss.invMan.setup.domain.Country;

@Component
public class CountryAddValidator extends BaseValidator implements Validator {

	@Autowired
	private CountryRepo repo;

	@Override
	public boolean supports(Class<?> clazz) {
		return Country.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Country obj = (Country) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code","country.code.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "country.name.required");

		if (obj.getCode() != null) {
			if (!this.lengthRange(obj.getCode(), 1, 20)) {
				errors.rejectValue("code", "country.code.size");
			}

			Country o = this.repo.findByCode(obj.getCode());
			
			if (o != null) {
				errors.rejectValue("code", "country.code.unique");
			}
		}

		if (obj.getName() != null) {
			if (!this.lengthRange(obj.getName(), 1, 100)) {
				errors.rejectValue("name", "country.name.size");
			}

			Country o = this.repo.findByName(obj.getName());

			if (o != null) {
				errors.rejectValue("name", "country.name.unique");
			}
		}
	}
}
