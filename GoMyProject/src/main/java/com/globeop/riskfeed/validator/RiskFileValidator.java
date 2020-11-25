package com.globeop.riskfeed.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.globeop.riskfeed.dto.RiskFileDto;
import com.globeop.riskfeed.entity.BillTable;
import com.globeop.riskfeed.entity.ClientOnboardTable;
import com.globeop.riskfeed.entity.OnBordDto;
import com.globeop.riskfeed.web.DevelopmentController;
import com.globeop.riskfeed.web.FtpController;


@Component
public class RiskFileValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		if(RiskFileDto.class.equals(clazz)) {
			return true;
		}else {
			return false;
		}
		
	}
	
	@Override
	public void validate(Object obj, Errors errors) {
		RiskFileDto riskFileDto = (RiskFileDto)obj;
		
//		System.out.println("@@@@@@@@@@@@@@ "+riskFileDto.getOnBoardForm()+" @@@@@@@@@@@@@@");
//	      System.out.println(riskFileDto);
//	      System.out.println("@@@@@@@@@@@@@@ "+riskFileDto.getOnBoardForm()+" @@@@@@@@@@@@@@");  
	      
		if(riskFileDto.getOnBoardForm().equals("riskFileForm")){
			riskFileFormValidator(riskFileDto, errors);
		}
	}
	
	private static void riskFileFormValidator(RiskFileDto riskFileDto,Errors errors) {
		 if(riskFileDto.getClient().equals("")) {
	    	  errors.rejectValue("client", "RiskFileDto.client.empty");
	      }
	      if(riskFileDto.getRiskAggregator().equals("")) {
	    	  errors.rejectValue("riskAggregator", "RiskFileDto.riskAggregator.empty");
	      }if(riskFileDto.getFund().equals("0")) {
	    	  errors.rejectValue("fund", "RiskFileDto.fund.empty");
	      }
	}
	
}
