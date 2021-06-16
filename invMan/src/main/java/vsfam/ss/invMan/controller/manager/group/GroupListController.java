package vsfam.ss.invMan.controller.manager.group;

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
import vsfam.ss.invMan.manager.dao.GroupRepo;
import vsfam.ss.invMan.manager.domain.Group;

@Controller
@RequestMapping("/manager/group")
public class GroupListController extends ManagerBaseController {
	
	@Autowired
	private GroupRepo groupRepo;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listGroup(Model model, Principal principal, HttpServletRequest request) {
		
		int pageNumber = 0;
		
		if (request.getSession().getAttribute("listGroup_pageNumber")==null) pageNumber = 0;
		else pageNumber = (int) request.getSession().getAttribute("listGroup_pageNumber");
		
		Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.ASC, "code"));
		
		Page<Group> page = this.groupRepo.findAll(pageable);
		
		int totalPages = page.getTotalPages();
		
		model.addAttribute("listGroup", page.getContent());
		
		model.addAttribute("currentPage", pageNumber + 1);
		model.addAttribute("totalPages", totalPages);
		
		if (pageNumber == 0) model.addAttribute("firstPage", true);
		else model.addAttribute("firstPage", false);
		
		if (pageNumber == (totalPages-1)) {
			model.addAttribute("lastPage", true);
		} else {
			model.addAttribute("lastPage", false);
		}
		
		request.getSession().setAttribute("listGroup_pageNumber", pageNumber);
		request.getSession().setAttribute("listGroup_totalPages", totalPages);
		
		return "manager/group/list";
	}
	
	@RequestMapping(value = "/list/{whichPage}", method = RequestMethod.GET)
	public String listGroup(@PathVariable String whichPage, Model model, Principal principal, HttpServletRequest request) {
		
		try {
			int pageNumber = (int) request.getSession().getAttribute("listGroup_pageNumber");
			int totalPages = (int) request.getSession().getAttribute("listGroup_totalPages");
			
			if ("previous".equals(whichPage)) {
				if (pageNumber == 0) return "redirect:/manager/group/list";
				else {
					pageNumber--; 
				}
			} else if ("last".equals(whichPage)) {
				pageNumber = totalPages - 1;
			} else {
				if (pageNumber+1 < totalPages) pageNumber++;
			}
			
			Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.ASC, "code"));
			
			Page<Group> page = this.groupRepo.findAll(pageable);
			
			model.addAttribute("currentPage", pageNumber + 1);
			model.addAttribute("totalPages", totalPages);
			
			if (pageNumber == 0) model.addAttribute("firstPage", true);
			else model.addAttribute("firstPage", false);
			
			if (pageNumber == (totalPages-1)) {
				model.addAttribute("lastPage", true);
			} else {
				model.addAttribute("lastPage", false);
			}
			
			request.getSession().setAttribute("listGroup_pageNumber", pageNumber);
			request.getSession().setAttribute("listGroup_totalPages", totalPages);
			
			model.addAttribute("listGroup", page.getContent());
			
			return "manager/group/list";
		
		} catch(Exception e) {
			return "redirect:/manager/group/list";
		}
	}
}
