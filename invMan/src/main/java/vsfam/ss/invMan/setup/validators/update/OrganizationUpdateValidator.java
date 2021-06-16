package vsfam.ss.invMan.setup.validators.update;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.setup.domain.Organization;

@Component
public class OrganizationUpdateValidator extends BaseValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Organization.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Organization obj = (Organization) target;
		
		if (obj.getLocation()!=null) {
			if (!this.lengthRange(obj.getLocation(), 0, 500)) {
				errors.rejectValue("location", "organization.location.size");
			}
		}
		
		if (obj.getCity()==null) {
			errors.rejectValue("city", "organization.city.required");
		}
		
		if (obj.getPhone()!=null) {
			if (!this.lengthRange(obj.getPhone(), 0, 20)) {
				errors.rejectValue("phone", "organization.phone.size");
			}
			
			if (!this.isNumeric(obj.getPhone())) {
				errors.rejectValue("phone", "organization.phone.format");
			}
		}
		
		if (obj.getFax()!=null) {
			if (!this.lengthRange(obj.getFax(), 0, 20)) {
				errors.rejectValue("fax", "organization.fax.size");
			}
			
			if (!this.isNumeric(obj.getFax())) {
				errors.rejectValue("fax", "organization.fax.format");
			}
		}
		
		if (obj.getEmail()!=null) {
			if (!this.lengthRange(obj.getEmail(), 0, 255)) {
				errors.rejectValue("email", "organization.email.size");
			}
			
			if (!this.isEmail(obj.getEmail())) {
				errors.rejectValue("email", "organization.email.format");
			}
		}
	}
}
