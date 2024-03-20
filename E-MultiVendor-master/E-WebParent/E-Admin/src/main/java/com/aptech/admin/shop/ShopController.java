package com.aptech.admin.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.aptech.admin.paging.PagingAndSortingHelper;
import com.aptech.admin.paging.PagingAndSortingParam;
import com.aptech.common.entity.Shop;
import com.aptech.common.exception.ShopNotFoundException;

@Controller
public class ShopController {
	private String defaultRedirectURL = "redirect:/shops/page/1?sortField=name&sortDir=asc";
	
	@Autowired private ShopService service;
	
	@GetMapping("/shops")
	public String listFirstPage(Model model) {
		
		return defaultRedirectURL;
	}

	@GetMapping("/shops/page/{pageNum}")
	public String listByPage(
			@PagingAndSortingParam(listName = "listShops", moduleURL = "/shops") PagingAndSortingHelper helper,
			@PathVariable(name = "pageNum") int pageNum) {

		service.listByPage(pageNum, helper);
		
		return "shops/shops";
	}
	
	@GetMapping("/shops/{id}/enabled/{status}")
	public String updateShopEnabledStatus(@PathVariable("id") Integer id,
			@PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
		service.updateShopEnabledStatus(id, enabled);
		String status = enabled ? "enabled" : "disabled";
		String message = "The Shop ID " + id + " has been " + status;
		redirectAttributes.addFlashAttribute("message", message);
		
		return defaultRedirectURL;
	}	
	
	@GetMapping("/shops/detail/{id}")
	public String viewShop(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
		try {
			Shop shop = service.get(id);
			model.addAttribute("shop", shop);
			
			return "shops/shop_detail_modal";
		} catch (ShopNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
			return defaultRedirectURL;		
		}
	}
	
	@GetMapping("/shops/edit/{id}")
	public String editShop(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
		try {
			Shop shop = service.get(id);		
			model.addAttribute("shops", shop);
			model.addAttribute("pageTitle", String.format("Edit Shop (ID: %d)", id));
			
			return "shops/shop_form";
			
		} catch (ShopNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
			return defaultRedirectURL;
		}
	}
	
	
	@GetMapping("/shops/delete/{id}")
	public String deleteShop(@PathVariable Integer id, RedirectAttributes ra) {
		try {
			service.delete(id);			
			ra.addFlashAttribute("message", "The Shop ID " + id + " has been deleted successfully.");
			
		} catch (ShopNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
		}
		
		return defaultRedirectURL;
	}
	
}

