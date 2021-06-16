package vsfam.ss.invMan.controller.manager.role;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.controller.manager.ManagerBaseController;
import vsfam.ss.invMan.manager.service.RoleService;

@Controller
@RequestMapping("/manager/role/delete")
public class RoleDeleteController extends ManagerBaseController {

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String deleteRole(@PathVariable Long id, Model model,
			RedirectAttributes reat, Principal principal) {

		try {
			
			TransactionResult tr = this.roleService.deleteRole(id, principal.getName());
			
			if (tr == null ) {
				
				reat.addFlashAttribute("message", "Record could not be deleted.");
				return "redirect:/manager/role/addNew";
				
			} else if (tr.isStatus()){
				
				reat.addFlashAttribute("message", "Record deleted successfully.");
				return "redirect:/manager/role/list/current";
				
			} else {
				
				reat.addFlashAttribute("message", "Record could not be deleted.");
				return "redirect:/manager/role/addNew";
				
			}
		} catch (Exception e) {
			
			reat.addFlashAttribute("message", "Error: " + e.getMessage());
			return "redirect:/manager/role/addNew";
			
		}
	}
}
