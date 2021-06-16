package vsfam.ss.invMan.controller.setup.state;

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
import vsfam.ss.invMan.setup.dao.StateRepo;
import vsfam.ss.invMan.setup.domain.State;

@Controller
@RequestMapping("/setup/state")
public class StateListController extends SetupBaseController {
	
	@Autowired
	private StateRepo stateRepo;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listState(Model model, Principal principal, HttpServletRequest request) {
		
		int pageNumber = 0;
		
		if (request.getSession().getAttribute("listState_pageNumber")==null) pageNumber = 0;
		else pageNumber = (int) request.getSession().getAttribute("listState_pageNumber");
		
		Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.ASC, "code"));
		
		Page<State> page = this.stateRepo.findAll(pageable);
		
		int totalPages = page.getTotalPages();
		
		model.addAttribute("listState", page.getContent());
		
		model.addAttribute("currentPage", pageNumber + 1);
		model.addAttribute("totalPages", totalPages);
		
		if (pageNumber == 0) model.addAttribute("firstPage", true);
		else model.addAttribute("firstPage", false);
		
		if (pageNumber == (totalPages-1)) {
			model.addAttribute("lastPage", true);
		} else {
			model.addAttribute("lastPage", false);
		}
		
		request.getSession().setAttribute("listState_pageNumber", pageNumber);
		request.getSession().setAttribute("listState_totalPages", totalPages);
		
		return "setup/state/list";
	}
	
	@RequestMapping(value = "/list/{whichPage}", method = RequestMethod.GET)
	public String listState(@PathVariable String whichPage, Model model, Principal principal, HttpServletRequest request) {
		
		try {
			int pageNumber = (int) request.getSession().getAttribute("listState_pageNumber");
			int totalPages = (int) request.getSession().getAttribute("listState_totalPages");
		
			if ("previous".equals(whichPage)) {
				if (pageNumber == 0) return "redirect:/setup/state/list";
				else {
					pageNumber--; 
				}
			} else if ("last".equals(whichPage)) {
				pageNumber = totalPages - 1;
			} else {
				if (pageNumber+1 < totalPages) pageNumber++;
			}
			
			Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.ASC, "code"));
			
			Page<State> page = this.stateRepo.findAll(pageable);
			
			model.addAttribute("currentPage", pageNumber + 1);
			model.addAttribute("totalPages", totalPages);
			
			if (pageNumber == 0) model.addAttribute("firstPage", true);
			else model.addAttribute("firstPage", false);
			
			if (pageNumber == (totalPages-1)) {
				model.addAttribute("lastPage", true);
			} else {
				model.addAttribute("lastPage", false);
			}
			
			request.getSession().setAttribute("listState_pageNumber", pageNumber);
			request.getSession().setAttribute("listState_totalPages", totalPages);
			
			model.addAttribute("listState", page.getContent());
			
			return "setup/state/list";
		
		} catch(Exception e) {
			return "redirect:/setup/state/list";
		}
	}
}
