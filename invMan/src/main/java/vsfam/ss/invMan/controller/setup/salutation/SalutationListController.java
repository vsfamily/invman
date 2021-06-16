package vsfam.ss.invMan.controller.setup.salutation;

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
import vsfam.ss.invMan.setup.dao.SalutationRepo;
import vsfam.ss.invMan.setup.domain.Salutation;

@Controller
@RequestMapping("/setup/salutation")
public class SalutationListController extends SetupBaseController {
	
	@Autowired
	private SalutationRepo salutationRepo;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String listSalutation(Model model, Principal principal, HttpServletRequest request) {
		
		int pageNumber = 0;
		
		if (request.getSession().getAttribute("listSalutation_pageNumber")==null) pageNumber = 0;
		else pageNumber = (int) request.getSession().getAttribute("listSalutation_pageNumber");
		
		Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.ASC, "code"));
		
		Page<Salutation> page = this.salutationRepo.findAll(pageable);
		
		int totalPages = page.getTotalPages();
		
		model.addAttribute("listSalutation", page.getContent());
		
		model.addAttribute("currentPage", pageNumber + 1);
		model.addAttribute("totalPages", totalPages);
		
		if (pageNumber == 0) model.addAttribute("firstPage", true);
		else model.addAttribute("firstPage", false);
		
		if (pageNumber == (totalPages-1)) {
			model.addAttribute("lastPage", true);
		} else {
			model.addAttribute("lastPage", false);
		}
		
		request.getSession().setAttribute("listSalutation_pageNumber", pageNumber);
		request.getSession().setAttribute("listSalutation_totalPages", totalPages);
		
		return "setup/salutation/list";
	}
	
	@RequestMapping(value = "/list/{whichPage}", method = RequestMethod.GET)
	public String listSalutation(@PathVariable String whichPage, Model model, Principal principal, HttpServletRequest request) {
		
		try {
			int pageNumber = (int) request.getSession().getAttribute("listSalutation_pageNumber");
			int totalPages = (int) request.getSession().getAttribute("listSalutation_totalPages");
			
			if ("previous".equals(whichPage)) {
				if (pageNumber == 0) return "redirect:/setup/salutation/list";
				else {
					pageNumber--; 
				}
			} else if ("last".equals(whichPage)) {
				pageNumber = totalPages - 1;
			} else {
				if (pageNumber+1 < totalPages) pageNumber++;
			}
			
			Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by(Sort.Direction.ASC, "code"));
			
			Page<Salutation> page = this.salutationRepo.findAll(pageable);
			
			model.addAttribute("currentPage", pageNumber + 1);
			model.addAttribute("totalPages", totalPages);
			
			if (pageNumber == 0) model.addAttribute("firstPage", true);
			else model.addAttribute("firstPage", false);
			
			if (pageNumber == (totalPages-1)) {
				model.addAttribute("lastPage", true);
			} else {
				model.addAttribute("lastPage", false);
			}
			
			request.getSession().setAttribute("listSalutation_pageNumber", pageNumber);
			request.getSession().setAttribute("listSalutation_totalPages", totalPages);
			
			model.addAttribute("listSalutation", page.getContent());
			
			return "setup/salutation/list";
			
		} catch(Exception e) {
			return "redirect:/setup/salutation/list";
		}
	}
}
