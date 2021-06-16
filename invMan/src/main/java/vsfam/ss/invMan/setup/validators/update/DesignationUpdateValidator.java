package vsfam.ss.invMan.setup.validators.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.setup.dao.DesignationRepo;
import vsfam.ss.invMan.setup.domain.Designation;

@Component
public class DesignationUpdateValidator extends BaseValidator implements Validator {

	@Autowired
	private DesignationRepo repo;

	@Override
	public boolean supports(Class<?> clazz) {
		return Designation.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Designation obj = (Designation) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "designation.description.required");

		if (obj.getDescription() != null) {
			if (!this.lengthRange(obj.getDescription(), 1, 100)) {
				errors.rejectValue("description", "designation.description.size");
			}

			Designation o = this.repo.findByDescription(obj.getDescription());

			if (o != null) {
				errors.rejectValue("description", "designation.description.unique");
			}
		}
	}
}
