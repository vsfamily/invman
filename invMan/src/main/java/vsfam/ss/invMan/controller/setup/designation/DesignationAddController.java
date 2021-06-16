package vsfam.ss.invMan.controller.setup.designation;

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
import vsfam.ss.invMan.setup.domain.Designation;
import vsfam.ss.invMan.setup.service.DesignationService;
import vsfam.ss.invMan.setup.validators.add.DesignationAddValidator;

@Controller
@RequestMapping(value = "/setup/designation/addNew")
public class DesignationAddController extends SetupBaseController {

	@Autowired
	private DesignationService designationService;
	
	@Autowired
	private DesignationAddValidator designationAddValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String designationAdd(Model model) {
		
		Designation designation = new Designation();
		
		model.addAttribute(designation);
		
		return "setup/designation/addNew";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String designationAdd(@ModelAttribute Designation designation,
			BindingResult result, Model model, RedirectAttributes reat, Principal principal) {
		
		this.designationAddValidator.validate(designation, result);
		
		if (result.hasErrors()) {
			return "setup/designation/addNew";
		}
		
		try {
			TransactionResult tr = this.designationService.addDesignation(designation, principal.getName());
			
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not added. Please try again later.");
				return "setup/designation/addNew";
			} else {
				reat.addFlashAttribute("message", "Record added successfully.");
			}
			
			return "redirect:/setup/designation/addNew";
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "setup/designation/addNew";
		}
	}
}
