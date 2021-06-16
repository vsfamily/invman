package vsfam.ss.invMan.manager.validators.add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.manager.dao.GroupRepo;
import vsfam.ss.invMan.manager.domain.Group;

@Component
public class GroupAddValidator extends BaseValidator implements Validator {

	@Autowired
	private GroupRepo repo;

	@Override
	public boolean supports(Class<?> clazz) {
		return Group.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Group obj = (Group) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code","role.code.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "role.description.required");

		if (obj.getCode() != null) {
			if (!this.lengthRange(obj.getCode(), 1, 20)) {
				errors.rejectValue("code", "role.code.size");
			}

			Group o = this.repo.findByCode(obj.getCode());
			
			if (o != null) {
				errors.rejectValue("code", "role.code.unique");
			}
		}

		if (obj.getDescription() != null) {
			if (!this.lengthRange(obj.getDescription(), 1, 100)) {
				errors.rejectValue("description", "role.description.size");
			}

			Group o = this.repo.findByDescription(obj.getDescription());

			if (o != null) {
				errors.rejectValue("description", "role.description.unique");
			}
		}
	}
}
