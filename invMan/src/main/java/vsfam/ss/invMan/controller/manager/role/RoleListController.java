package vsfam.ss.invMan.controller.manager.role;

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

import vsfam.ss.invMan.controller.manager.ManagerBaseController;
import vsfam.ss.invMan.manager.dao.RoleRepo;
import vsfam.ss.invMan.manager.domain.Role;

@Controller
@RequestMapping("/manager/role")
public class RoleListController extends ManagerBaseController {
	
	@Autowired
	private RoleRepo roleRepo;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listRole(Model model, Principal principal, HttpServletRequest request) {
		
		int pageNumber = 0;
		
		if (request.getSession().getAttribute("listRole_pageNumber")==null) pageNumber = 0;
		else pageNumber = (int) request.getSession().getAttribute("listRole_pageNumber");
		
		Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.ASC, "code"));
		
		Page<Role> page = this.roleRepo.findAll(pageable);
		
		int totalPages = page.getTotalPages();
		
		model.addAttribute("listRole", page.getContent());
		
		model.addAttribute("currentPage", pageNumber + 1);
		model.addAttribute("totalPages", totalPages);
		
		if (pageNumber == 0) model.addAttribute("firstPage", true);
		else model.addAttribute("firstPage", false);
		
		if (pageNumber == (totalPages-1)) {
			model.addAttribute("lastPage", true);
		} else {
			model.addAttribute("lastPage", false);
		}
		
		request.getSession().setAttribute("listRole_pageNumber", pageNumber);
		request.getSession().setAttribute("listRole_totalPages", totalPages);
		
		return "manager/role/list";
	}
	
	@RequestMapping(value = "/list/{whichPage}", method = RequestMethod.GET)
	public String listRole(@PathVariable String whichPage, Model model, Principal principal, HttpServletRequest request) {
		
		try {
			int pageNumber = (int) request.getSession().getAttribute("listRole_pageNumber");
			int totalPages = (int) request.getSession().getAttribute("listRole_totalPages");
			
			if ("previous".equals(whichPage)) {
				if (pageNumber == 0) return "redirect:/manager/role/list";
				else {
					pageNumber--; 
				}
			} else if ("last".equals(whichPage)) {
				pageNumber = totalPages - 1;
			} else {
				if (pageNumber+1 < totalPages) pageNumber++;
			}
			
			Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.ASC, "code"));
			
			Page<Role> page = this.roleRepo.findAll(pageable);
			
			model.addAttribute("currentPage", pageNumber + 1);
			model.addAttribute("totalPages", totalPages);
			
			if (pageNumber == 0) model.addAttribute("firstPage", true);
			else model.addAttribute("firstPage", false);
			
			if (pageNumber == (totalPages-1)) {
				model.addAttribute("lastPage", true);
			} else {
				model.addAttribute("lastPage", false);
			}
			
			request.getSession().setAttribute("listRole_pageNumber", pageNumber);
			request.getSession().setAttribute("listRole_totalPages", totalPages);
			
			model.addAttribute("listRole", page.getContent());
			
			return "manager/role/list";
		
		} catch(Exception e) {
			return "redirect:/manager/role/list";
		}
	}
}