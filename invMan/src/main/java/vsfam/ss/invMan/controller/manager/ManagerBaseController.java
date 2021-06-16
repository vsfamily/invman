package vsfam.ss.invMan.controller.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;

import vsfam.ss.invMan.common.YesNo;

public class ManagerBaseController {

	@ModelAttribute("viewLeftMenu")
	public String viewLeftMenu() {
		return "manager";
	}
	
	@ModelAttribute("listYesNo")
	public List<YesNo> getListYesNo(){
		
		List<YesNo> listYesNo = new ArrayList<YesNo>();
		
		listYesNo.add(new YesNo(0, "False"));
		listYesNo.add(new YesNo(1, "True"));
		
		return listYesNo;
	}
}
