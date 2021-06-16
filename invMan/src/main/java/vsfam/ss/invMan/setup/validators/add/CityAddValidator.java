package vsfam.ss.invMan.setup.validators.add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.setup.dao.CityRepo;
import vsfam.ss.invMan.setup.domain.City;

@Component
public class CityAddValidator extends BaseValidator implements Validator {

	@Autowired
	private CityRepo repo;

	@Override
	public boolean supports(Class<?> clazz) {
		return City.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		City obj = (City) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code","city.code.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "city.name.required");

		if (obj.getCode() != null) {
			if (!this.lengthRange(obj.getCode(), 1, 20)) {
				errors.rejectValue("code", "city.code.size");
			}

			City o = this.repo.findByCode(obj.getCode());
			
			if (o != null) {
				errors.rejectValue("code", "city.code.unique");
			}
		}

		if (obj.getName() != null) {
			if (!this.lengthRange(obj.getName(), 1, 100)) {
				errors.rejectValue("name", "city.name.size");
			}

			City o = this.repo.findByName(obj.getName());

			if (o != null) {
				errors.rejectValue("name", "city.name.unique");
			}
		}
		
		if (obj.getState()==null) {
			errors.rejectValue("state", "city.state.required");
		}
	}
}
