package com.aptech.admin.contact;



import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aptech.admin.paging.PagingAndSortingHelper;
import com.aptech.common.exception.ContactNotFoundException;
import com.aptech.common.exception.CustomerNotFoundException;


@Service
@Transactional
public class ContactService {
	public static final int CUSTOMERS_PER_PAGE = 5;
	
	@Autowired private ContactRepository contactUsRepository;
	
	public void listByPage(int pageNum, PagingAndSortingHelper helper) {
		helper.listEntities(pageNum, CUSTOMERS_PER_PAGE, contactUsRepository);
	}
	
	public void delete(Integer id) throws ContactNotFoundException {
		Long count = contactUsRepository.countById(id);
		if (count == null || count == 0) {
			throw new ContactNotFoundException("Could not find any contact with ID " + id);
		}
		
		contactUsRepository.deleteById(id);
	}
}
