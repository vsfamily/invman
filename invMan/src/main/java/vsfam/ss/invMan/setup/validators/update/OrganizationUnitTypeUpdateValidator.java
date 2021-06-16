package vsfam.ss.invMan.setup.validators.update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import vsfam.ss.invMan.common.BaseValidator;
import vsfam.ss.invMan.setup.dao.OrganizationUnitTypeRepo;
import vsfam.ss.invMan.setup.domain.OrganizationUnitType;

@Component
public class OrganizationUnitTypeUpdateValidator extends BaseValidator implements Validator {

	@Autowired
	private OrganizationUnitTypeRepo repo;

	@Override
	public boolean supports(Class<?> clazz) {
		return OrganizationUnitType.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OrganizationUnitType obj = (OrganizationUnitType) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "organizationUnitType.description.required");

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
