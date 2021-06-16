package vsfam.ss.invMan.controller.setup.organization;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.controller.setup.SetupBaseController;
import vsfam.ss.invMan.setup.dao.OrganizationUnitRepo;
import vsfam.ss.invMan.setup.domain.Organization;
import vsfam.ss.invMan.setup.domain.OrganizationUnit;
import vsfam.ss.invMan.setup.service.OrganizationService;
import vsfam.ss.invMan.setup.validators.add.OrganizationAddValidator;

@Controller
@RequestMapping(value = "/setup/organization/addNew")
public class OrganizationAddController extends SetupBaseController {

	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private OrganizationUnitRepo organizationUnitRepo;

	@ModelAttribute("listOrganizationUnit")
	public List<OrganizationUnit> getListOrganizationUnit(){
		return (List<OrganizationUnit>) this.organizationUnitRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
	} 
	@Autowired
	private OrganizationAddValidator organizationAddValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String organizationAdd(Model model) {
		
		Organization organization = new Organization();
		
		model.addAttribute(organization);
		
		return "setup/organization/addNew";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String organizationAdd(@ModelAttribute Organization organization,
			BindingResult result, Model model, RedirectAttributes reat, Principal principal) {
		
		this.organizationAddValidator.validate(organization, result);
		
		if (result.hasErrors()) {
			return "setup/organization/addNew";
		}
		
		try {
			TransactionResult tr = this.organizationService.addOrganization(organization, principal.getName());
			
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not added. Please try again later.");
				return "setup/organization/addNew";
			} else {
				reat.addFlashAttribute("message", "Record added successfully.");
			}
			
			return "redirect:/setup/organization/addNew";
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "setup/organization/addNew";
		}
	}
}
