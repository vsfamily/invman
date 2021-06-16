package vsfam.ss.invMan.controller.report;

import org.springframework.web.bind.annotation.ModelAttribute;

public class ReportBaseController {

	@ModelAttribute("viewLeftMenu")
	public String viewLeftMenu() {
		return "report";
	}

}
