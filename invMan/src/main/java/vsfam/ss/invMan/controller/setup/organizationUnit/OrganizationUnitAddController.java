package vsfam.ss.invMan.controller.setup.organizationUnit;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vsfam.ss.invMan.common.TransactionResult;
import vsfam.ss.invMan.controller.setup.SetupBaseController;
import vsfam.ss.invMan.setup.dao.OrganizationUnitRepo;
import vsfam.ss.invMan.setup.dao.OrganizationUnitTypeRepo;
import vsfam.ss.invMan.setup.domain.OrganizationUnit;
import vsfam.ss.invMan.setup.domain.OrganizationUnitType;
import vsfam.ss.invMan.setup.service.OrganizationUnitService;
import vsfam.ss.invMan.setup.validators.add.OrganizationUnitAddValidator;

@Controller
@RequestMapping(value = "/setup/organizationUnit/addNew")
public class OrganizationUnitAddController extends SetupBaseController {

	@Autowired
	private OrganizationUnitService organizationUnitService;

	@Autowired
	private OrganizationUnitRepo organizationUnitRepo;
	
	@Autowired
	private OrganizationUnitTypeRepo organizationUnitTypeRepo;
	
	@ModelAttribute("listOrganizationUnit")
	public List<OrganizationUnit> getListOrganizationUnit(){
		return (List<OrganizationUnit>) this.organizationUnitRepo.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
	
	@ModelAttribute("listOrganizationUnitType")
	public List<OrganizationUnitType> getListOrganizationUnitType(){
		return (List<OrganizationUnitType>) this.organizationUnitTypeRepo.findAll(Sort.by(Sort.Direction.ASC, "description"));
	}
	
	@Autowired
	private OrganizationUnitAddValidator organizationUnitAddValidator;
	
	@RequestMapping(method = RequestMethod.GET)
	public String organizationUnitAdd(Model model) {
		
		OrganizationUnit organizationUnit = new OrganizationUnit();
		
		model.addAttribute(organizationUnit);
		
		return "setup/organizationUnit/addNew";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String organizationUnitAdd(@ModelAttribute OrganizationUnit organizationUnit,
			BindingResult result, Model model, RedirectAttributes reat, Principal principal) {
		
		this.organizationUnitAddValidator.validate(organizationUnit, result);
		
		if (result.hasErrors()) {
			return "setup/organizationUnit/addNew";
		}
		
		try {
			TransactionResult tr = this.organizationUnitService.addOrganizationUnit(organizationUnit, principal.getName());
			
			if (tr == null) {
				reat.addFlashAttribute("message", "Record not added. Please try again later.");
				return "setup/organizationUnit/addNew";
			} else {
				reat.addFlashAttribute("message", "Record added successfully.");
			}
			
			return "redirect:/setup/organizationUnit/addNew";
		} catch (Exception e) {
			reat.addFlashAttribute("message", e.getMessage());
			return "setup/organizationUnit/addNew";
		}
	}
}
