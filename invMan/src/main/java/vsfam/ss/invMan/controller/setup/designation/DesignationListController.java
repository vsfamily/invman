package vsfam.ss.invMan.controller.setup.designation;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vsfam.ss.invMan.controller.setup.SetupBaseController;
import vsfam.ss.invMan.setup.dao.DesignationRepo;
import vsfam.ss.invMan.setup.domain.Designation;

@Controller
@RequestMapping("/setup/designation")
public class DesignationListController extends SetupBaseController {
	
	@Autowired
	private DesignationRepo designationRepo;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listDesignation(Model model, Principal principal, HttpServletRequest request) {
		
		int pageNumber = 0;
		
		if (request.getSession().getAttribute("listDesignation_pageNumber")==null) pageNumber = 0;
		else pageNumber = (int) request.getSession().getAttribute("listDesignation_pageNumber");
		
		Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.ASC, "code"));
		
		Page<Designation> page = this.designationRepo.findAll(pageable);
		
		int totalPages = page.getTotalPages();
		
		model.addAttribute("listDesignation", page.getContent());
		
		model.addAttribute("currentPage", pageNumber + 1);
		model.addAttribute("totalPages", totalPages);
		
		if (pageNumber == 0) model.addAttribute("firstPage", true);
		else model.addAttribute("firstPage", false);
		
		if (pageNumber == (totalPages-1)) {
			model.addAttribute("lastPage", true);
		} else {
			model.addAttribute("lastPage", false);
		}
		
		request.getSession().setAttribute("listDesignation_pageNumber", pageNumber);
		request.getSession().setAttribute("listDesignation_totalPages", totalPages);
		
		return "setup/designation/list";
	}
	
	@RequestMapping(value = "/list/{whichPage}", method = RequestMethod.GET)
	public String listDesignation(@PathVariable String whichPage, Model model, Principal principal, HttpServletRequest request) {
		
		try {
			int pageNumber = (int) request.getSession().getAttribute("listDesignation_pageNumber");
			int totalPages = (int) request.getSession().getAttribute("listDesignation_totalPages");
			
			if ("previous".equals(whichPage)) {
				if (pageNumber == 0) return "redirect:/setup/designation/list";
				else {
					pageNumber--; 
				}
			} else if ("last".equals(whichPage)) {
				pageNumber = totalPages - 1;
			} else {
				if (pageNumber+1 < totalPages) pageNumber++;
			}
			
			Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.ASC, "code"));
			
			Page<Designation> page = this.designationRepo.findAll(pageable);
			
			model.addAttribute("currentPage", pageNumber + 1);
			model.addAttribute("totalPages", totalPages);
			
			if (pageNumber == 0) model.addAttribute("firstPage", true);
			else model.addAttribute("firstPage", false);
			
			if (pageNumber == (totalPages-1)) {
				model.addAttribute("lastPage", true);
			} else {
				model.addAttribute("lastPage", false);
			}
			
			request.getSession().setAttribute("listDesignation_pageNumber", pageNumber);
			request.getSession().setAttribute("listDesignation_totalPages", totalPages);
			
			model.addAttribute("listDesignation", page.getContent());
			
			return "setup/designation/list";
		
		} catch(Exception e) {
			return "redirect:/setup/designation/list";
		}
	}
}
