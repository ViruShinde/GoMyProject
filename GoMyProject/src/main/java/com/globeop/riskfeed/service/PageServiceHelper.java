package com.globeop.riskfeed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.globeop.riskfeed.dto.SchedularDto;

@Service
public class PageServiceHelper {
	@Autowired
	ClientService theClientService;
	
	@Autowired
	FundService theFundService;
	
	private Sort sort;
	private Pageable pageable;
	private PageableService thePageableService;
	
	public Page getDetails(String id, String sortField, String requestFor, int pageNo, int recordSize) {
		//Sort sort = "asc".equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();			
		//Pageable pageable = PageRequest.of(0, 10, sort);
		//PageableService thePageableService=getService(requestFor);
		getServiceConfig(requestFor, sortField, pageNo, recordSize);
		if(null == id || "".equals(id)) {
			return thePageableService.findAllPage(pageable);
		}else {
			return thePageableService.findByIdPage(pageable,Integer.parseInt(id));
		}
		
		
			//Page<ClientTable> page=new RestPageImpl(theClientService.findAll(), pageable, 10);
			
		
		
	}
	
 public String commonMethod(Page page, Model model, int pageNo, int pageSize, String sortField, String sortDir, String pageName) {
		 
		 List details = page.getContent();			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());			
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("records", pageSize);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");			
			model.addAttribute("details", details);
			return pageName;
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
