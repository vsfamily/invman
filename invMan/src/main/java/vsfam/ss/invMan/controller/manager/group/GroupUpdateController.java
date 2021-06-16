package vsfam.ss.invMan.controller.manager.group;

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
import vsfam.ss.invMan.manager.dao.GroupRepo;
import vsfam.ss.invMan.manager.domain.Group;
import vsfam.ss.invMan.manager.service.GroupService;
import vsfam.ss.invMan.manager.validators.update.GroupUpdateValidator;

@Controller
@RequestMapping("/manager/group/update")
public class GroupUpdateController extends ManagerBaseController {

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private GroupRepo groupRepo;
	
	@Autowired
	private GroupUpdateValidator groupUpdateValidator;
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editGroup(@PathVariable Long id, Model model,
			RedirectAttributes reat) {

		Group group = this.groupRepo.getById(id);
		
		if (group == null) {
			reat.addFlashAttribute("message", "No such record.");
			return "redirect:/manager/group/addNew";
		}

		model.addAttribute("group", group);
		
		return "manager/group/update";
	}

	@RequestMapping(value = "/*", method = RequestMethod.POST)
	public String editGroup(@ModelAttribute Group group,
			BindingResult result, Model model, RedirectAttributes reat,
			Principal principal) {
		
		this.groupUpdateValidator.validate(group, result);
		
		if (result.hasErrors()) {
			return "manager/group/update";
		}
		
		try {
			TransactionResult tr = this.groupService.updateGroup(group, principal.getName());
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not updated.");
				return "redirect:/manager/group/addNew";
			} else {
				if (tr.isStatus()) {
					reat.addFlashAttribute("message", "Record updated successfully.");
					return "redirect:/manager/group/addNew";
				} else {
					reat.addFlashAttribute("message", tr.getMessage());
					return "redirect:/manager/group/update/"+group.getId();
				}
			}
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "redirect:/manager/group/update/"+group.getId();
		}
	}
}
