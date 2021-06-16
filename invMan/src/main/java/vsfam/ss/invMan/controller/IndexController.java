package vsfam.ss.invMan.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController extends BaseController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String indexView(Model model, Principal principal) {
		
		return "index";
		
	}
}
