package com.globeop.riskfeed.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.globeop.riskfeed.service.MisReportService;

@RestController
public class MisReportController {

	@Autowired
	MisReportService theMisReportService;
	
	@RequestMapping(value="/getMisLog",method = RequestMethod.GET)
	@ResponseBody	
	//riskAggregator +" "+ client +" "+ fund +" "+ frequency +" "+ fromDate +" "+ toDate)
	//
	public Object getResult(@RequestParam (value="p1") String riskAggregator, @RequestParam (value="p2") String client,@RequestParam (value="p3") String fund,@RequestParam (value="p4") String frequency, @RequestParam (value="p5") String fromDate , @RequestParam (value="p6") String toDate){
    	//System.out.println(" INSIDE getResult list 1 "+riskAggregator +" 2"+ client +" 3"+ fund +" 4"+ frequency +" 5"+ fromDate +" 6"+ toDate);
    	//System.out.println(" INSIDE getResult list 1="+riskAggregator.length() +" 2="+ client.length() +" 3="+ fund.length() +" 4="+ frequency.length() +" 5="+ fromDate.length() +" 6="+ toDate.length());
    	//return theMisReportService.findAll();
    	return theMisReportService.getData(riskAggregator, client, fund, frequency, fromDate, toDate);
	}
}
