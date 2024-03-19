package com.aptech.admin.setting.country;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aptech.common.entity.Country;

public interface CountryRepository extends CrudRepository<Country, Integer> {
	public List<Country> findAllByOrderByNameAsc();
}
