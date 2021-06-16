package vsfam.ss.invMan.controller.setup.city;

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
import vsfam.ss.invMan.setup.dao.StateRepo;
import vsfam.ss.invMan.setup.domain.City;
import vsfam.ss.invMan.setup.domain.State;
import vsfam.ss.invMan.setup.service.CityService;
import vsfam.ss.invMan.setup.validators.add.CityAddValidator;

@Controller
@RequestMapping(value = "/setup/city/addNew")
public class CityAddController extends SetupBaseController {

	@Autowired
	private CityService cityService;

	@Autowired
	private StateRepo stateRepo;

	@ModelAttribute("listState")
	public List<State> getListState(){
		return (List<State>) this.stateRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
	
	@Autowired
	private CityAddValidator cityAddValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String cityAdd(Model model) {
		
		City city = new City();
		
		model.addAttribute(city);
		
		return "setup/city/addNew";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String cityAdd(@ModelAttribute City city,
			BindingResult result, Model model, RedirectAttributes reat, Principal principal) {
		
		this.cityAddValidator.validate(city, result);
		
		if (result.hasErrors()) {
			return "setup/city/addNew";
		}
		
		try {
			TransactionResult tr = this.cityService.addCity(city, principal.getName());
			
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not added. Please try again later.");
				return "setup/city/addNew";
			} else {
				reat.addFlashAttribute("message", "Record added successfully.");
			}
			
			return "redirect:/setup/city/addNew";
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "setup/city/addNew";
		}
	}
}
