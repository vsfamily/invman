package vsfam.ss.invMan.controller.setup;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SetupController extends SetupBaseController {

	@RequestMapping(value="/setup", method=RequestMethod.GET)
	public String indexView(Model model) {
		
		return "setup/default";
	}
}
