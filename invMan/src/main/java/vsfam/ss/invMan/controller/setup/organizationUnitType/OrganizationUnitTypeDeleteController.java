package vsfam.ss.invMan.controller.setup.organizationUnitType;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.controller.setup.SetupBaseController;
import vsfam.ss.invMan.setup.service.OrganizationUnitTypeService;

@Controller
@RequestMapping("/setup/organizationUnitType/delete")
public class OrganizationUnitTypeDeleteController extends SetupBaseController {

	@Autowired
	private OrganizationUnitTypeService organizationUnitTypeService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String deleteOrganizationUnitType(@PathVariable Long id, Model model,
			RedirectAttributes reat, Principal principal) {

		try {
			
			TransactionResult tr = this.organizationUnitTypeService.deleteOrganizationUnitType(id, principal.getName());
			
			if (tr == null ) {
				
				reat.addFlashAttribute("message", "Record could not be deleted.");
				return "redirect:/setup/organizationUnitType/addNew";
				
			} else if (tr.isStatus()){
				
				reat.addFlashAttribute("message", "Record deleted successfully.");
				return "redirect:/setup/organizationUnitType/list/current";
				
			} else {
				
				reat.addFlashAttribute("message", "Record could not be deleted.");
				return "redirect:/setup/organizationUnitType/addNew";
				
			}
		} catch (Exception e) {
			
			reat.addFlashAttribute("message", "Error: " + e.getMessage());
			return "redirect:/setup/organizationUnitType/addNew";
			
		}
	}
}
