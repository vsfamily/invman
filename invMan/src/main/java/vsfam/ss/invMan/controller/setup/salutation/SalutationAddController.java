package vsfam.ss.invMan.controller.setup.salutation;

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
import vsfam.ss.invMan.setup.domain.Salutation;
import vsfam.ss.invMan.setup.service.SalutationService;
import vsfam.ss.invMan.setup.validators.add.SalutationAddValidator;

@Controller
@RequestMapping(value = "/setup/salutation/addNew")
public class SalutationAddController extends SetupBaseController {

	@Autowired
	private SalutationService salutationService;
	
	@Autowired
	private SalutationAddValidator salutationAddValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String salutationAdd(Model model) {
		
		Salutation salutation = new Salutation();
		
		model.addAttribute(salutation);
		
		return "setup/salutation/addNew";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salutationAdd(@ModelAttribute Salutation salutation,
			BindingResult result, Model model, RedirectAttributes reat, Principal principal) {
		
		this.salutationAddValidator.validate(salutation, result);
		
		if (result.hasErrors()) {
			return "setup/salutation/addNew";
		}
		
		try {
			TransactionResult tr = this.salutationService.addSalutation(salutation, principal.getName());
			
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not added. Please try again later.");
				return "setup/salutation/addNew";
			} else {
				reat.addFlashAttribute("message", "Record added successfully.");
			}
			
			return "redirect:/setup/salutation/addNew";
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "setup/salutation/addNew";
		}
	}
}
