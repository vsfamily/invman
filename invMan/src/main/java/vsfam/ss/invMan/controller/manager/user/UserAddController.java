package vsfam.ss.invMan.controller.manager.user;

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
import vsfam.ss.invMan.controller.manager.ManagerBaseController;
import vsfam.ss.invMan.manager.domain.User;
import vsfam.ss.invMan.manager.service.UserService;
import vsfam.ss.invMan.manager.validators.add.UserAddValidator;

@Controller
@RequestMapping(value = "/manager/user/addNew")
public class UserAddController extends ManagerBaseController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserAddValidator userAddValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String userAdd(Model model) {
		
		User user = new User();
		
		model.addAttribute(user);
		
		return "manager/user/addNew";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String userAdd(@ModelAttribute User user,
			BindingResult result, Model model, RedirectAttributes reat, Principal principal) {
		
		this.userAddValidator.validate(user, result);
		
		if (result.hasErrors()) {
			return "manager/user/addNew";
		}
		
		try {
			TransactionResult tr = this.userService.addUser(user, principal.getName());
			
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not added. Please try again later.");
				return "manager/user/addNew";
			} else {
				reat.addFlashAttribute("message", "Record added successfully.");
			}
			
			return "redirect:/manager/user/addNew";
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "manager/user/addNew";
		}
	}
}
