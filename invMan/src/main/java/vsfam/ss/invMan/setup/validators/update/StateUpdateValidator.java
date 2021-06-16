package vsfam.ss.invMan.setup.validators.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.setup.dao.StateRepo;
import vsfam.ss.invMan.setup.domain.State;

@Component
public class StateUpdateValidator extends BaseValidator implements Validator {

	@Autowired
	private StateRepo repo;

	@Override
	public boolean supports(Class<?> clazz) {
		return State.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		State obj = (State) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "state.name.required");

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
