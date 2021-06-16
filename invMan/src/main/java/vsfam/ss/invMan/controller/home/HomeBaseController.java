package vsfam.ss.invMan.controller.home;

import org.springframework.web.bind.annotation.ModelAttribute;

public class HomeBaseController {

	@ModelAttribute("viewLeftMenu")
	public String viewLeftMenu() {
		return "home";
	}
}
