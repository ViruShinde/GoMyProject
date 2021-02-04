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
	
	@Autowired
	ClientOnboardService theClientOnboardService;
	
	@Autowired
	RiskAggregatorService theRiskAggregatorService;
	
	@Autowired
	FtpService theFtpService;
	
	@Autowired
	DevelopmentService theDevelopmentService;
	
	private Sort sort;
	private Pageable pageable;
	private PageableService thePageableService;
	
	public Page getDetails(String requestFor,String id, int pageNo,String sortField,String sortDir,String keyword, int recordSize) {
		//Sort sort = "asc".equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();			
		//Pageable pageable = PageRequest.of(0, 10, sort);
		//PageableService thePageableService=getService(requestFor);
		System.out.println("req "+requestFor +" ID"+id);
		getServiceConfig(requestFor, sortField, pageNo, recordSize,sortDir);
		Page page=null;
		if(keyword == null || "".equals(keyword)) {
			if(null == id || "".equals(id)) {
				page= thePageableService.findAllPage(pageable);
			}else {
				page= thePageableService.findByIdPage(pageable,Integer.parseInt(id));
			}
		}else {
			if(null == id || "".equals(id)) {
				page=thePageableService.getSearchDetails(pageable, keyword);
			}else {
				page=thePageableService.getSearchDetails(pageable, keyword,Integer.parseInt(id));
			}
		}								
			
		return page;
		
	}

	public Page getDetails(String requestFor,String riskAggregatorId,String clientId, int pageNo,String sortField,String sortDir,String keyword, int recordSize) {
		//Sort sort = "asc".equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();			
		//Pageable pageable = PageRequest.of(0, 10, sort);
		//PageableService thePageableService=getService(requestFor);
		getServiceConfig(requestFor, sortField, pageNo, recordSize,sortDir);
		Page page=null;
		if(! ( (null == riskAggregatorId || "".equals(riskAggregatorId) ) && (null == clientId || "".equals(clientId) ) ) ) {
			if(keyword == null || "".equals(keyword)) {					
				page= theClientOnboardService.getDetails(pageable,requestFor, riskAggregatorId, clientId, pageNo, sortField, sortDir, keyword, recordSize);
			}else {
				page= theClientOnboardService.getSearchDetails(pageable,requestFor, riskAggregatorId, clientId, pageNo, sortField, sortDir, keyword, recordSize);
			}
		}
									
			
		return page;
		
	}
	
 public String commonMethod(String returnPage,String id, int pageNo,String sortField,String sortDir,String keyword, int recordSize, Page page, Model model) {
		 
		 List details = page.getContent();			
			model.addAttribute("currentPage", pageNo);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());			
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("records", recordSize);
			model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");			
			model.addAttribute("details", details);
			model.addAttribute("id", id);
			model.addAttribute("keyword",keyword);			
			
			  System.out.println("pageNo ="+pageNo+" totalPages="+page.getTotalPages()+" totalItems="+page.getTotalElements()+
					" sortField="+sortField+" sortDir="+sortDir+" records="+recordSize+" details="+details+" id="+id);
				
			return returnPage;
	 }
	
	
	public void getServiceConfig(String requestFor, String sortField, int startPage, int recordSize,String sortDir) {		
		if(requestFor.equals("client")) {
			thePageableService=theClientService;
			
		}else if(requestFor.equals("fund")) {
			thePageableService=theFundService;
		}else if(requestFor.equals("onboard") || requestFor.equals("fundByRiskAggAndClient")) {
			thePageableService=theClientOnboardService;
		}else if(requestFor.equals("riskAggregator")) {
			thePageableService=theRiskAggregatorService;
		}else if(requestFor.equals("ftpDetails")) {
			thePageableService=theFtpService;
		}else if(requestFor.equals("developmentDetails")) {
			thePageableService=theDevelopmentService;
		}
		
		if(startPage !=0) {
			startPage=startPage-1;
		}
		
		sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		pageable = PageRequest.of(startPage, recordSize, sort);
		
	}
	
	public Page getSearchDetails(String keyword, int record , String requestFor, String sortField,String sortDir) {
		getServiceConfig(requestFor, sortField, 0, record,sortDir);
		
		return thePageableService.getSearchDetails(pageable, keyword);
	}
}
