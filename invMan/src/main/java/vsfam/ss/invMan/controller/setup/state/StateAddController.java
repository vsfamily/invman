package vsfam.ss.invMan.controller.setup.state;

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
import vsfam.ss.invMan.setup.dao.CountryRepo;
import vsfam.ss.invMan.setup.domain.Country;
import vsfam.ss.invMan.setup.domain.State;
import vsfam.ss.invMan.setup.service.StateService;
import vsfam.ss.invMan.setup.validators.add.StateAddValidator;

@Controller
@RequestMapping(value = "/setup/state/addNew")
public class StateAddController extends SetupBaseController {

	@Autowired
	private StateService stateService;
	
	@Autowired
	private CountryRepo countryRepo;
	
	@ModelAttribute("listCountry")
	public List<Country> getListCountry(){
		return (List<Country>) this.countryRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
	
	@Autowired
	private StateAddValidator stateAddValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String stateAdd(Model model) {
		
		State state = new State();
		
		model.addAttribute(state);
		
		return "setup/state/addNew";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String stateAdd(@ModelAttribute State state,
			BindingResult result, Model model, RedirectAttributes reat, Principal principal) {
		
		this.stateAddValidator.validate(state, result);
		
		if (result.hasErrors()) {
			return "setup/state/addNew";
		}
		
		try {
			TransactionResult tr = this.stateService.addState(state, principal.getName());
			
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not added. Please try again later.");
				return "setup/state/addNew";
			} else {
				reat.addFlashAttribute("message", "Record added successfully.");
			}
			
			return "redirect:/setup/state/addNew";
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "setup/state/addNew";
		}
	}
}

