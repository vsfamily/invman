package vsfam.ss.invMan.controller;

import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {

	@ModelAttribute("viewLeftMenu")
	public String viewLeftMenu() {
		return "index";
	}
}
