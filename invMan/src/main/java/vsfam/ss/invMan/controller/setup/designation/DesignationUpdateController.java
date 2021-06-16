package vsfam.ss.invMan.controller.setup.designation;

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
import vsfam.ss.invMan.setup.dao.DesignationRepo;
import vsfam.ss.invMan.setup.domain.Designation;
import vsfam.ss.invMan.setup.service.DesignationService;
import vsfam.ss.invMan.setup.validators.update.DesignationUpdateValidator;

@Controller
@RequestMapping("/setup/designation/update")
public class DesignationUpdateController extends SetupBaseController {

	@Autowired
	private DesignationService designationService;
	
	@Autowired
	private DesignationRepo designationRepo;
	
	@Autowired
	private DesignationUpdateValidator designationUpdateValidator;
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editDesignation(@PathVariable Long id, Model model,
			RedirectAttributes reat) {

		Designation designation = this.designationRepo.getById(id);
		
		if (designation == null) {
			reat.addFlashAttribute("message", "No such record.");
			return "redirect:/setup/designation/addNew";
		}

		model.addAttribute("designation", designation);
		
		return "setup/designation/update";
	}

	@RequestMapping(value = "/*", method = RequestMethod.POST)
	public String editDesignation(@ModelAttribute Designation designation,
			BindingResult result, Model model, RedirectAttributes reat,
			Principal principal) {
		
		this.designationUpdateValidator.validate(designation, result);
		
		if (result.hasErrors()) {
			return "setup/designation/update";
		}
		
		try {
			TransactionResult tr = this.designationService.updateDesignation(designation, principal.getName());
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not updated.");
				return "redirect:/setup/designation/addNew";
			} else {
				if (tr.isStatus()) {
					reat.addFlashAttribute("message", "Record updated successfully.");
					return "redirect:/setup/designation/addNew";
				} else {
					reat.addFlashAttribute("message", tr.getMessage());
					return "redirect:/setup/designation/update/"+designation.getId();
				}
			}
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "redirect:/setup/designation/update/"+designation.getId();
		}
	}
}
