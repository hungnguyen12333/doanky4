package com.aptech.admin.setting.state;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aptech.common.entity.Country;
import com.aptech.common.entity.State;

public interface StateRepository extends CrudRepository<State, Integer> {
	
	public List<State> findByCountryOrderByNameAsc(Country country);
}

