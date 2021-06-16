package vsfam.ss.invMan.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController extends HomeBaseController {

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String indexView(Model model) {
		
		return "home/default";
	}
}
