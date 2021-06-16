package vsfam.ss.invMan.controller.setup.country;

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
import vsfam.ss.invMan.setup.dao.CountryRepo;
import vsfam.ss.invMan.setup.domain.Country;
import vsfam.ss.invMan.setup.service.CountryService;
import vsfam.ss.invMan.setup.validators.update.CountryUpdateValidator;

@Controller
@RequestMapping("/setup/country/update")
public class CountryUpdateController extends SetupBaseController {

	@Autowired
	private CountryService countryService;
	
	@Autowired
	private CountryRepo countryRepo;
	
	@Autowired
	private CountryUpdateValidator countryUpdateValidator;
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editCountry(@PathVariable Long id, Model model,
			RedirectAttributes reat) {

		Country country = this.countryRepo.getById(id);
		
		if (country == null) {
			reat.addFlashAttribute("message", "No such record.");
			return "redirect:/setup/country/addNew";
		}

		model.addAttribute("country", country);
		
		return "setup/country/update";
	}

	@RequestMapping(value = "/*", method = RequestMethod.POST)
	public String editCountry(@ModelAttribute Country country,
			BindingResult result, Model model, RedirectAttributes reat,
			Principal principal) {
		
		this.countryUpdateValidator.validate(country, result);
		
		if (result.hasErrors()) {
			return "setup/country/update";
		}
		
		try {
			TransactionResult tr = this.countryService.updateCountry(country, principal.getName());
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not updated.");
				return "redirect:/setup/country/addNew";
			} else {
				if (tr.isStatus()) {
					reat.addFlashAttribute("message", "Record updated successfully.");
					return "redirect:/setup/country/addNew";
				} else {
					reat.addFlashAttribute("message", tr.getMessage());
					return "redirect:/setup/country/update/"+country.getId();
				}
			}
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "redirect:/setup/country/update/"+country.getId();
		}
	}
}
