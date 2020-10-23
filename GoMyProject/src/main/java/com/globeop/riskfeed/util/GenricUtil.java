package com.globeop.riskfeed.util;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.globeop.gocheck.api.ClientRetriever;
import com.globeop.gocheck.api.ClientRetrieverFactory;
import com.globeop.gocheck.api.FundRetriever;
import com.globeop.gocheck.api.FundRetrieverFactory;
import com.globeop.gocheck.api.GoCheckApiConfig;
import com.globeop.gocheck.api.GoCheckApiConfigParser;
import com.globeop.gocheck.core.Client;
import com.globeop.gocheck.core.Fund;
import com.globeop.gocheck.core.FundStatus;
import com.globeop.riskfeed.dto.LabelValueDto;
import com.globeop.riskfeed.dto.OnBoardFunds;




public class GenricUtil {

public static List<LabelValueDto> getClientFundList(String clientShortname){
		
		List<LabelValueDto> list=new ArrayList<LabelValueDto>();
		try{
			Class<?> clazz = Class.forName("org.apache.xerces.parsers.SAXParser");
            XMLReader reader = (XMLReader) clazz.newInstance();
            GoCheckApiConfigParser goCheckApiConfigParser = new GoCheckApiConfigParser(reader);
            reader.setContentHandler(goCheckApiConfigParser);
			reader.parse(new InputSource(Resources.getResourceAsStream("templates/gocheck-api-config.xml"))); 
			GoCheckApiConfig config = goCheckApiConfigParser.getGoCheckClientConfig();
			FundRetriever fundRetriever = FundRetrieverFactory.newInstance(config);
			ClientRetriever clientRetriever = ClientRetrieverFactory.newInstance(config);
			Client client=clientRetriever.getClient(clientShortname);
			//System.out.println(client.getDescription()+">>"+client.getClientId());
	  
	 @SuppressWarnings("unchecked") List<Fund>
	 fundList=fundRetriever.getFundListForClient(client.getClientId());
	  
	 for(Fund f:fundList)
	 { 
		 FundStatus fs; 
		 String status=""; 
		 try{ 
			 fs = f.getStatus(); 
			 status=fs.getName(); 
		 }catch (Exception e){
			 
		 }
	  
		 if("Active".equals(status) && f.isTradingEntity()) { 
			 LabelValueDto labelValueDto = new LabelValueDto();
			 labelValueDto.setLabel(f.getShortName());
			 labelValueDto.setValue(f.getShortName()); 
			 list.add(labelValueDto); 
		 }
	 } 	 	
	 }catch (Exception e){ 
		 e.printStackTrace();
	 } 
		return list; 
	 
	}
	
	 
  

	// for local use dummy funds
	public static List<LabelValueDto> getClientFundList2(String clientShortname){
		 List<LabelValueDto> list=new ArrayList<LabelValueDto>();
		 LabelValueDto l1 = new LabelValueDto();
		 l1.setLabel("FUND1");
		 l1.setValue("FUND1");
		 
		 LabelValueDto l2 = new LabelValueDto();
		 
		 l2.setLabel("FUND2");
		 l2.setValue("FUND2");
		 
		 LabelValueDto l3 = new LabelValueDto();
		 l3.setLabel("FUND3");
		 l3.setValue("FUND3");
		 
		 LabelValueDto l4 = new LabelValueDto();
		 l4.setLabel("FUND4");
		 l4.setValue("FUND4");
		 
		 list.add(l1);
		 list.add(l2);
		 list.add(l3);
		 list.add(l4);
		 return list;
	}
	
	
	public static List<OnBoardFunds> getClientFundList2(){
		 List<OnBoardFunds> list=new ArrayList<OnBoardFunds>();
		 OnBoardFunds f1 = new OnBoardFunds();
		 f1.setFundName("FUND1");
	
		 OnBoardFunds f2 = new OnBoardFunds();
		 f2.setFundName("FUND2");
	
		 OnBoardFunds f3 = new OnBoardFunds();
		 f3.setFundName("FUND3");
	
		 OnBoardFunds f4 = new OnBoardFunds();
		 f4.setFundName("FUND4");
	
		 
		 list.add(f1);
		 list.add(f2);
		 list.add(f3);
		 list.add(f4);
		 return list;
	}
		
	public static Date convertStringToDate(String date) {
		Date date1=null;
		try {
			date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
			/*
			 * System.out.println(date1); DateFormat dateFormat = new
			 * SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); String strDate =
			 * dateFormat.format(date1); System.out.println(strDate); date1=new
			 * SimpleDateFormat("yyyy-mm-dd hh:mm:ss").parse(strDate);
			 * System.out.println(date1);
			 */
            
		}catch (Exception e) {
			e.printStackTrace();
		}		  
		return date1;
	}
	
	public static String decode(String input) {
		byte[] decodedBytes = Base64.getDecoder().decode(input);
	    return new String(decodedBytes);
	}
	
	public static String  getFileName(MultipartFile file) {
		return StringUtils.cleanPath(file.getOriginalFilename());		
	}
	
	  public static void main(String[] args) throws Exception {
	  getClientFundList2("BFAM");
	  
	  //System.out.println(convertStringToDate("2020-04-04"));
	  
	  //System.out.println(AutomationProcess.valueOf("RiskMQ"));
	  
	  //System.out.println(AutomationProcess.getEnum("RiskMQ"));
	  
	  //Enum automationProcess=AutomationProcess.getEnum("RiskMQ");
	  //System.out.println(AutomationProcess.valueOf("RiskMQ")); 
	  
	  }
	 
}
