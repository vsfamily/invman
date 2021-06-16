package vsfam.ss.invMan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController extends BaseController {
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String indexView(Model model) {
		
		return "login";
	}
	
	@RequestMapping(value="/logout-success", method=RequestMethod.GET)
	public String logoutSuccessView(Model model) {
		
		return "logoutSuccess";
	}
}
