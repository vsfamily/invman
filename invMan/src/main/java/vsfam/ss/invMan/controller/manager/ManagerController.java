package vsfam.ss.invMan.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ManagerController extends ManagerBaseController {

	@RequestMapping(value="/manager", method=RequestMethod.GET)
	public String indexView(Model model) {
		
		return "manager/default";
	}
}
