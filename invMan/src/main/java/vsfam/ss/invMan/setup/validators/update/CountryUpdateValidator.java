package vsfam.ss.invMan.setup.validators.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.setup.dao.CountryRepo;
import vsfam.ss.invMan.setup.domain.Country;

@Component
public class CountryUpdateValidator extends BaseValidator implements Validator {

	@Autowired
	private CountryRepo repo;

	@Override
	public boolean supports(Class<?> clazz) {
		return Country.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Country obj = (Country) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "country.name.required");

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
