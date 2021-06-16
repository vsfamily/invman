package vsfam.ss.invMan.controller.manager.role;

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
import vsfam.ss.invMan.manager.dao.RoleRepo;
import vsfam.ss.invMan.manager.domain.Role;
import vsfam.ss.invMan.manager.service.RoleService;
import vsfam.ss.invMan.manager.validators.update.RoleUpdateValidator;

@Controller
@RequestMapping("/manager/role/update")
public class RoleUpdateController extends ManagerBaseController {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private RoleUpdateValidator roleUpdateValidator;
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editRole(@PathVariable Long id, Model model,
			RedirectAttributes reat) {

		Role role = this.roleRepo.getById(id);
		
		if (role == null) {
			reat.addFlashAttribute("message", "No such record.");
			return "redirect:/manager/role/addNew";
		}

		model.addAttribute("role", role);
		
		return "manager/role/update";
	}

	@RequestMapping(value = "/*", method = RequestMethod.POST)
	public String editRole(@ModelAttribute Role role,
			BindingResult result, Model model, RedirectAttributes reat,
			Principal principal) {
		
		this.roleUpdateValidator.validate(role, result);
		
		if (result.hasErrors()) {
			return "manager/role/update";
		}
		
		try {
			TransactionResult tr = this.roleService.updateRole(role, principal.getName());
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not updated.");
				return "redirect:/manager/role/addNew";
			} else {
				if (tr.isStatus()) {
					reat.addFlashAttribute("message", "Record updated successfully.");
					return "redirect:/manager/role/addNew";
				} else {
					reat.addFlashAttribute("message", tr.getMessage());
					return "redirect:/manager/role/update/"+role.getId();
				}
			}
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "redirect:/manager/role/update/"+role.getId();
		}
	}
}
