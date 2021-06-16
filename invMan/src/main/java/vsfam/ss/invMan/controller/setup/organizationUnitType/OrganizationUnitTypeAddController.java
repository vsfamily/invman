package vsfam.ss.invMan.controller.setup.organizationUnitType;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.controller.setup.SetupBaseController;
import vsfam.ss.invMan.setup.domain.OrganizationUnitType;
import vsfam.ss.invMan.setup.service.OrganizationUnitTypeService;
import vsfam.ss.invMan.setup.validators.add.OrganizationUnitTypeAddValidator;

@Controller
@RequestMapping(value = "/setup/organizationUnitType/addNew")
public class OrganizationUnitTypeAddController extends SetupBaseController {

	@Autowired
	private OrganizationUnitTypeService organizationUnitTypeService;
	
	@Autowired
	private OrganizationUnitTypeAddValidator organizationUnitTypeAddValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String organizationUnitTypeAdd(Model model) {
		
		OrganizationUnitType organizationUnitType = new OrganizationUnitType();
		
		model.addAttribute(organizationUnitType);
		
		return "setup/organizationUnitType/addNew";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String organizationUnitTypeAdd(@ModelAttribute OrganizationUnitType organizationUnitType,
			BindingResult result, Model model, RedirectAttributes reat, Principal principal) {
		
		this.organizationUnitTypeAddValidator.validate(organizationUnitType, result);
		
		if (result.hasErrors()) {
			return "setup/organizationUnitType/addNew";
		}
		
		try {
			TransactionResult tr = this.organizationUnitTypeService.addOrganizationUnitType(organizationUnitType, principal.getName());
			
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not added. Please try again later.");
				return "setup/organizationUnitType/addNew";
			} else {
				reat.addFlashAttribute("message", "Record added successfully.");
			}
			
			return "redirect:/setup/organizationUnitType/addNew";
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "setup/organizationUnitType/addNew";
		}
	}
}