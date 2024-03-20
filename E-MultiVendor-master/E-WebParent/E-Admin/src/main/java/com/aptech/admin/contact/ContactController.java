package com.aptech.admin.contact;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.aptech.admin.paging.PagingAndSortingHelper;
import com.aptech.admin.paging.PagingAndSortingParam;
import com.aptech.common.exception.ContactNotFoundException;



@Controller
public class ContactController {
	private String defaultRedirectURL = "redirect:/contacts/page/1?sortField=name&sortDir=asc";
	
	@Autowired  ContactService service;
	
	@GetMapping("/contacts")
	public String listFirstPage(Model model) {
		return defaultRedirectURL;
	}
	
	@GetMapping("/contacts/page/{pageNum}")
	public String listByPage(
			@PagingAndSortingParam(listName = "listContacts", moduleURL = "/contacts") PagingAndSortingHelper helper,
			@PathVariable(name = "pageNum") int pageNum) {

		service.listByPage(pageNum, helper);	
	
		
		return "contacts/contacts";
	}
	
	
	@GetMapping("/contacts/delete/{id}")
	public String deleteCustomer(@PathVariable Integer id, RedirectAttributes ra) {
		try {
			service.delete(id);			
			ra.addFlashAttribute("message", "The customer ID " + id + " has been deleted successfully.");			
		} catch (ContactNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
		}	
		return defaultRedirectURL;
	}
	
	
}
