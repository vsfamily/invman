package vsfam.ss.invMan.controller.setup.state;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import vsfam.ss.invMan.setup.dao.StateRepo;
import vsfam.ss.invMan.setup.domain.Country;
import vsfam.ss.invMan.setup.domain.State;
import vsfam.ss.invMan.setup.service.StateService;
import vsfam.ss.invMan.setup.validators.update.StateUpdateValidator;

@Controller
@RequestMapping("/setup/state/update")
public class StateUpdateController extends SetupBaseController {

	@Autowired
	private StateService stateService;
	
	@Autowired
	private StateRepo stateRepo;
	
	@Autowired
	private CountryRepo countryRepo;
	
	@ModelAttribute("listCountry")
	public List<Country> getListCountry(){
		return (List<Country>) this.countryRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
	
	@Autowired
	private StateUpdateValidator stateUpdateValidator;
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editState(@PathVariable Long id, Model model,
			RedirectAttributes reat) {

		State state = this.stateRepo.getById(id);
		
		if (state == null) {
			reat.addFlashAttribute("message", "No such record.");
			return "redirect:/setup/state/addNew";
		}

		model.addAttribute("state", state);
		
		return "setup/state/update";
	}

	@RequestMapping(value = "/*", method = RequestMethod.POST)
	public String editState(@ModelAttribute State state,
			BindingResult result, Model model, RedirectAttributes reat,
			Principal principal) {
		
		this.stateUpdateValidator.validate(state, result);
		
		if (result.hasErrors()) {
			return "setup/state/update";
		}
		
		try {
			TransactionResult tr = this.stateService.updateState(state, principal.getName());
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not updated.");
				return "redirect:/setup/state/addNew";
			} else {
				if (tr.isStatus()) {
					reat.addFlashAttribute("message", "Record updated successfully.");
					return "redirect:/setup/state/addNew";
				} else {
					reat.addFlashAttribute("message", tr.getMessage());
					return "redirect:/setup/state/update/"+state.getId();
				}
			}
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "redirect:/setup/state/update/"+state.getId();
		}
	}
}
