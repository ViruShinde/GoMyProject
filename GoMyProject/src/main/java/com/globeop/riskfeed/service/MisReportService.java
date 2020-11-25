package com.globeop.riskfeed.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.globeop.riskfeed.dto.GirdData;
import com.globeop.riskfeed.entity.MisReport;
import com.globeop.riskfeed.repository.MisReportRepository;

@Service
public class MisReportService {
	
	@Autowired
	MisReportRepository theMisReportRepository;
	
	
	MisReport theMisReport=new MisReport();

	public GirdData findAll() {
		/*
		GirdData gridData = new GirdData();
		List header = new ArrayList();
		List data = new ArrayList();
		
		Map headerDataMap=null;
		
		headerDataMap=new HashMap();
		headerDataMap.put("headerName", rsmeta.getColumnName(i));
		headerDataMap.put("field", rsmeta.getColumnName(i));
		headerDataMap.put("sortable", true);
		headerDataMap.put("filter", true);	
		header.add(headerDataMap);
		*/
		GirdData gridData = new GirdData();
		List header = tableColumnsName();
		gridData.setHeader(header);
		List<MisReport> l=theMisReportRepository.findAll();
		gridData.setData(l);
		//System.out.println("@@@@@@@@ "+gridData);
		
		return gridData;
	}
	
	public GirdData getData(String riskAggregator, String client, String fund, String frequency, String fromDate, String toDate){
		
		
		GirdData gridData = new GirdData();
		List header = tableColumnsName();
		gridData.setHeader(header);
		
		//List<MisReport> l=theMisReportRepository.findAll(Specification.where(hasRiskAggregator(riskAggregator)).and(hasClient(client)) );
		List<MisReport> l=null;
		/*
		if( ! ("".equals(riskAggregator)  && "".equals(client) && "".equals(fund) && "".equals(frequency)) ) {
			System.out.println("@ 1");
			l=theMisReportRepository.findAll(Specification.where(hasRiskAggregator(riskAggregator)).and(hasClient(client)).and(hasFund(fund)).and(hasFrequency(frequency)).and(hasFromDate(fromDate)).and(hasToDate(toDate)) );
		}else if(! ("".equals(riskAggregator)  && "".equals(client) && "".equals(fund)) ) {
			System.out.println("@ 2");
			l=theMisReportRepository.findAll(Specification.where(hasRiskAggregator(riskAggregator)).and(hasClient(client)).and(hasFund(fund)).and(hasFromDate(fromDate)).and(hasToDate(toDate)) );
		}else if(! ("".equals(riskAggregator)  && "".equals(client)) ) {
			System.out.println("@ 3");
			l=theMisReportRepository.findAll(Specification.where(hasRiskAggregator(riskAggregator)).and(hasClient(client)).and(hasFromDate(fromDate)).and(hasToDate(toDate)) );
		}else if(! ("".equals(client)) ) {
			System.out.println("@ 4");
			l=theMisReportRepository.findAll(Specification.where( hasClient(client)).and(hasFromDate(fromDate)).and(hasToDate(toDate)) );
		}
		*/
		//1st all blank only date 		
		if(  "".equals(riskAggregator)  && "".equals(client) && "".equals(fund) && "".equals(frequency) ) {
			//System.out.println("@ 1");
			l=theMisReportRepository.findAll(Specification.where(hasFromDate(fromDate)).and(hasToDate(toDate)) );
		}
		// only freqeuncy 
		else if( ("".equals(riskAggregator)  && "".equals(client) && "".equals(fund)) ) {
			//System.out.println("@ 2");
			l=theMisReportRepository.findAll(Specification.where(hasFrequency(frequency)).and(hasFromDate(fromDate)).and(hasToDate(toDate)) );
		}
		// only riskAggregator
		else if( "".equals(client) && "".equals(fund) && "".equals(frequency) ) {
			//System.out.println("@ 3");
			l=theMisReportRepository.findAll(Specification.where(hasRiskAggregator(riskAggregator)).and(hasFromDate(fromDate)).and(hasToDate(toDate)) );			
		}
		//only client
		else if(  "".equals(riskAggregator)  && "".equals(fund) && "".equals(frequency) ) {
			//System.out.println("@ 4");
			l=theMisReportRepository.findAll(Specification.where(hasClient(client)).and(hasFromDate(fromDate)).and(hasToDate(toDate)) );
		}
		// riskaggregator and client
		else if( "".equals(fund) && "".equals(frequency) ) {
			//System.out.println("@ 5");
			l=theMisReportRepository.findAll(Specification.where(hasRiskAggregator(riskAggregator)).and(hasClient(client)).and(hasFromDate(fromDate)).and(hasToDate(toDate)) );
		}
		// riskaggregator and Frequency
		else if( "".equals(client) && "".equals(fund) ) {
			//System.out.println("@ 6");
			l=theMisReportRepository.findAll(Specification.where(hasRiskAggregator(riskAggregator)).and(hasFrequency(frequency)).and(hasFromDate(fromDate)).and(hasToDate(toDate)) );
		}
		// client and Frequency
		else if( "".equals(riskAggregator) && "".equals(fund) ) {
			//System.out.println("@ 7");
			l=theMisReportRepository.findAll(Specification.where(hasClient(client)).and(hasFrequency(frequency)).and(hasFromDate(fromDate)).and(hasToDate(toDate)) );
		}
		// riskaggregator and client and frequency
		else if( "".equals(fund) ) {
			//System.out.println("@ 8");
			l=theMisReportRepository.findAll(Specification.where(hasRiskAggregator(riskAggregator)).and(hasClient(client)).and(hasFrequency(frequency)).and(hasFromDate(fromDate)).and(hasToDate(toDate)) );
		}
		// riskaggregator and client and fund
		else if( "".equals(frequency) ) {
			//System.out.println("@ 9");
			l=theMisReportRepository.findAll(Specification.where(hasRiskAggregator(riskAggregator)).and(hasClient(client)).and(hasFund(fund)).and(hasFromDate(fromDate)).and(hasToDate(toDate)) );
		}
		// client fund and frequency
		else if( "".equals(riskAggregator) ) {
			//System.out.println("@ 10");
			l=theMisReportRepository.findAll(Specification.where(hasClient(client)).and(hasFund(fund)).and(hasFrequency(frequency)).and(hasFromDate(fromDate)).and(hasToDate(toDate)) );
		}else {
			//System.out.println("@ else");
			l=theMisReportRepository.findAll(Specification.where(hasRiskAggregator(riskAggregator)).and(hasClient(client)).and(hasFund(fund)).and(hasFrequency(frequency)).and(hasFromDate(fromDate)).and(hasToDate(toDate)) );
		}
		
		
		//l=theMisReportRepository.findAll(Specification.where(hasRiskAggregator(riskAggregator)).and(hasClient(client)).and(hasFund(fund)).and(hasFrequency(frequency)).and(hasFromDate(fromDate)).and(hasToDate(toDate)) );
		gridData.setData(l);
		return gridData;
	}
	
	

	static Specification<MisReport> hasRiskAggregator(String riskAggregator) {
	    return (theMisReport, cq, cb) -> cb.equal(theMisReport.get("riskAggregator"), riskAggregator);
	}
	 
	static Specification<MisReport> hasClient(String client) {
	    return (theMisReport, cq, cb) -> cb.equal(theMisReport.get("client"), client);
	}
	
	static Specification<MisReport> hasFund(String fund) {
	    return (theMisReport, cq, cb) -> cb.equal(theMisReport.get("fund"), fund);
	}
	
	static Specification<MisReport> hasFrequency(String frequency) {
	    return (theMisReport, cq, cb) -> cb.equal(theMisReport.get("frequency"), frequency);
	}
	
	static Specification<MisReport> hasFromDate(String fromDate) {
	    return (theMisReport, cq, cb) -> cb.greaterThanOrEqualTo(theMisReport.get("cobDate"), fromDate);
	}
	
	static Specification<MisReport> hasToDate(String toDate) {
	    return (theMisReport, cq, cb) -> cb.lessThanOrEqualTo(theMisReport.get("cobDate"), toDate);
	}
	
	
	 public List<String> tableColumnsName(){   
		List header = new ArrayList();			
		Map headerDataMap=null;		 	
	    List<String> Columns = new ArrayList<String>();
	    Field[] fields = MisReport.class.getDeclaredFields();
        for (Field field : fields) {
        	Column col = field.getAnnotation(Column.class);
	        if (col != null) {	      
	        	headerDataMap=new HashMap();
				headerDataMap.put("headerName", col.name());
				headerDataMap.put("field", col.name());
				headerDataMap.put("sortable", true);
				headerDataMap.put("filter", true);			
				header.add(headerDataMap);
	           //System.out.println("Columns: "+col);
	        }
        }
	    return header;   
	 } 
	
	 public List<String> tableColumnsName2()
	    {   
		 	
	        List<String> Columns = new ArrayList<String>();
	        Field[] fields = MisReport.class.getDeclaredFields();

	        for (Field field : fields) {
	            Column col = field.getAnnotation(Column.class);
	            if (col != null) {
	            	
	                Columns.add(col.name());
	                //System.out.println("Columns: "+col);
	            }
	         }
	          return Columns;   
	    } 
}
