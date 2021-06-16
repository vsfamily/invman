package vsfam.ss.invMan.controller.setup;

import org.springframework.web.bind.annotation.ModelAttribute;

public class SetupBaseController {

	@ModelAttribute("viewLeftMenu")
	public String viewLeftMenu() {
		return "setup";
	}
}
