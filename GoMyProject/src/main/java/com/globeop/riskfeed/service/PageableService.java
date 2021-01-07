package com.globeop.riskfeed.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PageableService<T> {

	public Page<T> findAllPage(Pageable pageable);
	
	public Page<T> findByIdPage(Pageable pageable, int id);
	
	public Page<T> getSearchDetails(Pageable pageable,String keyword);
}
