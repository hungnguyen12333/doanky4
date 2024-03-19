package com.aptech.admin.contact;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import com.aptech.admin.paging.SearchRepository;
import com.aptech.common.entity.Contact;

public interface ContactRepository extends SearchRepository<Contact, Integer> {

	@Query("SELECT c FROM Contact c WHERE CONCAT(c.name, ' ', c.email, ' ', c.phone, ' ', c.address, ' ', c.message) LIKE %?1%")
	public Page<Contact> findAll(String keyword, Pageable pageable);
	
	public Long countById(Integer id);
}
