package vsfam.ss.invMan.setup.validators.update;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.setup.domain.OrganizationUnit;

@Component
public class OrganizationUnitUpdateValidator extends BaseValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return OrganizationUnit.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrganizationUnit obj = (OrganizationUnit) target;

		if (obj.getLocation()!=null) {
			if (!this.lengthRange(obj.getLocation(), 0, 500)) {
				errors.rejectValue("location", "organizationUnit.location.size");
			}
		}
		
		if (obj.getCity()==null) {
			errors.rejectValue("city", "organizationUnit.city.required");
		}
		
		if (obj.getPhone()!=null) {
			if (!this.lengthRange(obj.getPhone(), 0, 20)) {
				errors.rejectValue("phone", "organizationUnit.phone.size");
			}
			
			if (!this.isNumeric(obj.getPhone())) {
				errors.rejectValue("phone", "organizationUnit.phone.format");
			}
		}
		
		if (obj.getFax()!=null) {
			if (!this.lengthRange(obj.getFax(), 0, 20)) {
				errors.rejectValue("fax", "organizationUnit.fax.size");
			}
			
			if (!this.isNumeric(obj.getFax())) {
				errors.rejectValue("fax", "organizationUnit.fax.format");
			}
		}
		
		if (obj.getEmail()!=null) {
			if (!this.lengthRange(obj.getEmail(), 0, 255)) {
				errors.rejectValue("email", "organizationUnit.email.size");
			}
			
			if (!this.isEmail(obj.getEmail())) {
				errors.rejectValue("email", "organizationUnit.email.format");
			}
		}
		
		if (obj.getOrganization()==null) {
			errors.rejectValue("organization", "organizationUnit.organization.required");
		}
	}
}
