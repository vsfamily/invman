package vsfam.ss.invMan.controller.setup.city;

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
import vsfam.ss.invMan.setup.dao.CityRepo;
import vsfam.ss.invMan.setup.domain.City;
import vsfam.ss.invMan.setup.service.CityService;
import vsfam.ss.invMan.setup.validators.update.CityUpdateValidator;

@Controller
@RequestMapping("/setup/city/update")
public class CityUpdateController extends SetupBaseController {

	@Autowired
	private CityService cityService;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Autowired
	private CityUpdateValidator cityUpdateValidator;
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editCity(@PathVariable Long id, Model model,
			RedirectAttributes reat) {

		City city = this.cityRepo.getById(id);
		
		if (city == null) {
			reat.addFlashAttribute("message", "No such record.");
			return "redirect:/setup/city/addNew";
		}

		model.addAttribute("city", city);
		
		return "setup/city/update";
	}

	@RequestMapping(value = "/*", method = RequestMethod.POST)
	public String editCity(@ModelAttribute City city,
			BindingResult result, Model model, RedirectAttributes reat,
			Principal principal) {
		
		this.cityUpdateValidator.validate(city, result);
		
		if (result.hasErrors()) {
			return "setup/city/update";
		}
		
		try {
			TransactionResult tr = this.cityService.updateCity(city, principal.getName());
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not updated.");
				return "redirect:/setup/city/addNew";
			} else {
				if (tr.isStatus()) {
					reat.addFlashAttribute("message", "Record updated successfully.");
					return "redirect:/setup/city/addNew";
				} else {
					reat.addFlashAttribute("message", tr.getMessage());
					return "redirect:/setup/city/update/"+city.getId();
				}
			}
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "redirect:/setup/city/update/"+city.getId();
		}
	}
}
