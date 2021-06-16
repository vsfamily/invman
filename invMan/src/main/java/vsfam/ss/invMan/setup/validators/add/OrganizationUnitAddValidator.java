package vsfam.ss.invMan.setup.validators.add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.setup.dao.OrganizationUnitRepo;
import vsfam.ss.invMan.setup.domain.OrganizationUnit;

@Component
public class OrganizationUnitAddValidator extends BaseValidator implements Validator {

	@Autowired
	private OrganizationUnitRepo repo;

	@Override
	public boolean supports(Class<?> clazz) {
		return OrganizationUnit.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrganizationUnit obj = (OrganizationUnit) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shortName","organizationUnit.shortName.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "organizationUnit.name.required");

		if (obj.getShortName() != null) {
			if (!this.lengthRange(obj.getShortName(), 1, 20)) {
				errors.rejectValue("shortName", "organizationUnit.shortName.size");
			}

			OrganizationUnit o = this.repo.findByShortName(obj.getShortName());
			
			if (o != null) {
				errors.rejectValue("shortName", "organizationUnit.shortName.unique");
			}
		}

		if (obj.getName() != null) {
			if (!this.lengthRange(obj.getName(), 1, 500)) {
				errors.rejectValue("name", "organizationUnit.name.size");
			}

			OrganizationUnit o = this.repo.findByName(obj.getName());

			if (o != null) {
				errors.rejectValue("name", "organizationUnit.name.unique");
			}
		}
		
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
