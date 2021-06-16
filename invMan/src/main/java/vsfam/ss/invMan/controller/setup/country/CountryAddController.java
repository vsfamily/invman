package vsfam.ss.invMan.controller.setup.country;

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
import vsfam.ss.invMan.setup.domain.Country;
import vsfam.ss.invMan.setup.service.CountryService;
import vsfam.ss.invMan.setup.validators.add.CountryAddValidator;

@Controller
@RequestMapping(value = "/setup/country/addNew")
public class CountryAddController extends SetupBaseController {

	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CountryAddValidator countryAddValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String countryAdd(Model model) {
		
		Country country = new Country();
		
		model.addAttribute(country);
		
		return "setup/country/addNew";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String countryAdd(@ModelAttribute Country country,
			BindingResult result, Model model, RedirectAttributes reat, Principal principal) {
		
		this.countryAddValidator.validate(country, result);
		
		if (result.hasErrors()) {
			return "setup/country/addNew";
		}
		
		try {
			TransactionResult tr = this.countryService.addCountry(country, principal.getName());
			
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not added. Please try again later.");
				return "setup/country/addNew";
			} else {
				reat.addFlashAttribute("message", "Record added successfully.");
			}
			
			return "redirect:/setup/country/addNew";
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "setup/country/addNew";
		}
	}
}
