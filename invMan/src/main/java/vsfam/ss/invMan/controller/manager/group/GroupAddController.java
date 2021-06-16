package vsfam.ss.invMan.controller.manager.group;

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
import vsfam.ss.invMan.manager.domain.Group;
import vsfam.ss.invMan.manager.service.GroupService;
import vsfam.ss.invMan.manager.validators.add.GroupAddValidator;

@Controller
@RequestMapping(value = "/manager/group/addNew")
public class GroupAddController extends ManagerBaseController {

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private GroupAddValidator groupAddValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String groupAdd(Model model) {
		
		Group group = new Group();
		
		model.addAttribute(group);
		
		return "manager/group/addNew";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String groupAdd(@ModelAttribute Group group,
			BindingResult result, Model model, RedirectAttributes reat, Principal principal) {
		
		this.groupAddValidator.validate(group, result);
		
		if (result.hasErrors()) {
			return "manager/group/addNew";
		}
		
		try {
			TransactionResult tr = this.groupService.addGroup(group, principal.getName());
			
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not added. Please try again later.");
				return "manager/group/addNew";
			} else {
				reat.addFlashAttribute("message", "Record added successfully.");
			}
			
			return "redirect:/manager/group/addNew";
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "manager/group/addNew";
		}
	}
}
