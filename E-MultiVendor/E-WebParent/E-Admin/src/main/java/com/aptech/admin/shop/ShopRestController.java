package com.aptech.admin.shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopRestController {
	@Autowired
	private ShopService service;
	
	@PostMapping("/shops/checkNameShop")
	public String checkDuplicateNameShop(Integer id, String name) {
		if (service.isNameShopUnique(id, name)) {
			return "OK";
		} else {
			return "Duplicated";
		}
	}
}
