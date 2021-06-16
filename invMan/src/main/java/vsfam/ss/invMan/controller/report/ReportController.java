package vsfam.ss.invMan.controller.report;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportController extends ReportBaseController {

	@RequestMapping(value="/report", method=RequestMethod.GET)
	public String indexView(Model model) {
		
		return "report/default";
	}
}
