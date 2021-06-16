package vsfam.ss.invMan.manager.validators.add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.manager.dao.UserRepo;
import vsfam.ss.invMan.manager.domain.User;

@Component
public class UserAddValidator extends BaseValidator implements Validator {

	@Autowired
	private UserRepo repo;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User obj = (User) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uid","user.uid.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fullName","user.fullName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "retypePassword", "user.retypePassword.required");

		if (obj.getUid() != null) {
			if (!this.lengthRange(obj.getUid(), 3, 20)) {
				errors.rejectValue("uid", "user.uid.size");
			}

			User o = this.repo.findByUid(obj.getUid());
			
			if (o != null) {
				errors.rejectValue("uid", "user.uid.unique");
			}
		}

		if (obj.getFullName() != null) {
			if (!this.lengthRange(obj.getFullName(), 1, 255)) {
				errors.rejectValue("fullName", "user.fullName.size");
			}
		}
		
		if (obj.getPassword()!=null && obj.getRetypePassword()!=null) {
			
			if (!this.lengthRange(obj.getPassword(), 8, 255)) {
				errors.rejectValue("password", "user.password.size");
			}
			
			if (!obj.getPassword().equals(obj.getRetypePassword())) {
				errors.rejectValue("password", "user.password.match");
			}
		}
	}
}
