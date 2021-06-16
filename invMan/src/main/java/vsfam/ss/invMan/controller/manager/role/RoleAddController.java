package vsfam.ss.invMan.controller.manager.role;

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
import vsfam.ss.invMan.manager.domain.Role;
import vsfam.ss.invMan.manager.service.RoleService;
import vsfam.ss.invMan.manager.validators.add.RoleAddValidator;

@Controller
@RequestMapping(value = "/manager/role/addNew")
public class RoleAddController extends ManagerBaseController {

	@Autowired
	private RoleService roleService;
		
	@Autowired
	private RoleAddValidator roleAddValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String roleAdd(Model model) {
		
		Role role = new Role();
		
		model.addAttribute(role);
		
		return "manager/role/addNew";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String roleAdd(@ModelAttribute Role role,
			BindingResult result, Model model, RedirectAttributes reat, Principal principal) {
		
		this.roleAddValidator.validate(role, result);
		
		if (result.hasErrors()) {
			return "manager/role/addNew";
		}
		
		try {
			TransactionResult tr = this.roleService.addRole(role, principal.getName());
			
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not added. Please try again later.");
				return "manager/role/addNew";
			} else {
				reat.addFlashAttribute("message", "Record added successfully.");
			}
			
			return "redirect:/manager/role/addNew";
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "manager/role/addNew";
		}
	}
}

