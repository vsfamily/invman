package vsfam.ss.invMan.manager.validators.update;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.manager.domain.User;

@Component
public class UserUpdateValidator extends BaseValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User obj = (User) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName","user.fullName.required");

		if (obj.getFullName() != null) {
			if (!this.lengthRange(obj.getFullName(), 1, 255)) {
				errors.rejectValue("fullName", "user.fullName.size");
			}
		}
		
		if (obj.getPassword()!=null && obj.getRetypePassword()!=null) {
			if (obj.getPassword().length()>0) {
				if (!this.lengthRange(obj.getPassword(), 8, 255)) {
					errors.rejectValue("password", "user.password.size");
				}
				
				if (!obj.getPassword().equals(obj.getRetypePassword())) {
					errors.rejectValue("password", "user.password.match");
				}
			}
		}
	}
}
