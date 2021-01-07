package com.globeop.riskfeed.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.globeop.riskfeed.dto.RestPageImpl;
import com.globeop.riskfeed.entity.ClientTable;

@Service
public class TestService{
	@Autowired
	ClientService theClientService;
	
	@Autowired
	FundService theFundService;
	
	private Sort sort;
	private Pageable pageable;
	private PageableService thePageableService;
	
	public Page getDetails(String id, String sortField, String requestFor) {
		//Sort sort = "asc".equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();			
		//Pageable pageable = PageRequest.of(0, 10, sort);
		//PageableService thePageableService=getService(requestFor);
		getServiceConfig(requestFor, sortField, 0, 10);
		if(null == id || "".equals(id)) {
			return thePageableService.findAllPage(pageable);
		}else {
			return thePageableService.findByIdPage(pageable,Integer.parseInt(id));
		}
		
		
			//Page<ClientTable> page=new RestPageImpl(theClientService.findAll(), pageable, 10);
			
		
		
	}
	
	public void getServiceConfig(String requestFor, String sortField, int startPage, int recordSize) {		
		if(requestFor.equals("client")) {
			thePageableService=theClientService;
			
		}else if(requestFor.equals("fund")) {
			thePageableService=theFundService;
		}
		sort = "asc".equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		pageable = PageRequest.of(startPage, recordSize, sort);
		
	}
	
	public Page getSearchDetails(String keyword, int record , String requestFor, String sortField) {
		getServiceConfig(requestFor, sortField, 0, record);
		
		return thePageableService.getSearchDetails(pageable, keyword);
	}
}
