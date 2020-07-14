package com.globeop.riskfeed.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.globeop.riskfeed.dto.OnBoardFunds;
import com.globeop.riskfeed.entity.OnBordDto;

@Component
public class OnBordValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return OnBordDto.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
	      OnBordDto onBordDto = (OnBordDto)obj;
	      System.out.println("@@@@@@@@@@@@@@ "+onBordDto.getOnBoardForm()+" @@@@@@@@@@@@@@");
	      System.out.println(onBordDto);
	      System.out.println("@@@@@@@@@@@@@@ "+onBordDto.getOnBoardForm()+" @@@@@@@@@@@@@@");    
	      
	      if(onBordDto.getOnBoardForm().equals("onBoardForm1")) {
	    	  validateForm1(onBordDto, errors);
	      }else if(onBordDto.getOnBoardForm().equals("onBoardForm2")) {
	    	  validateForm1(onBordDto, errors);
	    	  validateForm2(onBordDto, errors);
	      }else if(onBordDto.getOnBoardForm().equals("billForm")) {
	    	  billFormValidator(onBordDto, errors);
	      }
		/*
		 * 
		 * if(onBordDto.getRiskAggregatorId() == -1) {
		 * errors.rejectValue("riskAggregatorId", "OnBordDto.riskAggregatorId.empty"); }
		 * 
		 * if(onBordDto.getClientId() == 0) { errors.rejectValue("clientId",
		 * "OnBordDto.clientId.empty"); }
		 * 
		 * ValidationUtils.rejectIfEmpty(errors, "fundName",
		 * "OnBordDto.fundName.empty"); ValidationUtils.rejectIfEmpty(errors,
		 * "setUpDate", "OnBordDto.setUpDate.empty");
		 * 
		 * if(onBordDto.getAutomationProcess().equals("-1") ) {
		 * errors.rejectValue("automationProcess", "OnBordDto.automationProcess.empty");
		 * }
		 * 
		 * if(onBordDto.getIsActive().equals("-1") ) { errors.rejectValue("isActive",
		 * "OnBordDto.isActive.empty"); }
		 * 
		 * ValidationUtils.rejectIfEmpty(errors, "frequency",
		 * "OnBordDto.frequency.empty");
		 * 
		 */
	}
	
	private static void validateForm1(OnBordDto onBordDto,Errors errors) {
		System.out.println("@@@@@@@@@@@@ validateForm1");
		if(onBordDto.getRiskAggregatorId() == -1) {
	    	  errors.rejectValue("riskAggregatorId", "OnBordDto.riskAggregatorId.empty");	  
	      }
	      
	      if(onBordDto.getClientId() == 0) {
	    	  errors.rejectValue("clientId", "OnBordDto.clientId.empty");
	      }
	      ValidationUtils.rejectIfEmpty(errors, "fundName", "OnBordDto.fundName.empty");
	}
	
	private static void validateForm2(OnBordDto onBordDto,Errors errors) {		
		
		for(OnBoardFunds onBoardFunds : onBordDto.getOnBoardFundsList()){
			if(onBoardFunds.getFrequency()==null) {
				ValidationUtils.rejectIfEmpty(errors, "frequency", "OnBordDto.fundFrequency.empty");
				break;
			}
		}
		
	    ValidationUtils.rejectIfEmpty(errors, "setUpDate", "OnBordDto.setUpDate.empty");
	    if(onBordDto.getAutomationProcess().equals("-1") ) {
	    	errors.rejectValue("automationProcess", "OnBordDto.automationProcess.empty");
	    }
	    if(onBordDto.getIsActive().equals("-1") ) {
	    	errors.rejectValue("isActive", "OnBordDto.isActive.empty");
	    }
	}
	
	private static void billFormValidator(OnBordDto onBordDto,Errors errors) {
		if(onBordDto.getRiskAggregatorId() == -1) {
	    	  errors.rejectValue("riskAggregatorId", "OnBordDto.riskAggregatorId.empty");	  
	      }
	      
	      if(onBordDto.getClientId() == 0) {
	    	  errors.rejectValue("clientId", "OnBordDto.clientId.empty");
	      }
	      
	      if(onBordDto.getIsClientPayingOldCharges().equals("-1")) {
	    	  errors.rejectValue("isClientPayingOldCharges", "OnBordDto.isClientPayingOldCharges.empty");
	      }
	      if(onBordDto.getIsWaivedOff().equals("-1")) {
	    	  errors.rejectValue("isWaivedOff", "OnBordDto.isWaivedOff.empty");
	      }
	}

}
