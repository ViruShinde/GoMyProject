package com.globeop.riskfeed.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globeop.riskfeed.dto.Test;
import com.globeop.riskfeed.dto.TestDto;
import com.globeop.riskfeed.service.ClientOnboardService;
import com.globeop.riskfeed.service.ClientService;
import com.globeop.riskfeed.service.FundService;
import com.globeop.riskfeed.service.RiskAggregatorService;

@RestController
@RequestMapping("/api")
public class MainRestController {

	
	@Autowired
	private RiskAggregatorService riskAggregatorService;

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private FundService fundService;


	@Autowired
	private ClientOnboardService theClientOnboardService;
	
	List<TestDto> data=new ArrayList<TestDto>();
	
	
	
	//http://localhost:8080/rest/BillingDetails/?req=firstRequest
	@GetMapping("/allBillingDetails")
	public Test test(@RequestParam (value="req", required=false, defaultValue="") String request,@RequestParam(value = "columns[]", required = false) String[]  requestedColumns) {
		//List<Object> list = new ArrayList<Object>();
		System.out.println( " request " +request+ " requestedColumns "+Arrays.toString(requestedColumns) );
		//for(String s: requestedColumns) {System.out::println}
		List<String> columns = new ArrayList<String>();
		columns.add("riskAggregatorName");
		columns.add("clientName");
		columns.add("fundName");
		columns.add("frequency");	
		//columns.add("setUpDate");
		columns.add("setupFee");
		columns.add("monthlyFee");
		//columns.add("billStartDate");
		columns.add("isClientPayingOldCharges");

		// automationProcess , isActive isWaivedOff , endDate, billingComments developmentHours  developmentCost isDevelopmentWaivedOff developmentStartDate
				
		if("firstRequest".equals(request)) {
			data=theClientOnboardService.test();
		}
		
		try {
			if(requestedColumns.length>0) { 
				  for(String c : requestedColumns) {
					  if(c.length()>0)
					  columns.add(c); 
				  }			  
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		Test t = new Test(columns,data);		
		return t;
	}
	
	@GetMapping("/allPendingBillingDetails")
	public List<TestDto> test2() {
		return theClientOnboardService.getPendingBillingDetails();
	}
	
	@GetMapping("/overAllDetails")
	public List<TestDto> overAllDetails() {
		return theClientOnboardService.getOverAllDetails();
	}
}
