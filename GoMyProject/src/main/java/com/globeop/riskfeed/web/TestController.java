package com.globeop.riskfeed.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.globeop.riskfeed.dto.Test;
import com.globeop.riskfeed.dto.TestDto;
import com.globeop.riskfeed.entity.ClientOnboardTable;
import com.globeop.riskfeed.entity.ClientTable;
import com.globeop.riskfeed.entity.FundTable;
import com.globeop.riskfeed.entity.RiskAggregator;
import com.globeop.riskfeed.service.ClientOnboardService;
import com.globeop.riskfeed.service.ClientService;
import com.globeop.riskfeed.service.FundService;
import com.globeop.riskfeed.service.RiskAggregatorService;

import javassist.expr.NewArray;

@RestController
@RequestMapping("/api")
public class TestController {
	
	@Autowired
	private RiskAggregatorService riskAggregatorService;

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private FundService fundService;
	

	@Autowired
	private ClientOnboardService theClientOnboardService;
    
	/*
	 * @Autowired public TestController(RiskAggregatorService riskAggregatorService,
	 * ClientService clientService) { this.riskAggregatorService =
	 * riskAggregatorService; this.clientService = clientService; }
	 */

	/*
	 * @Autowired public TestController(RiskAggregatorService riskAggregatorService)
	 * { this.riskAggregatorService = riskAggregatorService; }
	 * 
	 * 
	 * @Autowired public TestController(ClientService clientService) {
	 * this.clientService = clientService; }
	 */



	@GetMapping("/riskaggregator")
	public List<RiskAggregator> riskaggregator () {	
		return riskAggregatorService.findAll();		
	} 
	
	@GetMapping("/client")
	public List<ClientTable> client () {	
		return clientService.findAll();		
	} 
	
	@GetMapping("/client2")
	public List<String> client2 () {	
		return clientService.findAll1();		
	}
	@GetMapping("/fund")
	public List<FundTable> fund () {	
		return fundService.findAll();		
	} 
	
	
	List<TestDto> data=new ArrayList<TestDto>();
	//rest api call
	//http://localhost:8080/api/Test/?req=firstRequest
	
	@GetMapping("/Test")
	public Test test(@RequestParam (value="req", required=false, defaultValue="") String request,@RequestParam(value = "columns[]", required = false) String[]  requestedColumns) {
		//List<Object> list = new ArrayList<Object>();
		System.out.println( " request " +request+ " requestedColumns "+Arrays.toString(requestedColumns) );
		//for(String s: requestedColumns) {System.out::println}
		List<String> columns = new ArrayList<String>();
		columns.add("riskAggregatorName");
		columns.add("clientName");
		columns.add("fundName");
		columns.add("frequency");	
		columns.add("setUpDate");
		columns.add("setupFee");
		columns.add("monthlyFee");
		columns.add("billStartDate");
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
			// TODO: handle exception
		}
		
		  
		 
		//columns.add(requestedColumns);
		Test t = new Test(columns,data);

		
		return t;
	}
	
	@GetMapping("/Test2")
	public List<ClientOnboardTable> test2() {	
		return theClientOnboardService.test2();		
	}
	
}
