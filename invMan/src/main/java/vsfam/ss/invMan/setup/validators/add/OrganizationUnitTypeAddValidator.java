package vsfam.ss.invMan.setup.validators.add;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.setup.dao.OrganizationUnitTypeRepo;
import vsfam.ss.invMan.setup.domain.OrganizationUnitType;

@Component
public class OrganizationUnitTypeAddValidator extends BaseValidator implements Validator {

	@Autowired
	private OrganizationUnitTypeRepo repo;

	@Override
	public boolean supports(Class<?> clazz) {
		return OrganizationUnitType.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrganizationUnitType obj = (OrganizationUnitType) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "code","organizationUnitType.code.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "organizationUnitType.description.required");

		if (obj.getCode() != null) {
			if (!this.lengthRange(obj.getCode(), 1, 20)) {
				errors.rejectValue("code", "organizationUnitType.code.size");
			}

			OrganizationUnitType o = this.repo.findByCode(obj.getCode());
			
			if (o != null) {
				errors.rejectValue("code", "organizationUnitType.code.unique");
			}
		}

		if (obj.getDescription() != null) {
			if (!this.lengthRange(obj.getDescription(), 1, 100)) {
				errors.rejectValue("description", "organizationUnitType.description.size");
			}

			OrganizationUnitType o = this.repo.findByDescription(obj.getDescription());

			if (o != null) {
				errors.rejectValue("description", "organizationUnitType.description.unique");
			}
		}
	}
}
