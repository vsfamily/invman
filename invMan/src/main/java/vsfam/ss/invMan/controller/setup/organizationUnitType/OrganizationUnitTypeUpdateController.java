package vsfam.ss.invMan.controller.setup.organizationUnitType;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.controller.setup.SetupBaseController;
import vsfam.ss.invMan.setup.dao.OrganizationUnitTypeRepo;
import vsfam.ss.invMan.setup.domain.OrganizationUnitType;
import vsfam.ss.invMan.setup.service.OrganizationUnitTypeService;
import vsfam.ss.invMan.setup.validators.update.OrganizationUnitTypeUpdateValidator;

@Controller
@RequestMapping("/setup/organizationUnitType/update")
public class OrganizationUnitTypeUpdateController extends SetupBaseController {

	@Autowired
	private OrganizationUnitTypeService organizationUnitTypeService;
	
	@Autowired
	private OrganizationUnitTypeRepo organizationUnitTypeRepo;
	
	@Autowired
	private OrganizationUnitTypeUpdateValidator organizationUnitTypeUpdateValidator;
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editOrganizationUnitType(@PathVariable Long id, Model model,
			RedirectAttributes reat) {

		OrganizationUnitType organizationUnitType = this.organizationUnitTypeRepo.getById(id);
		
		if (organizationUnitType == null) {
			reat.addFlashAttribute("message", "No such record.");
			return "redirect:/setup/organizationUnitType/addNew";
		}

		model.addAttribute("organizationUnitType", organizationUnitType);
		
		return "setup/organizationUnitType/update";
	}

	@RequestMapping(value = "/*", method = RequestMethod.POST)
	public String editOrganizationUnitType(@ModelAttribute OrganizationUnitType organizationUnitType,
			BindingResult result, Model model, RedirectAttributes reat,
			Principal principal) {
		
		this.organizationUnitTypeUpdateValidator.validate(organizationUnitType, result);
		
		if (result.hasErrors()) {
			return "setup/organizationUnitType/update";
		}
		
		try {
			TransactionResult tr = this.organizationUnitTypeService.updateOrganizationUnitType(organizationUnitType, principal.getName());
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not updated.");
				return "redirect:/setup/organizationUnitType/addNew";
			} else {
				if (tr.isStatus()) {
					reat.addFlashAttribute("message", "Record updated successfully.");
					return "redirect:/setup/organizationUnitType/addNew";
				} else {
					reat.addFlashAttribute("message", tr.getMessage());
					return "redirect:/setup/organizationUnitType/update/"+organizationUnitType.getId();
				}
			}
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "redirect:/setup/organizationUnitType/update/"+organizationUnitType.getId();
		}
	}
}
