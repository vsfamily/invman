package vsfam.ss.invMan.controller.setup.designation;

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
import vsfam.ss.invMan.setup.service.DesignationService;

@Controller
@RequestMapping("/setup/designation/delete")
public class DesignationDeleteController extends SetupBaseController {

	@Autowired
	private DesignationService designationService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String deleteDesignation(@PathVariable Long id, Model model,
			RedirectAttributes reat, Principal principal) {

		try {
			
			TransactionResult tr = this.designationService.deleteDesignation(id, principal.getName());
			
			if (tr == null ) {
				
				reat.addFlashAttribute("message", "Record could not be deleted.");
				return "redirect:/setup/designation/addNew";
				
			} else if (tr.isStatus()){
				
				reat.addFlashAttribute("message", "Record deleted successfully.");
				return "redirect:/setup/designation/list/current";
				
			} else {
				
				reat.addFlashAttribute("message", "Record could not be deleted.");
				return "redirect:/setup/designation/addNew";
				
			}
		} catch (Exception e) {
			
			reat.addFlashAttribute("message", "Error: " + e.getMessage());
			return "redirect:/setup/designation/addNew";
			
		}
	}
}
