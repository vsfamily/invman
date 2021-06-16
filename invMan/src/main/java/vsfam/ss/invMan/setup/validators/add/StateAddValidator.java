package vsfam.ss.invMan.setup.validators.add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.setup.dao.StateRepo;
import vsfam.ss.invMan.setup.domain.State;

@Component
public class StateAddValidator extends BaseValidator implements Validator {

	@Autowired
	private StateRepo repo;

	@Override
	public boolean supports(Class<?> clazz) {
		return State.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		State obj = (State) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code","state.code.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "state.name.required");

		if (obj.getCode() != null) {
			if (!this.lengthRange(obj.getCode(), 1, 20)) {
				errors.rejectValue("code", "state.code.size");
			}

			State o = this.repo.findByCode(obj.getCode());
			
			if (o != null) {
				errors.rejectValue("code", "state.code.unique");
			}
		}

		if (obj.getName() != null) {
			if (!this.lengthRange(obj.getName(), 1, 100)) {
				errors.rejectValue("name", "state.name.size");
			}

			State o = this.repo.findByName(obj.getName());

			if (o != null) {
				errors.rejectValue("name", "state.name.unique");
			}
		}
	}
}
