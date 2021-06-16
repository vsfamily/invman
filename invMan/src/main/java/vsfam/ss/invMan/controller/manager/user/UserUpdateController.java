package vsfam.ss.invMan.controller.manager.user;

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
import vsfam.ss.invMan.controller.manager.ManagerBaseController;
import vsfam.ss.invMan.manager.dao.UserRepo;
import vsfam.ss.invMan.manager.domain.User;
import vsfam.ss.invMan.manager.service.UserService;
import vsfam.ss.invMan.manager.validators.update.UserUpdateValidator;

@Controller
@RequestMapping("/manager/user/update")
public class UserUpdateController extends ManagerBaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserUpdateValidator userUpdateValidator;
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable Long id, Model model,
			RedirectAttributes reat) {

		User user = this.userRepo.getById(id);
		
		if (user == null) {
			reat.addFlashAttribute("message", "No such record.");
			return "redirect:/manager/user/addNew";
		}

		model.addAttribute("user", user);
		
		return "manager/user/update";
	}

	@RequestMapping(value = "/*", method = RequestMethod.POST)
	public String editUser(@ModelAttribute User user,
			BindingResult result, Model model, RedirectAttributes reat,
			Principal principal) {
		
		this.userUpdateValidator.validate(user, result);
		
		if (result.hasErrors()) {
			return "manager/user/update";
		}
		
		try {
			TransactionResult tr = this.userService.updateUser(user, principal.getName());
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not updated.");
				return "redirect:/manager/user/addNew";
			} else {
				if (tr.isStatus()) {
					reat.addFlashAttribute("message", "Record updated successfully.");
					return "redirect:/manager/user/addNew";
				} else {
					reat.addFlashAttribute("message", tr.getMessage());
					return "redirect:/manager/user/update/"+user.getId();
				}
			}
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "redirect:/manager/user/update/"+user.getId();
		}
	}
}
