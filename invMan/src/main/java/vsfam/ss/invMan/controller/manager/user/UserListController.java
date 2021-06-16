package vsfam.ss.invMan.controller.manager.user;

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
import vsfam.ss.invMan.manager.dao.UserRepo;
import vsfam.ss.invMan.manager.domain.User;

@Controller
@RequestMapping("/manager/user")
public class UserListController extends ManagerBaseController {
	
	@Autowired
	private UserRepo userRepo;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listUser(Model model, Principal principal, HttpServletRequest request) {
		
		int pageNumber = 0;
		
		if (request.getSession().getAttribute("listUser_pageNumber")==null) pageNumber = 0;
		else pageNumber = (int) request.getSession().getAttribute("listUser_pageNumber");
		
		Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.ASC, "fullName"));
		
		Page<User> page = this.userRepo.findAll(pageable);
		
		int totalPages = page.getTotalPages();
		
		model.addAttribute("listUser", page.getContent());
		
		model.addAttribute("currentPage", pageNumber + 1);
		model.addAttribute("totalPages", totalPages);
		
		if (pageNumber == 0) model.addAttribute("firstPage", true);
		else model.addAttribute("firstPage", false);
		
		if (pageNumber == (totalPages-1)) {
			model.addAttribute("lastPage", true);
		} else {
			model.addAttribute("lastPage", false);
		}
		
		request.getSession().setAttribute("listUser_pageNumber", pageNumber);
		request.getSession().setAttribute("listUser_totalPages", totalPages);
		
		return "manager/user/list";
	}
	
	@RequestMapping(value = "/list/{whichPage}", method = RequestMethod.GET)
	public String listUser(@PathVariable String whichPage, Model model, Principal principal, HttpServletRequest request) {
		
		try {
			int pageNumber = (int) request.getSession().getAttribute("listUser_pageNumber");
			int totalPages = (int) request.getSession().getAttribute("listUser_totalPages");
			
			if ("previous".equals(whichPage)) {
				if (pageNumber == 0) return "redirect:/manager/user/list";
				else {
					pageNumber--; 
				}
			} else if ("last".equals(whichPage)) {
				pageNumber = totalPages - 1;
			} else {
				if (pageNumber+1 < totalPages) pageNumber++;
			}
			
			Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.ASC, "fullName"));
			
			Page<User> page = this.userRepo.findAll(pageable);
			
			model.addAttribute("currentPage", pageNumber + 1);
			model.addAttribute("totalPages", totalPages);
			
			if (pageNumber == 0) model.addAttribute("firstPage", true);
			else model.addAttribute("firstPage", false);
			
			if (pageNumber == (totalPages-1)) {
				model.addAttribute("lastPage", true);
			} else {
				model.addAttribute("lastPage", false);
			}
			
			request.getSession().setAttribute("listUser_pageNumber", pageNumber);
			request.getSession().setAttribute("listUser_totalPages", totalPages);
			
			model.addAttribute("listUser", page.getContent());
			
			return "manager/user/list";
		
		} catch(Exception e) {
			return "redirect:/manager/user/list";
		}
	}
}