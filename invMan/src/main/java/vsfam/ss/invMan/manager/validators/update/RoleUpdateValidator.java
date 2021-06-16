package vsfam.ss.invMan.manager.validators.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.manager.dao.RoleRepo;
import vsfam.ss.invMan.manager.domain.Role;

@Component
public class RoleUpdateValidator extends BaseValidator implements Validator {

	@Autowired
	private RoleRepo repo;

	@Override
	public boolean supports(Class<?> clazz) {
		return Role.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Role obj = (Role) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "role.description.required");

		if (obj.getDescription() != null) {
			if (!this.lengthRange(obj.getDescription(), 1, 100)) {
				errors.rejectValue("description", "role.description.size");
			}

			Role o = this.repo.findByDescription(obj.getDescription());

			if (o != null && !o.getId().equals(obj.getId())) {
				errors.rejectValue("description", "role.description.unique");
			}
		}
	}
}
