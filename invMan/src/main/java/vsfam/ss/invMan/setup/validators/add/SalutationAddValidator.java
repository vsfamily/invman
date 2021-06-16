package vsfam.ss.invMan.setup.validators.add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.setup.dao.SalutationRepo;
import vsfam.ss.invMan.setup.domain.Salutation;

@Component
public class SalutationAddValidator extends BaseValidator implements Validator {

	@Autowired
	private SalutationRepo repo;

	@Override
	public boolean supports(Class<?> clazz) {
		return Salutation.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Salutation obj = (Salutation) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code","salutation.code.required");

		if (obj.getCode() != null) {
			if (!this.lengthRange(obj.getCode(), 1, 20)) {
				errors.rejectValue("code", "salutation.code.size");
			}

			Salutation o = this.repo.findByCode(obj.getCode());
			
			if (o != null) {
				errors.rejectValue("code", "salutation.code.unique");
			}
		}
	}
}
