package com.aptech.admin.shop;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.aptech.admin.paging.PagingAndSortingHelper;
import com.aptech.admin.setting.country.CountryRepository;
import com.aptech.common.entity.Country;
import com.aptech.common.entity.Customer;
import com.aptech.common.entity.Shop;
import com.aptech.common.exception.ShopNotFoundException;

@Service
@Transactional
public class ShopService {
	public static final int SHOPS_PER_PAGE = 10;
	
	@Autowired private ShopRepository shopRepo;
	
	public void listByPage(int pageNum, PagingAndSortingHelper helper) {
		helper.listEntities(pageNum, SHOPS_PER_PAGE, shopRepo);
	}
	
	public void updateShopEnabledStatus(Integer id, boolean enabled) {
		shopRepo.updateEnabledStatus(id, enabled);
	}
	
	public Shop get(Integer id) throws ShopNotFoundException {
		try {
			return shopRepo.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new ShopNotFoundException("Could not find any shops with ID " + id);
		}
	}
		
	
	public boolean isNameShopUnique(Integer id, String name) {
		Shop existShop = shopRepo.findByShop(name);

		if (existShop != null && existShop.getId() != id) {
			
			return false;
		}
		
		return true;
	}
	
	
	public void delete(Integer id) throws ShopNotFoundException {
		Long count = shopRepo.countById(id);
		if (count == null || count == 0) {
			throw new ShopNotFoundException("Could not find any shops with ID " + id);
		}
		
		shopRepo.deleteById(id);
	}
	
}
