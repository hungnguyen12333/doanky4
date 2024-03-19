package com.aptech.admin.shop;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.aptech.admin.paging.SearchRepository;
import com.aptech.common.entity.Shop;

public interface ShopRepository extends SearchRepository<Shop, Integer> {
	
	@Query("SELECT s FROM Shop s WHERE CONCAT(s.name, ' ', s.alias, ' ', s.createdTime, ' ', s.deliveryAddress, ' ', s.customer.email) LIKE %?1%")
	public Page<Shop> findAll(String keyword, Pageable pageable);
	
	@Query("UPDATE Shop s SET s.enabled = ?2 WHERE s.id = ?1")
	@Modifying
	public void updateEnabledStatus(Integer id, boolean enabled);
	
	@Query("SELECT s FROM Shop s WHERE s.name = ?1")
	public Shop findByShop(String name);
	
	public Long countById(Integer id);	
}
