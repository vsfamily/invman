package vsfam.ss.invMan.controller.setup.organizationUnit;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import vsfam.ss.invMan.setup.dao.OrganizationUnitRepo;
import vsfam.ss.invMan.setup.dao.OrganizationUnitTypeRepo;
import vsfam.ss.invMan.setup.domain.OrganizationUnit;
import vsfam.ss.invMan.setup.domain.OrganizationUnitType;
import vsfam.ss.invMan.setup.service.OrganizationUnitService;
import vsfam.ss.invMan.setup.validators.update.OrganizationUnitUpdateValidator;

@Controller
@RequestMapping("/setup/organizationUnit/update")
public class OrganizationUnitUpdateController extends SetupBaseController {

	@Autowired
	private OrganizationUnitService organizationUnitService;
	
	@Autowired
	private OrganizationUnitRepo organizationUnitRepo;

	@Autowired
	private OrganizationUnitTypeRepo organizationUnitTypeRepo;
	
	@ModelAttribute("listOrganizationUnit")
	public List<OrganizationUnit> getListOrganizationUnit(){
		return (List<OrganizationUnit>) this.organizationUnitRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
	
	@ModelAttribute("listOrganizationUnitType")
	public List<OrganizationUnitType> getListOrganizationUnitType(){
		return (List<OrganizationUnitType>) this.organizationUnitTypeRepo.findAll(Sort.by(Sort.Direction.ASC, "description"));
	}
	
	@Autowired
	private OrganizationUnitUpdateValidator organizationUnitUpdateValidator;
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editOrganizationUnit(@PathVariable Long id, Model model,
			RedirectAttributes reat) {

		OrganizationUnit organizationUnit = this.organizationUnitRepo.getById(id);
		
		if (organizationUnit == null) {
			reat.addFlashAttribute("message", "No such record.");
			return "redirect:/setup/organizationUnit/addNew";
		}

		model.addAttribute("organizationUnit", organizationUnit);
		
		return "setup/organizationUnit/update";
	}

	@RequestMapping(value = "/*", method = RequestMethod.POST)
	public String editOrganizationUnit(@ModelAttribute OrganizationUnit organizationUnit,
			BindingResult result, Model model, RedirectAttributes reat,
			Principal principal) {
		
		this.organizationUnitUpdateValidator.validate(organizationUnit, result);
		
		if (result.hasErrors()) {
			return "setup/organizationUnit/update";
		}
		
		try {
			TransactionResult tr = this.organizationUnitService.updateOrganizationUnit(organizationUnit, principal.getName());
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not updated.");
				return "redirect:/setup/organizationUnit/addNew";
			} else {
				if (tr.isStatus()) {
					reat.addFlashAttribute("message", "Record updated successfully.");
					return "redirect:/setup/organizationUnit/addNew";
				} else {
					reat.addFlashAttribute("message", tr.getMessage());
					return "redirect:/setup/organizationUnit/update/"+organizationUnit.getId();
				}
			}
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "redirect:/setup/organizationUnit/update/"+organizationUnit.getId();
		}
	}
}
