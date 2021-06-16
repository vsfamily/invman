package vsfam.ss.invMan.controller.setup.organization;

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
import vsfam.ss.invMan.setup.dao.OrganizationRepo;
import vsfam.ss.invMan.setup.domain.Organization;
import vsfam.ss.invMan.setup.service.OrganizationService;
import vsfam.ss.invMan.setup.validators.update.OrganizationUpdateValidator;

@Controller
@RequestMapping("/setup/organization/update")
public class OrganizationUpdateController extends SetupBaseController {

	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private OrganizationRepo organizationRepo;
	
	@Autowired
	private OrganizationUpdateValidator organizationUpdateValidator;
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editOrganization(@PathVariable Long id, Model model,
			RedirectAttributes reat) {

		Organization organization = this.organizationRepo.getById(id);
		
		if (organization == null) {
			reat.addFlashAttribute("message", "No such record.");
			return "redirect:/setup/organization/addNew";
		}

		model.addAttribute("organization", organization);
		
		return "setup/organization/update";
	}

	@RequestMapping(value = "/*", method = RequestMethod.POST)
	public String editOrganization(@ModelAttribute Organization organization,
			BindingResult result, Model model, RedirectAttributes reat,
			Principal principal) {
		
		this.organizationUpdateValidator.validate(organization, result);
		
		if (result.hasErrors()) {
			return "setup/organization/update";
		}
		
		try {
			TransactionResult tr = this.organizationService.updateOrganization(organization, principal.getName());
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not updated.");
				return "redirect:/setup/organization/addNew";
			} else {
				if (tr.isStatus()) {
					reat.addFlashAttribute("message", "Record updated successfully.");
					return "redirect:/setup/organization/addNew";
				} else {
					reat.addFlashAttribute("message", tr.getMessage());
					return "redirect:/setup/organization/update/"+organization.getId();
				}
			}
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "redirect:/setup/organization/update/"+organization.getId();
		}
	}
}
