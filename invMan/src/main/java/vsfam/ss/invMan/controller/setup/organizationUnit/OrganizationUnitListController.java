package vsfam.ss.invMan.controller.setup.organizationUnit;

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
import vsfam.ss.invMan.setup.dao.OrganizationUnitRepo;
import vsfam.ss.invMan.setup.domain.OrganizationUnit;

@Controller
@RequestMapping("/setup/organizationUnit")
public class OrganizationUnitListController extends SetupBaseController {
	
	@Autowired
	private OrganizationUnitRepo organizationUnitRepo;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listOrganizationUnit(Model model, Principal principal, HttpServletRequest request) {
		
		int pageNumber = 0;
		
		if (request.getSession().getAttribute("listOrganizationUnit_pageNumber")==null) pageNumber = 0;
		else pageNumber = (int) request.getSession().getAttribute("listOrganizationUnit_pageNumber");
		
		Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.ASC, "code"));
		
		Page<OrganizationUnit> page = this.organizationUnitRepo.findAll(pageable);
		
		int totalPages = page.getTotalPages();
		
		model.addAttribute("listOrganizationUnit", page.getContent());
		
		model.addAttribute("currentPage", pageNumber + 1);
		model.addAttribute("totalPages", totalPages);
		
		if (pageNumber == 0) model.addAttribute("firstPage", true);
		else model.addAttribute("firstPage", false);
		
		if (pageNumber == (totalPages-1)) {
			model.addAttribute("lastPage", true);
		} else {
			model.addAttribute("lastPage", false);
		}
		
		request.getSession().setAttribute("listOrganizationUnit_pageNumber", pageNumber);
		request.getSession().setAttribute("listOrganizationUnit_totalPages", totalPages);
		
		return "setup/organizationUnit/list";
	}
	
	@RequestMapping(value = "/list/{whichPage}", method = RequestMethod.GET)
	public String listOrganizationUnit(@PathVariable String whichPage, Model model, Principal principal, HttpServletRequest request) {
		
		try {
			int pageNumber = (int) request.getSession().getAttribute("listOrganizationUnit_pageNumber");
			int totalPages = (int) request.getSession().getAttribute("listOrganizationUnit_totalPages");
			
			if ("previous".equals(whichPage)) {
				if (pageNumber == 0) return "redirect:/setup/organizationUnit/list";
				else {
					pageNumber--; 
				}
			} else if ("last".equals(whichPage)) {
				pageNumber = totalPages - 1;
			} else {
				if (pageNumber+1 < totalPages) pageNumber++;
			}
			
			Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.ASC, "code"));
			
			Page<OrganizationUnit> page = this.organizationUnitRepo.findAll(pageable);
			
			model.addAttribute("currentPage", pageNumber + 1);
			model.addAttribute("totalPages", totalPages);
			
			if (pageNumber == 0) model.addAttribute("firstPage", true);
			else model.addAttribute("firstPage", false);
			
			if (pageNumber == (totalPages-1)) {
				model.addAttribute("lastPage", true);
			} else {
				model.addAttribute("lastPage", false);
			}
			
			request.getSession().setAttribute("listOrganizationUnit_pageNumber", pageNumber);
			request.getSession().setAttribute("listOrganizationUnit_totalPages", totalPages);
			
			model.addAttribute("listOrganizationUnit", page.getContent());
			
			return "setup/organizationUnit/list";
		
		} catch(Exception e) {
			return "redirect:/setup/organizationUnit/list";
		}
	}
}
